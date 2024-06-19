package com.maximum.nikonbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName shipping_records
 */
@TableName(value ="shipping_records")
@Data
public class ShippingRecords implements Serializable {
    /**
     * 
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
    private Long productId;

    /**
     * 
     */
    private Long userId;

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
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}