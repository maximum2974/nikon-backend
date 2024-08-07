package com.maximum.nikonbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.dto.user
 * @Author: maximum
 * @CreateTime: 2024-06-15
 * @Version: 1.0
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3866966212590461419L;

    private String userAccount;
    private String userPassword;
}
