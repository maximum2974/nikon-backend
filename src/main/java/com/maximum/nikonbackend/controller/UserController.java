package com.maximum.nikonbackend.controller;

import com.maximum.nikonbackend.annotation.AuthCheck;
import com.maximum.nikonbackend.common.BaseResponse;
import com.maximum.nikonbackend.common.ErrorCode;
import com.maximum.nikonbackend.common.GithubUploaderUtils;
import com.maximum.nikonbackend.common.ResultUtils;
import com.maximum.nikonbackend.constant.UserConstant;
import com.maximum.nikonbackend.exception.BusinessException;
import com.maximum.nikonbackend.model.dto.user.UserLoginRequest;
import com.maximum.nikonbackend.model.dto.user.UserRegisterRequest;
import com.maximum.nikonbackend.model.dto.user.UserUpdateInfoRequest;
import com.maximum.nikonbackend.model.entity.User;
import com.maximum.nikonbackend.model.vo.UserVO;
import com.maximum.nikonbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.controller
 * @Author: maximum
 * @CreateTime: 2024-06-15
 * @Version: 1.0
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GithubUploaderUtils githubUploaderUtils;

    @PostMapping("register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = userRegisterRequest.getUserName();
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(username, userAccount, userPassword, checkPassword)){
            return null;
        }
        long result = userService.userRegister(username, userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<UserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest,
                                        HttpServletRequest request){
        if(userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request){
        User user = userService.getLoginUser(request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request){
        if(request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }


    @PostMapping("/update")
    @AuthCheck(anyRole = {UserConstant.DEFAULT_ROLE, UserConstant.ADMIN_ROLE})
    public BaseResponse<Boolean> updateUserInfo(@RequestBody UserUpdateInfoRequest userUpdateInfoRequest,
                                                 HttpServletRequest request) throws IOException {
        String userName = userUpdateInfoRequest.getUserName();
        String userAvatar = userUpdateInfoRequest.getUserAvatar();
        Integer gender = userUpdateInfoRequest.getGender();
        User user = userService.getLoginUser(request);
        if (userName != null) {
            if (userName.length() > 4) {
                user.setUserName(userName);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "user name is too short");
            }
        }
        if(userAvatar != null){
            user.setUserAvatar(userAvatar);
        }
        if(gender != null){
            if(gender != 1 && gender != 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            user.setGender(gender);
        }
        boolean result = userService.updateById(user);
        if(!result){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }
}
