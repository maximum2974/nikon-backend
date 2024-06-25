package com.maximum.nikonbackend.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maximum.nikonbackend.mapper.FeedbackMapper;
import com.maximum.nikonbackend.model.entity.Feedback;
import com.maximum.nikonbackend.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback>
    implements FeedbackService {

}




