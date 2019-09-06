package com.sc.community.exception;

/**
 * @Auther: An
 * @Date: Created in 11:092019/9/3
 * @Description:
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;
    public  CustomizeException(IcustomizeErrorCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
