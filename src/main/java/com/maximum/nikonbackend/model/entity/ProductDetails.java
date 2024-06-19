package com.maximum.nikonbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName product_details
 */
@TableName(value ="product_details")
@Data
public class ProductDetails implements Serializable {
    /**
     * product_id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDiscontinued;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}