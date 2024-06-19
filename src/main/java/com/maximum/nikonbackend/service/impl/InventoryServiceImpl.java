package com.maximum.nikonbackend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maximum.nikonbackend.mapper.InventoryMapper;
import com.maximum.nikonbackend.model.entity.Inventory;
import com.maximum.nikonbackend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory>
    implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public Map<Long, Integer> getQuantitiesByProductIds(List<Long> productIds) {
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);
        List<Inventory> inventories = inventoryMapper.selectList(queryWrapper);
        return inventories.stream()
                .collect(Collectors.toMap(Inventory::getProductId, Inventory::getQuantity));
    }
}




