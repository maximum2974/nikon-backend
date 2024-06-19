package com.maximum.nikonbackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class ShippingRecordsVO implements Serializable {
    /**
     *
     */
    private String uuid;

    /**
     *
     */
    private Integer quantity;

    /**
     *
     */
    private BigDecimal price;

    /**
     *
     */
    private String creditCardNumber;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String shippingAddress;

    /**
     *
     */
    private String productName;

    /**
     *
     */
    private String productUrl;
}
