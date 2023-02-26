package org.rivor.swiftsend.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.rivor.swiftsend.entity.Files;
import org.rivor.swiftsend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <p>Project: swift-send - AutoCleanUp
 * <p>Powered by river On 2023/02/17 4:32 PM
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
@Component
@Slf4j
public class AutoCleanUp {

    @Value("${swift.path}")
    private String basePath;

    @Autowired
    private FileService fileService;

    // 每隔一段时间，清理一次数据库中的过期文件
    @Scheduled(fixedRate = 10 * 60 * 1000)  // 10 minutes
    public void cleanExpiredFiles() {
        log.info("Start cleaning expired files");
        File directory = new File(basePath);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    long lastModified = file.lastModified();
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastModified > 24 * 60 * 60 * 1000) { // 24 hours
                        log.info("Deleting expired file: {}", file.getName());
                        if (file.delete()) {
                            log.info("Deleted file: {}", file.getName());
                            // 删除数据库中的记录
                            LambdaQueryWrapper<Files> queryWrapper = new LambdaQueryWrapper<>();
                            queryWrapper.eq(Files::getFileName, file.getName());
                            fileService.remove(queryWrapper);
                            log.info("Deleted record in database: {}", file.getName());
                        } else {
                            log.warn("Failed to delete file: {}", file.getName());
                        }
                    }
                }
            }
        } else {
            log.warn("Path is not a directory!");
        }
    }
}
