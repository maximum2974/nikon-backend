package com.maximum.nikonbackend.common;

/**
 * @BelongsProject: nikon-backend
 * @BelongsPackage: com.maximum.nikonbackend.common
 * @Author: maximum
 * @CreateTime: 2024-06-15
 * @Version: 1.0
 */

public class ResultUtils {
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok");
    }

    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(int code, String message){
        return new BaseResponse(code, null, message);
    }

    public static BaseResponse error(ErrorCode errorCode, String message){
        return new BaseResponse(errorCode.getCode(), null, message);
    }
}
