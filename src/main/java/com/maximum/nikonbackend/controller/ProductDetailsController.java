package com.maximum.nikonbackend.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.BaseResponse;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.common.GithubUploaderUtils;
import com.maximum.nikonbackend.common.ResultUtils;
import com.maximum.nikonbackend.constant.UserConstant;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.dto.productDetails.ProductDetailsAddRequest;
import com.maximum.nikonbackend.model.dto.productDetails.ProductDetailsUpdateRequest;
import com.maximum.nikonbackend.model.entity.Inventory;
import com.maximum.nikonbackend.model.entity.ProductDetails;
import com.maximum.nikonbackend.service.InventoryService;
import com.maximum.nikonbackend.service.ProductDetailsService;
import com.maximum.nikonbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private GithubUploaderUtils githubUploaderUtils;

    /**
     * admin add product and inventory
     * @param productName
     * @param productDescription
     * @param productPrice
     * @param quantity
     * @param productImage
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> addProductDetails(@RequestParam(value = "productName", required = true) String productName,
                                                   @RequestParam(value = "productDescription", required = true) String productDescription,
                                                   @RequestParam(value = "productPrice", required = true) BigDecimal productPrice,
                                                   @RequestParam(value = "quantity", required = true) Integer quantity,
                                                   @RequestParam(value = "productImage", required = true)MultipartFile productImage) throws IOException {
        if(StringUtils.isAnyBlank(productName, productDescription)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(productPrice.compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "price cannot be less than or equal to 0");
        }
        if(quantity < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "quantity cannot be less than 0");
        }
        if(productImage == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String productUrl = githubUploaderUtils.upload(productImage);
        boolean result = productDetailsService.addProductDetails(productName, productDescription, productPrice, quantity, productUrl);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @Transactional
    public BaseResponse<Boolean> updateProductDetails(@RequestParam(value = "uuid", required = true) String uuid,
                                                      @RequestParam(value = "productName", required = false) String productName,
                                                      @RequestParam(value = "productDescription", required = false) String productDescription,
                                                      @RequestParam(value = "productPrice", required = false) BigDecimal productPrice,
                                                      @RequestParam(value = "quantity", required = false) Integer quantity,
                                                      @RequestParam(value = "productImage", required = false)MultipartFile productImage) throws IOException {
        if(uuid == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductDetails productDetails = productDetailsService.getProductDetailsByUuid(uuid);
        if(StringUtils.isNotBlank(productName)){
            productDetails.setProductName(productName);
        }
        if(StringUtils.isNotBlank(productDescription)){
            productDetails.setProductDescription(productDescription);
        }
        if(productPrice != null){
            if(productPrice.compareTo(BigDecimal.ZERO) > 0){
                productDetails.setProductPrice(productPrice);
            }
        }
        if(productImage != null){
            String productUrl = githubUploaderUtils.upload(productImage);
            productDetails.setProductUrl(productUrl);
        }
        boolean result = productDetailsService.updateById(productDetails);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        if(quantity != null){
            if(quantity >= 0){
                UpdateWrapper<Inventory> inventoryUpdateWrapper = new UpdateWrapper<>();
                inventoryUpdateWrapper.eq("product_id", productDetails.getId());
                inventoryUpdateWrapper.set("quantity", quantity);
                boolean update = inventoryService.update(inventoryUpdateWrapper);
                if(!update){
                    throw new BusinessException(ErrorCode.OPERATION_ERROR);
                }
            }else{
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
        }
        return ResultUtils.success(true);
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
