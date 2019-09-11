package com.sc.community.enums;

/**
 * @Auther: An
 * @Date: Created in 21:562019/9/10
 * @Description:
 */
public enum NoticeStatusEnum {
    UNREAD(0),READ(1);
    private Integer status;
    public Integer getStatus(){
        return status;
    }
    NoticeStatusEnum(Integer status){
        this.status=status;
    }
}
