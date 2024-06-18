package com.maximum.nikonbackend.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.BaseResponse;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.common.ResultUtils;
import com.maximum.nikonbackend.constant.UserConstant;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.dto.shippingRecords.PurchaseRequest;
import com.maximum.nikonbackend.model.entity.User;
import com.maximum.nikonbackend.service.InventoryService;
import com.maximum.nikonbackend.service.ProductDetailsService;
import com.maximum.nikonbackend.service.ShippingRecordsService;
import com.maximum.nikonbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.controller
 * @Author: maximum
 * @CreateTime: 2024-06-16
 * @Version: 1.0
 */

@RestController
@RequestMapping("/shipping")
@Slf4j
public class ShippingRecordsController {
    @Autowired
    private ProductDetailsService productDetailsService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ShippingRecordsService shippingRecordsService;
    @Autowired
    private UserService userService;

    @PostMapping("/purchase")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Boolean> purchaseProduct(@RequestBody PurchaseRequest purchaseRequest, HttpServletRequest request){
        if(purchaseRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        Long uid = user.getId();
        boolean result = shippingRecordsService.purchaseProduct(purchaseRequest, uid);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }
}
