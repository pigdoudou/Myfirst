package com.sc.community.enums;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 15:592019/9/6
 * @Description:
 */
public enum CommentTypeEnum{
    QUESTION(1),
    COMMENT(2)
    ;
    private Integer type;

    public static boolean isExist(Integer type) {
        for(CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()){
            if(commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
