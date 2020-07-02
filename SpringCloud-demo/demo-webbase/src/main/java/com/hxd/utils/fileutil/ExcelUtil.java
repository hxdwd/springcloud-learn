package com.hxd.utils.fileutil;

import com.hxd.commom.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtil {



    public static List<User> excelToUser(String filePath) {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<User> users=null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String columns[] = {"name","age","sex"};
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数 (包含标题行)
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            //迭代HashMap的顺序并不是HashMap放置的顺序，也就是无序。
            //所有LinkedHashMap闪亮登场
            //从第2行开始读取，第一行是标题行
            users=new ArrayList<User>();

            for (int i = 1; i<rownum; i++) {
                User user=new User();
                row = sheet.getRow(i);
                if(row !=null){
                    user.setUsername((String) getCellFormatValue(row.getCell(0)));
                    user.setAge(parse((String) getCellFormatValue(row.getCell(1))));
                    user.setSex((String) getCellFormatValue(row.getCell(2)));
                    users.add(user);
                }else{
                    break;
                }
            }
        }
        return users;
    }

    private static Integer parse(String s){
        try {
            s=s.split("\\.")[0];
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }
    //读取excel
    private static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

}
