package com.practice.mall.controller;

import com.practice.mall.constant.RedisConstant;
import com.practice.mall.util.ImageServerUtil;
import com.practice.mall.util.JSONResult;
import com.practice.mall.util.QiniuUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate redisTemplate;

    // SpringMVC会将上传的文件信息封装到MultipartFile
    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONResult uploadImage(MultipartFile file) {
        String name = UUID.randomUUID().toString().replace("-", "");    //生成随机文件名
        String fileName = file.getOriginalFilename();       //获得上传文件名
        System.out.println("fileName: " + fileName);//aa.jpg
        String extension = FilenameUtils.getExtension(fileName);//jpg   获得文件后缀
        String newFileName = name + "." + extension;//52635b3153cf415da70179a2c472b3b9.jpg  拼接形成新的文件名

        if (ImageServerUtil.IMG_SERVER == ImageServerUtil.LOCAL) {      //判断存为本地还是云端
            String filePath = "D:\\mypic\\" + newFileName;//D:/mypic/52635b3153cf415da70179a2c472b3b9.jpg
            try {
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                QiniuUtils.upload2Qiniu(file.getBytes(), newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE, newFileName);

        return JSONResult.ok("上传成功", newFileName);
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);//9dbcb93e-1e6d-46c1-a0a2-faee5a71254e
        System.out.println(uuid.replace("-", ""));//52635b3153cf415da70179a2c472b3b9
    }
}
