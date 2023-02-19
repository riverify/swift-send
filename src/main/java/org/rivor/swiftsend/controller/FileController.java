package org.rivor.swiftsend.controller;

import lombok.extern.slf4j.Slf4j;
import org.rivor.swiftsend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project: swift-send - FileController
 * <p>Powered by river On 2023/02/16 9:58 PM
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(MultipartFile file) {

        // 如果文件过大，则返回错误信息
        if (file.getSize() > 104857600L) {  // 100MB
            Map<String, String> error = new HashMap<>();
            error.put("message", "File size must be less than 100MB");
            log.debug("File size must be less than 100MB");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        try {
            // generateKey()方法会生成一个唯一的key
            String key = fileService.generateKey();
            Map<String, String> response = new HashMap<>();
            response.put("key", key);
            fileService.saveFile(key, file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 如果出现异常，则返回错误信息
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile(String key) {

        // 根据key获取文件
        File file = fileService.getFile(key);

        if (file == null) {
            // 如果没有找到文件，则返回状态码404 Not Found
            return ResponseEntity.notFound().build();
        }

        // 加载文件数据
        ByteArrayResource resource = null;
        HttpHeaders headers = null;

        try {
            Path path = file.toPath();
            resource = new ByteArrayResource(Files.readAllBytes(path));

            // 使用URLEncoder.encode()方法对文件名进行编码
            String encodedFilename = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");

            // 设置响应头
            headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path));
            headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回响应实体
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
