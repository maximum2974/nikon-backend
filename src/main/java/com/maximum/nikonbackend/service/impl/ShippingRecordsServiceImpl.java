package com.maximum.nikonbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.mapper.InventoryMapper;
import com.maximum.nikonbackend.mapper.ShippingRecordsMapper;
import com.maximum.nikonbackend.model.dto.shippingRecords.PurchaseRequest;
import com.maximum.nikonbackend.model.entity.Inventory;
import com.maximum.nikonbackend.model.entity.ProductDetails;
import com.maximum.nikonbackend.model.entity.ShippingRecords;
import com.maximum.nikonbackend.service.ProductDetailsService;
import com.maximum.nikonbackend.service.ShippingRecordsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ShippingRecordsServiceImpl extends ServiceImpl<ShippingRecordsMapper, ShippingRecords>
    implements ShippingRecordsService {
    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private ShippingRecordsMapper shippingRecordsMapper;

    @Override
    @Transactional
    public boolean purchaseProduct(PurchaseRequest purchaseRequest, Long uid) {
        String productUuid = purchaseRequest.getProductUuid();
        Integer quantity = purchaseRequest.getQuantity();
        String creditCardNumber = purchaseRequest.getCreditCardNumber();
        String email = purchaseRequest.getEmail();
        String shippingAddress = purchaseRequest.getShippingAddress();
        if(StringUtils.isAnyBlank(productUuid, creditCardNumber, email, shippingAddress)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "parameter is empty");
        }
        if(quantity == null || quantity <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "quantity is wrong");
        }
        String creditCardRegex = "^[1-9][0-9]{12,15}$";
        Pattern cardPattern = Pattern.compile(creditCardRegex);
        Matcher cardMatcher = cardPattern.matcher(creditCardNumber);
        if (!cardMatcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "invalid credit card number format");
        }
        String emailRegex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "invalid email format");
        }
        ProductDetails productDetails = productDetailsService.getProductDetailsByUuid(productUuid);
        if(productDetails == null || productDetails.getIsDiscontinued() == 1){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        Long productId = productDetails.getId();
        QueryWrapper<Inventory> inventoryQueryWrapper = new QueryWrapper<>();
        inventoryQueryWrapper.eq("product_id", productId);
        Inventory inventory = inventoryMapper.selectOne(inventoryQueryWrapper);
        if(inventory.getQuantity() < quantity){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        UpdateWrapper<Inventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("product_id", productId);
        inventory.setQuantity(inventory.getQuantity() - quantity);
        int updateResult = inventoryMapper.update(inventory, updateWrapper);
        if(updateResult != 1){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        ShippingRecords shippingRecords = new ShippingRecords();
        UUID uuid = UUID.randomUUID();
        shippingRecords.setUuid(uuid.toString());
        shippingRecords.setProductId(productDetails.getId());
        shippingRecords.setUserId(uid);
        shippingRecords.setQuantity(quantity);
        shippingRecords.setCreditCardNumber(creditCardNumber);
        shippingRecords.setEmail(email);
        shippingRecords.setShippingAddress(shippingAddress);
        int insertResult = shippingRecordsMapper.insert(shippingRecords);
        if(insertResult != 1){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return true;
    }
}




