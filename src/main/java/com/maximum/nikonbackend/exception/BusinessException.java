package com.maximum.nikonbackend.exception;

import com.maximum.nikonbackend.common.ErrorCode;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.exception
 * @Author: maximum
 * @CreateTime: 2024-06-15
 * @Version: 1.0
 */

public class BusinessException extends RuntimeException{
    private final int code;

    public BusinessException(int code, String message){
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message){
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode(){
        return code;
    }
}
