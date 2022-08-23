package com.practice.mall.controller;

import com.practice.mall.util.JSONResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {


    // SpringMVC会将上传的文件信息封装到MultipartFile
    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONResult uploadImage(MultipartFile file) {
        String name = UUID.randomUUID().toString().replace("-", "");
        String fileName = file.getOriginalFilename();
        System.out.println("fileName: " + fileName);//aa.jpg
        String extension = FilenameUtils.getExtension(fileName);//jpg
        String newFileName = name + "." + extension;//52635b3153cf415da70179a2c472b3b9.jpg
        String filePath = "D:\\mypic\\" + newFileName;//D:/mypic/52635b3153cf415da70179a2c472b3b9.jpg
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONResult.ok("上传成功", newFileName);
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);//9dbcb93e-1e6d-46c1-a0a2-faee5a71254e
        System.out.println(uuid.replace("-", ""));//52635b3153cf415da70179a2c472b3b9
    }
}
