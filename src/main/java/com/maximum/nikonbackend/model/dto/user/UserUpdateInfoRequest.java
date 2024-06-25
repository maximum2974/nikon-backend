package com.maximum.nikonbackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.model.dto.user
 * @Author: maximum
 * @CreateTime: 2024-06-25
 * @Version: 1.0
 */

@Data
public class UserUpdateInfoRequest implements Serializable {
    private static final long serialVersionUID = 4617325467638658805L;
    private String userName;
    private String userAvatar;
    private Integer gender;

}
