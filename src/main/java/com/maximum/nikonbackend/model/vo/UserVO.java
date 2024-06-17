package com.maximum.nikonbackend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.vo
 * @Author: maximum
 * @CreateTime: 2024-06-15
 * @Version: 1.0
 */

@Data
public class UserVO implements Serializable {


    /**
     *
     */
    private String userName;

    /**
     *
     */
    private String userAccount;

    /**
     *
     */
    private String userAvatar;

    /**
     *
     */
    private Integer gender;

    /**
     * user / admin
     */
    private String userRole;


    private static final long serialVersionUID = 1L;
}
