package com.sc.community.enums;

/**
 * @Auther: An
 * @Date: Created in 21:132019/9/10
 * @Description:
 */
public enum NoticeTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(1,"回复了评论");

    private Integer type;
    private String name;

    public Integer getType(){
        return type;
    }
    public String getName(){
        return name;
    }

    NoticeTypeEnum(Integer type,String name){
        this.type =type;
        this.name=name;
    }
}
