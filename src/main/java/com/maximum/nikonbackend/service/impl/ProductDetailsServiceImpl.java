package com.maximum.nikonbackend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.mapper.InventoryMapper;
import com.maximum.nikonbackend.mapper.ProductDetailsMapper;
import com.maximum.nikonbackend.model.dto.productDetails.ProductDetailsUpdateRequest;
import com.maximum.nikonbackend.model.entity.Inventory;
import com.maximum.nikonbackend.model.entity.ProductDetails;
import com.maximum.nikonbackend.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class ProductDetailsServiceImpl extends ServiceImpl<ProductDetailsMapper, ProductDetails>
    implements ProductDetailsService {

    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    @Transactional
    public boolean addProductDetails(String productName, String productDescription, BigDecimal productPrice, Integer quantity, String productUrl) {
        ProductDetails productDetails = new ProductDetails();
        String uuid = UUID.randomUUID().toString();
        synchronized (uuid.intern()){
            productDetails.setProductName(productName);
            productDetails.setProductDescription(productDescription);
            productDetails.setProductPrice(productPrice);
            productDetails.setUuid(uuid);
            productDetails.setProductUrl(productUrl);
            int result = productDetailsMapper.insert(productDetails);
            if(result != 1){
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
            QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uuid", uuid);
            ProductDetails details = productDetailsMapper.selectOne(queryWrapper);
            if (details == null) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
            Long productId = details.getId();
            Inventory inventory = new Inventory();
            inventory.setProductId(productId);
            inventory.setQuantity(quantity);
            int insert = inventoryMapper.insert(inventory);
            return insert == 1;
        }
    }

    @Override
    @Transactional
    public Boolean updateProductDetails(ProductDetailsUpdateRequest detailsUpdateRequest) {
        String uuid = detailsUpdateRequest.getUuid();
        synchronized (uuid.intern()){
            QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uuid", uuid);
            ProductDetails details = productDetailsMapper.selectOne(queryWrapper);
            if(details == null){
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
            Long productId = details.getId();
            UpdateWrapper<ProductDetails> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", productId);
            boolean hasUpdates = false;
            String productName = detailsUpdateRequest.getProductName();
            if (productName != null) {
                updateWrapper.set("product_name", productName);
                hasUpdates = true;
            }
            String productDescription = detailsUpdateRequest.getProductDescription();
            if (productDescription != null) {
                updateWrapper.set("product_description", productDescription);
                hasUpdates = true;
            }
            BigDecimal productPrice = detailsUpdateRequest.getProductPrice();
            if (productPrice != null) {
                if (productPrice.compareTo(BigDecimal.ZERO) > 0) {
                    updateWrapper.set("product_price", productPrice);
                    hasUpdates = true;
                } else {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "price cannot be less than or equal to 0");
                }
            }
            if (hasUpdates) {
                int result = productDetailsMapper.update(null, updateWrapper);
                if (result != 1) {
                    throw new BusinessException(ErrorCode.OPERATION_ERROR);
                }
            }
            Integer quantity = detailsUpdateRequest.getQuantity();
            if (quantity != null) {
                if (quantity < 0) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "quantity cannot be less than 0");
                } else {
                    UpdateWrapper<Inventory> inventoryUpdateWrapper = new UpdateWrapper<>();
                    inventoryUpdateWrapper.eq("product_id", productId);
                    inventoryUpdateWrapper.set("quantity", quantity);
                    int inventoryResult = inventoryMapper.update(null, inventoryUpdateWrapper);
                    if (inventoryResult != 1) {
                        throw new BusinessException(ErrorCode.OPERATION_ERROR);
                    }
                }
            }
            return true;
        }
    }

    @Override
    public ProductDetails getProductDetailsByUuid(String uuid) {
        if(uuid == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<ProductDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        ProductDetails productDetails = productDetailsMapper.selectOne(queryWrapper);
        if(productDetails == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return productDetails;
    }

    @Override
    public ProductDetails getProductDetailsById(Long productId) {
        return productDetailsMapper.selectById(productId);
    }
}




