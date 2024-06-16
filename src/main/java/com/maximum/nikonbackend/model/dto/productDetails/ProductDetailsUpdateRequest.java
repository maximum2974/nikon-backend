package com.maximum.nikonbackend.model.dto.productDetails;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.dto.productDetails
 * @Author: maximum
 * @CreateTime: 2024-06-16
 * @Version: 1.0
 */
@Data
public class ProductDetailsUpdateRequest implements Serializable {
    /**
     *
     */
    private String uuid;

    /**
     *
     */
    private String productName;

    /**
     *
     */
    private String productDescription;

    /**
     *
     */
    private BigDecimal productPrice;

    /**
     *
     */
    private Integer quantity;

    private static final long serialVersionUID = -4764753844199057497L;
}
