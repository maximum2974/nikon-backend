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
public class PutawayRequest implements Serializable {
    private static final long serialVersionUID = 2678436646835874984L;
    private String uuid;

}
