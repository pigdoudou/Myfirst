package com.sc.community.exception;

/**
 * @Auther: An
 * @Date: Created in 19:312019/9/5
 * @Description:
 */
public enum CustomizeErrorCode implements IcustomizeErrorCode{
    QUESTION_NOT_FOUND("你要找的问题不在了，要不换一个试试~");
    private String message;
    public String getMessage(){
        return message;
    }
    CustomizeErrorCode(String message){
        this.message=message;
    }
}
