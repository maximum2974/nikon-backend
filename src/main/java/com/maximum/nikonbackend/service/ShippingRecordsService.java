package com.maximum.nikonbackend.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maximum.nikonbackend.model.dto.shippingRecords.PurchaseRequest;
import com.maximum.nikonbackend.model.entity.ShippingRecords;

public interface ShippingRecordsService extends IService<ShippingRecords> {

    /**
     * purchase products
     * @param purchaseRequest
     * @param uid
     * @return
     */
    boolean purchaseProduct(PurchaseRequest purchaseRequest, Long uid);

    /**
     *
     * @param id
     * @param current
     * @param size
     * @return
     */
    Page<ShippingRecords> getUserShippingRecords(Long id, long current, long size);
}
