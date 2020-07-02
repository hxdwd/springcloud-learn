package com.hxd.controller;

import com.hxd.commom.User;
import com.hxd.utils.fileutil.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequestMapping("file")
@Controller
public class WebbaseFlieController {

    private final String UPLOAD_FILE_PATH = "C:\\Users\\admin\\Desktop\\";

    @RequestMapping("uploadFile")
    @ResponseBody
    public String upFile(MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) return "error";

        String fileName = file.getOriginalFilename();
        String filePath = UPLOAD_FILE_PATH + new Date().getTime() + fileName;
        File dest = new File(filePath);
        try {
            file.transferTo(dest);
            List<User> users = ExcelUtil.excelToUser(filePath);
            if (null == users)
                return "文件解析失败";
            for (User user : users) {
                System.out.println(user);
            }
            return "success";
        } catch (IOException e) {
        }
        return "error";
    }

    @RequestMapping("tsetFileUtil")
    @ResponseBody
    public String tsetFileUtil() {
        /*setVa();
        System.out.println("-------");
        List<Object> user = com.hxd.utils.ExcelUtil.getDate("C:\\Users\\admin\\Desktop\\test.xlsx", "user");
        for (Object o : user) {
            System.out.println(o);
        }*/
        return "";
    }


}
