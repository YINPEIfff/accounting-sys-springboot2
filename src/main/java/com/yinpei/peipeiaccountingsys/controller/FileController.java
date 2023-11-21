package com.yinpei.peipeiaccountingsys.controller;

import cn.hutool.core.io.FileUtil;
import com.yinpei.peipeiaccountingsys.common.AuthAccess;
import com.yinpei.peipeiaccountingsys.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${ip:localhost}")
    String ip;

    @Value("${server.port}")
    String port;

    private final static String ROOT_PATH = System.getProperty("user.dir") + '\\' + "file";

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String mainName = FileUtil.mainName(originalFilename);
        String extName = FileUtil.extName(originalFilename);
        String basePath = ROOT_PATH;
        File baseFilePath = new File(basePath);
        if (!baseFilePath.exists())
            baseFilePath.mkdirs();
        String fileName = basePath + '\\' + originalFilename;
        File file = new File(fileName);
        String legalFileName = System.currentTimeMillis() + '_' + originalFilename;
        if (file.exists()) {
            file = new File(basePath + '\\' + legalFileName);
            multipartFile.transferTo(file);
            String downloadAddress = "http://" + ip + ":" + port + "/file/download/" + legalFileName;
            return Result.success(downloadAddress);
        }
        multipartFile.transferTo(file);
        String downloadAddress = "http://" + ip + ":" + port + "/file/download/" + originalFilename;
        return Result.success(downloadAddress);
    }

    @AuthAccess
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse httpServletResponse) throws IOException {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String filePath = ROOT_PATH + File.separator + fileName;
        if (!FileUtil.exist(filePath))
            return;
        byte[] bytes = FileUtil.readBytes(filePath);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

}
