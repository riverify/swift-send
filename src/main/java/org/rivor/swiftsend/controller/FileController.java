package org.rivor.swiftsend.controller;

import lombok.extern.slf4j.Slf4j;
import org.rivor.swiftsend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

        if (file.getSize() > 104857600L) {  // 100MB
            Map<String, String> error = new HashMap<>();
            error.put("message", "File size must be less than 100MB");
            log.debug("File size must be less than 100MB");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        try {
            // 如果文件过大，则返回错误信息
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

    @GetMapping("/get")
    public ResponseEntity<Resource> getFile(@RequestParam("key") String key) {
        // 在这里编写获取文件的逻辑
        // 如果找到了对应的文件，则返回文件资源

        // 如果没有找到文件，则返回状态码404 Not Found
        return null;
    }
}
