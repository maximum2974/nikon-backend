package com.maximum.nikonbackend.common;

public enum ErrorCode {
    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "request parameter error"),
    NOT_LOGIN_ERROR(40100, "not login"),
    NO_AUTH_ERROR(40101, "no auth"),
    NOT_FOUND_ERROR(40400, "request data does not exist"),
    FORBIDDEN_ERROR(40300, "access forbidden"),
    SYSTEM_ERROR(50000, "system internal exception"),
    OPERATION_ERROR(50001, "operation failure");

    private final int code;
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
