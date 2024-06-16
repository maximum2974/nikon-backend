package com.maximum.nikonbackend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.maximum.nikonbackend.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    /**
     * user register
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String username, String userAccount, String userPassword, String checkPassword);

    /**
     * user login
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * get the currently logged in user
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * user logout
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * is admin
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);
}
