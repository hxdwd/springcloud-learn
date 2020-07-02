import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class XlsUtils {

    public static void main(String[] args) {
        getDate("","");
    }


    public static void getDate(String filePath, String id) {
        // 解压Book1.xlsx
        ZipFile xlsxFile;

        try {

            xlsxFile = new ZipFile(new File("C:\\Users\\admin\\Desktop\\test.xlsx"));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // 先读取sharedStrings.xml  其中包含该表所有用到的共享字符串
            // 共享：如第一格和第二格都是 lisi  那么lisi只会在sharedStrings.xml中出现一次
            // 放置的顺序不是按照字符串在表格中出现
            ZipEntry sharedStringXML = xlsxFile.getEntry("xl/sharedStrings.xml");
            InputStream sharedStringXMLIS = xlsxFile.getInputStream(sharedStringXML);
            Document sharedString;
            sharedString = dbf.newDocumentBuilder().parse(sharedStringXMLIS);
            NodeList str = sharedString.getElementsByTagName("t");
            System.out.println(str.getLength());
            String sharedStrings[] = new String[str.getLength()];
            for (int n = 0; n < str.getLength(); n++) {
                Element element = (Element) str.item(n);
                sharedStrings[n] = element.getTextContent();
            }
            // 找到解压文件夹里的workbook.xml,此文件中包含了这张工作表中有几个sheet
            ZipEntry workbookXML = xlsxFile.getEntry("xl/workbook.xml");
            InputStream workbookXMLIS = xlsxFile.getInputStream(workbookXML);
            Document doc = dbf.newDocumentBuilder().parse(workbookXMLIS);
            // 获取一共有几个sheet
            NodeList nl = doc.getElementsByTagName("sheet");
            for (int i = 0; i < nl.getLength(); i++) {
                Element element = (Element) nl.item(i);// 强转为Element和接下来就是xml解析
                // 接着就要到解压文件夹里找到对应的name值的xml文件，比如在workbook.xml中有<sheet name="Sheet1"
                // sheetId="1" r:id="rId1" /> 节点
                // 那么就可以在解压文件夹里的xl/worksheets下找到sheet1.xml,这个xml文件夹里就是包含的表格的‘内容’
                // 内容并非实际内容，分两种
                // 1是非共享比如数字，那么我们取值时直接使用该值
                // 2是共享字符串，此时里面的值则为在sharedStrings.xml中出现的位置
                ZipEntry sheetXML = xlsxFile.getEntry("xl/worksheets/"
                        + element.getAttribute("name").toLowerCase() + ".xml");
                InputStream sheetXMLIS = xlsxFile.getInputStream(sheetXML);
                Document sheetdoc = dbf.newDocumentBuilder().parse(sheetXMLIS);
                NodeList rowdata = sheetdoc.getElementsByTagName("row");//所有行
                Element e = null;
                Element ess[] = new Element[2];

                for (int j = 0, jlen = rowdata.getLength(); j < jlen; j++) {//遍历每一行
                    Element row = (Element) rowdata.item(j); //第j行
                    // 根据行得到每个行中的列
                    NodeList columndata = row.getElementsByTagName("c"); //j行的所有单元格
                    for (int k = 0, len = columndata.getLength(); k < len; k++) { //遍历一行的所有单元格
                        Element column = (Element) columndata.item(k);//第k单元格
                        NodeList values = column.getElementsByTagName("v");//c是单元格 v是单元格里面的值
                        Element value = (Element) values.item(0);//在xml中每个节点都可以有子节点，所有这里是NodeList为一个单元格

                        int clen = 0;
                        if (isCommon(column)) {
                            // 如果是共享字符串则在sharedstring.xml里查找该列的值
                            String mobile = sharedStrings[Integer.parseInt(value.getTextContent())];
                            System.out.print(mobile + " + ");
                        } else {
                            if (value != null) {
                                System.out.print(value.getTextContent() + " - ");
                            } else {
                                System.out.println("null");
                            }
                        }
                    }
                    System.out.println();
                }
            }
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    private static boolean isCommon(Element column) {
        return column.getAttribute("t") != null && column.getAttribute("t").equals("s");
    }

}