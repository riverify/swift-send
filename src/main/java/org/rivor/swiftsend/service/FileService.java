package org.rivor.swiftsend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.rivor.swiftsend.entity.Files;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * <p>Project: swift-send - FileService
 * <p>Powered by river On 2023/02/16 9:52 PM
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
public interface FileService extends IService<Files> {
    String generateKey();

    void saveFile(String key, MultipartFile file);

    File getFile(String key);
}
