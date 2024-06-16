package com.maximum.nikonbackend.model.dto.shippingRecords;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.dto.shippingRecords
 * @Author: maximum
 * @CreateTime: 2024-06-16
 * @Version: 1.0
 */

@Data
public class PurchaseRequest implements Serializable {
    /**
     *
     */
    private String productUuid;

    /**
     *
     */
    private Integer quantity;

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

    private static final long serialVersionUID = 5382402646056771248L;
}
