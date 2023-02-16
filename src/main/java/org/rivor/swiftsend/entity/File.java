package org.rivor.swiftsend.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Project: swift-send - file
 * <p>Powered by river On 2023/02/16 9:48 PM
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
@Data
public class File implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long fileId;

    private String fileName;

    private String fileCode;

    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;
}
