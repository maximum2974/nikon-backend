package com.maximum.nikonbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class ProductDetailsVo implements Serializable {

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
    private String productUrl;

    /**
     *
     */
    private Integer quantity;

    private static final long serialVersionUID = 1951697066793357286L;
}
