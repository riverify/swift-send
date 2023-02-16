package org.rivor.swiftsend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.rivor.swiftsend.entity.File;
import org.rivor.swiftsend.mapper.FileMapper;
import org.rivor.swiftsend.service.FileService;
import org.rivor.swiftsend.util.CodeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * <p>Project: swift-send - FileServiceImpl
 * <p>Powered by river On 2023/02/16 9:53 PM
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
@Service
@Slf4j
@EnableTransactionManagement
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Value("${swift.path}")
    private String basePath;

    @Override
    @Transactional
    public String generateKey() {
        // 在这里编写生成密钥的逻辑
        String key = CodeUtils.generateValidateCode4String(4);
        // 如果数据库中已经存在了这个密钥，则重新生成
        LambdaQueryWrapper<File> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(File::getFileCode, key);
        while (this.count(wrapper) > 0) {
            key = CodeUtils.generateValidateCode4String(4);
        }
        // 如果数据库中不存在这个密钥，则返回这个密钥
        log.info("The key is: {}", key);
        return key;
    }

    @Override
    public void saveFile(String key, MultipartFile file) {
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 使用UUID生成文件名
        String fileName = UUID.randomUUID() + suffix;
        // 创建一个目录，判断是否存在，不存在则创建
        java.io.File dir = new java.io.File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new java.io.File(basePath + fileName)); // 将文件转存到指定目录
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
