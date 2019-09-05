package com.sc.community.exception;

/**
 * @Auther: An
 * @Date: Created in 11:092019/9/3
 * @Description:
 */
public class CustomizeException extends RuntimeException{
    private String message;
    public  CustomizeException(IcustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
