package com.maximum.nikonbackend.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.vo
 * @Author: maximum
 * @CreateTime: 2024-12-11
 * @Version: 1.0
 */
@Data
public class UploadImageVO implements Serializable {
    private String image;
}
