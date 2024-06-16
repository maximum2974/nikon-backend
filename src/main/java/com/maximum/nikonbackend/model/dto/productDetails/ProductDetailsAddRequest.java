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
public class ProductDetailsAddRequest implements Serializable {
    private static final long serialVersionUID = 8722363048121163L;
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
}
