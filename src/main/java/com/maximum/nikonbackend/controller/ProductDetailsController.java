package com.maximum.nikonbackend.controller;

import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.BaseResponse;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.common.ResultUtils;
import com.maximum.nikonbackend.constant.UserConstant;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.dto.productDetails.ProductDetailsAddRequest;
import com.maximum.nikonbackend.model.dto.productDetails.ProductDetailsUpdateRequest;
import com.maximum.nikonbackend.model.entity.ProductDetails;
import com.maximum.nikonbackend.service.InventoryService;
import com.maximum.nikonbackend.service.ProductDetailsService;
import com.maximum.nikonbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.controller
 * @Author: maximum
 * @CreateTime: 2024-06-16
 * @Version: 1.0
 */

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductDetailsController {
    @Autowired
    private ProductDetailsService productDetailsService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private UserService userService;

    /**
     * admin add product and inventory
     * @param productDetailsAddRequest
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> addProductDetails(@RequestBody ProductDetailsAddRequest productDetailsAddRequest){
        if(productDetailsAddRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String productName = productDetailsAddRequest.getProductName();
        String productDescription = productDetailsAddRequest.getProductDescription();
        BigDecimal productPrice = productDetailsAddRequest.getProductPrice();
        Integer quantity = productDetailsAddRequest.getQuantity();
        if(StringUtils.isAnyBlank(productName, productDescription)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(productPrice.compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "price cannot be less than or equal to 0");
        }
        if(quantity < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "quantity cannot be less than 0");
        }
        boolean result = productDetailsService.addProductDetails(productName, productDescription, productPrice, quantity);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateProductDetails(@RequestBody ProductDetailsUpdateRequest detailsUpdateRequest){
        if(detailsUpdateRequest == null || detailsUpdateRequest.getUuid() == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean result = productDetailsService.updateProductDetails(detailsUpdateRequest);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }

    @DeleteMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteProductDetails(String uuid){
        if(uuid == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductDetails productDetails = productDetailsService.getProductDetailsByUuid(uuid);
        if(productDetails == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(productDetails.getIsDiscontinued() == 1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = productDetailsService.removeById(productDetails.getId());
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }
}
