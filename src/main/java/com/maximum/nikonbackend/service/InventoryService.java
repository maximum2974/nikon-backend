package com.maximum.nikonbackend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.maximum.nikonbackend.model.entity.Inventory;

import java.util.List;
import java.util.Map;

public interface InventoryService extends IService<Inventory> {
    Map<Long, Integer> getQuantitiesByProductIds(List<Long> productIds);
}
