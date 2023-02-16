package org.rivor.swiftsend.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * (SavedFiles)表实体类
 *
 * @author makejava
 * @since 2023-02-16 16:12:10
 */
@SuppressWarnings("serial")
public class SavedFiles extends Model<SavedFiles> {

    private String fid;

    private String fileName;

    private Integer fileCode;

    private Integer isDeleted;


    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileCode() {
        return fileCode;
    }

    public void setFileCode(Integer fileCode) {
        this.fileCode = fileCode;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}

