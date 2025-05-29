package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import com.example.springboot.common.Result;
import com.example.springboot.exception.CustomException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private static final String filePath = System.getProperty("user.dir") + "/files/";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (!FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);
        }
        String fileName = System.currentTimeMillis() + "_" + originalFilename;
        String realPath = filePath + fileName;
        try {
            FileUtil.writeBytes(file.getBytes(), realPath);
        } catch (IOException e) {
            throw new CustomException("500",  "文件上传失败");
        }
        return Result.success();
    }
}
//public class FileController {
//
//    private static final String filePath = System.getProperty("user.dir") + "/files/";
//
//    //文件上传路径
//    @PostMapping("/upload")
//    public Result upload(MultipartFile file) {
//        String originalFilename = file.getOriginalFilename();
//        if (!FileUtil.isDirectory(filePath)) {
//            FileUtil.mkdir(filePath);
//        }
//        String fileName = System.currentTimeMillis() + "_" + originalFilename;
//        String realPath = filePath + fileName;
//        try {
//            FileUtil.writeBytes(file.getBytes(), realPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new CustomException("500", "文件上传失败");
//        }
//        //返回网络链接
//        //http://localhost:9090/files/upload/xx.jpg
//        return Result.success();
//    }
//}
