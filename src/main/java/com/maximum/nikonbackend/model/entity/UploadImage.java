package com.maximum.nikonbackend.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "upload_image")
@Data
public class UploadImage implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String image;

    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
