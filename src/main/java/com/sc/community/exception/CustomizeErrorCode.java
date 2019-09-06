package com.sc.community.exception;

/**
 * @Auther: An
 * @Date: Created in 19:312019/9/5
 * @Description:
 */
public enum CustomizeErrorCode implements IcustomizeErrorCode{

    QUESTION_NOT_FOUND("你要找的问题不在了，要不换一个试试~",1000),
    COMMENT_NOT_POST("你要评论的问题不在了",1001),
    NO_LOGIN("请先登录后评论",1002),
    SYS_ERROR("服务器出错",1003),
    COMMENT_TYPE_ERROR("评论类型错误",1004),
    COMMENT_REPLY_ERROR("回复的评论不存在",1005);

    private String message;
    private Integer code;

    CustomizeErrorCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
