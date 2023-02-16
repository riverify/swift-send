package org.rivor.swiftsend.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import org.rivor.swiftsend.service.SavedFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (SavedFiles)表控制层
 *
 * @author makejava
 * @since 2023-02-16 16:12:04
 */
@RestController
@RequestMapping("savedFiles")
public class SavedFilesController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private SavedFilesService savedFilesService;



}

