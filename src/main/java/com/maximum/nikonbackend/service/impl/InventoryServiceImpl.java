package com.maximum.nikonbackend.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maximum.nikonbackend.mapper.InventoryMapper;
import com.maximum.nikonbackend.model.entity.Inventory;
import com.maximum.nikonbackend.service.InventoryService;
import org.springframework.stereotype.Service;

/**
* @author 12911
* @description 针对表【inventory】的数据库操作Service实现
* @createDate 2024-06-16 15:14:06
*/
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory>
    implements InventoryService {

}




