package com.maximum.nikonbackend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.maximum.nikonbackend.model.dto.productDetails.ProductDetailsUpdateRequest;
import com.maximum.nikonbackend.model.entity.ProductDetails;

import java.math.BigDecimal;

public interface ProductDetailsService extends IService<ProductDetails> {

    /**
     * add product details
     * @param productName
     * @param productDescription
     * @param productPrice
     * @param quantity
     * @return
     */
    boolean addProductDetails(String productName, String productDescription, BigDecimal productPrice, Integer quantity);

    /**
     * update product details
     * @param detailsUpdateRequest
     * @return
     */
    Boolean updateProductDetails(ProductDetailsUpdateRequest detailsUpdateRequest);

    /**
     * get by uuid
     * @param uuid
     * @return
     */
    ProductDetails getProductDetailsByUuid(String uuid);
}
