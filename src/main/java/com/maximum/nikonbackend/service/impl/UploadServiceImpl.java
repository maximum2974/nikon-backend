package com.maximum.nikonbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maximum.nikonbackend.mapper.UploadMapper;
import com.maximum.nikonbackend.model.entity.UploadImage;
import com.maximum.nikonbackend.service.UploadService;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl extends ServiceImpl<UploadMapper, UploadImage> implements UploadService {
}
