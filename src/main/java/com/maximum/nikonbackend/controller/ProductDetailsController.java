package com.maximum.nikonbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.*;
import com.maximum.nikonbackend.constant.UserConstant;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.dto.productDetails.AddProductRequest;
import com.maximum.nikonbackend.model.dto.productDetails.DeleteRequest;
import com.maximum.nikonbackend.model.dto.productDetails.PutawayRequest;
import com.maximum.nikonbackend.model.dto.productDetails.UpdateProductRequest;
import com.maximum.nikonbackend.model.entity.Inventory;
import com.maximum.nikonbackend.model.entity.ProductDetails;
import com.maximum.nikonbackend.model.vo.ProductDetailsVO;
import com.maximum.nikonbackend.service.InventoryService;
import com.maximum.nikonbackend.service.ProductDetailsService;
import com.maximum.nikonbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * admin add product
     * @param addProductRequest
     * @return
     * @throws IOException
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> addProductDetails(@RequestBody AddProductRequest addProductRequest){
        String productName = addProductRequest.getProductName();
        String productDescription = addProductRequest.getProductDescription();
        BigDecimal productPrice = addProductRequest.getProductPrice();
        Integer quantity = addProductRequest.getQuantity();
        String productImage = addProductRequest.getProductUrl();
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
        boolean result = productDetailsService.addProductDetails(productName, productDescription, productPrice, quantity, productImage);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @Transactional
    public BaseResponse<Boolean> updateProductDetails(@RequestBody UpdateProductRequest updateProductRequest) throws IOException {
        String uuid = updateProductRequest.getUuid();
        String productName = updateProductRequest.getProductName();
        String productDescription = updateProductRequest.getProductDescription();
        BigDecimal productPrice = updateProductRequest.getProductPrice();
        Integer quantity = updateProductRequest.getQuantity();
        String productImage = updateProductRequest.getProductUrl();
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
            productDetails.setProductUrl(productImage);
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

    @PostMapping("/putaway")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> putawayProductDetails(@RequestBody PutawayRequest putawayRequest){
        String uuid = putawayRequest.getUuid();
        if(uuid == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductDetails productDetails = productDetailsService.getProductDetailsByUuid(uuid);
        if(productDetails == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if(productDetails.getIsDiscontinued() == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        productDetails.setIsDiscontinued(0);
        boolean result = productDetailsService.updateById(productDetails);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteProductDetails(@RequestBody DeleteRequest deleteRequest){
        String uuid = deleteRequest.getUuid();
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
        productDetails.setIsDiscontinued(1);
        boolean result = productDetailsService.updateById(productDetails);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }

    @GetMapping("/list/user")
    public BaseResponse<Page<ProductDetailsVO>> getUserProductDetails(@RequestParam long current,
                                                                      @RequestParam long size){
        if(current < 0 || size <= 0 || size > 50){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_discontinued", 0);
        Page<ProductDetails> productDetailsPage = productDetailsService.page(new Page<>(current, size), queryWrapper);
        List<ProductDetails> productDetailsList = productDetailsPage.getRecords();
        if(productDetailsList.isEmpty()){
            return ResultUtils.success(new Page<>());
        }
        List<Long> idList = productDetailsList.stream()
                .map(ProductDetails::getId)
                .toList();
        Map<Long, Integer> inventoryMap = inventoryService.getQuantitiesByProductIds(idList);
        List<ProductDetailsVO> productDetailsVOList = productDetailsList.stream().map(productDetails -> {
            ProductDetailsVO productDetailsVo = new ProductDetailsVO();
            BeanUtils.copyProperties(productDetails, productDetailsVo);
            productDetailsVo.setQuantity(inventoryMap.getOrDefault(productDetails.getId(), 0));
            return productDetailsVo;
        }).collect(Collectors.toList());
        Page<ProductDetailsVO> productDetailsVoPage = new Page<>(current, size, productDetailsPage.getTotal());
        productDetailsVoPage.setRecords(productDetailsVOList);

        return ResultUtils.success(productDetailsVoPage);
    }

    @GetMapping("/list/admin")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<ProductDetailsVO>> getAdminProductDetails(@RequestParam long current,
                                                                       @RequestParam long size){
        if(current < 0 || size <= 0 || size > 50){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("is_discontinued = 1");
        Page<ProductDetails> productDetailsPage = productDetailsService.page(new Page<>(current, size), queryWrapper);
        List<ProductDetails> productDetailsList = productDetailsPage.getRecords();
        if(productDetailsList.isEmpty()){
            return ResultUtils.success(new Page<>());
        }
        List<Long> idList = productDetailsList.stream()
                .map(ProductDetails::getId)
                .toList();
        Map<Long, Integer> inventoryMap = inventoryService.getQuantitiesByProductIds(idList);
        List<ProductDetailsVO> productDetailsVOList = productDetailsList.stream().map(productDetails -> {
            ProductDetailsVO productDetailsVo = new ProductDetailsVO();
            BeanUtils.copyProperties(productDetails, productDetailsVo);
            productDetailsVo.setQuantity(inventoryMap.getOrDefault(productDetails.getId(), 0));
            return productDetailsVo;
        }).collect(Collectors.toList());
        Page<ProductDetailsVO> productDetailsVoPage = new Page<>(current, size, productDetailsPage.getTotal());
        productDetailsVoPage.setRecords(productDetailsVOList);

        return ResultUtils.success(productDetailsVoPage);
    }
}
