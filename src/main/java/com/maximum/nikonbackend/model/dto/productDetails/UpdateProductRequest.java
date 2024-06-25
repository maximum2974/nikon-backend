package com.maximum.nikonbackend.model.dto.productDetails;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.dto.productDetails
 * @Author: maximum
 * @CreateTime: 2024-06-25
 * @Version: 1.0
 */

@Data
public class UpdateProductRequest implements Serializable {
    private static final long serialVersionUID = 318794766901247458L;
    private String uuid;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer quantity;
    private String productImage;
}
