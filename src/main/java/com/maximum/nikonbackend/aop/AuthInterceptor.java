package com.maximum.nikonbackend.aop;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.entity.User;
import com.maximum.nikonbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.aop
 * @Author: maximum
 * @CreateTime: 2024-06-15
 * @Version: 1.0
 */
@Aspect
@Component
public class AuthInterceptor {
    @Autowired
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        List<String> anyRole = Arrays.stream(authCheck.anyRole())
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User user = userService.getLoginUser(request);
        if(CollectionUtils.isNotEmpty(anyRole)){
            String userRole = user.getUserRole();
            if(!anyRole.contains(userRole)){
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        if(StringUtils.isNotBlank(mustRole)){
            String userRole = user.getUserRole();
            if(!mustRole.equals(userRole)){
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        return joinPoint.proceed();
    }
}
