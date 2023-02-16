package org.rivor.swiftsend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.rivor.swiftsend.entity.SavedFiles;
import org.rivor.swiftsend.service.SavedFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

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

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param savedFiles 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SavedFiles> page, SavedFiles savedFiles) {
        return success(this.savedFilesService.page(page, new QueryWrapper<>(savedFiles)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.savedFilesService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param savedFiles 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SavedFiles savedFiles) {
        return success(this.savedFilesService.save(savedFiles));
    }

    /**
     * 修改数据
     *
     * @param savedFiles 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SavedFiles savedFiles) {
        return success(this.savedFilesService.updateById(savedFiles));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.savedFilesService.removeByIds(idList));
    }
}

