package com.maximum.nikonbackend.model.dto.productDetails;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.dto.productDetails
 * @Author: maximum
 * @CreateTime: 2024-06-26
 * @Version: 1.0
 */

@Data
public class DeleteRequest implements Serializable {
    private static final long serialVersionUID = -3669442812288136126L;
    private String uuid;
}
