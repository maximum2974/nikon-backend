package com.maximum.nikonbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maximum.nikonbackend.model.entity.UploadImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadMapper extends BaseMapper<UploadImage> {
}
