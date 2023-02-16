package org.rivor.swiftsend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.rivor.swiftsend.dao.SavedFilesDao;
import org.rivor.swiftsend.entity.SavedFiles;
import org.rivor.swiftsend.service.SavedFilesService;
import org.springframework.stereotype.Service;

/**
 * (SavedFiles)表服务实现类
 *
 * @author makejava
 * @since 2023-02-16 16:12:18
 */
@Service("savedFilesService")
public class SavedFilesServiceImpl extends ServiceImpl<SavedFilesDao, SavedFiles> implements SavedFilesService {

}

