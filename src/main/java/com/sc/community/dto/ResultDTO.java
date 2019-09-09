package com.sc.community.dto;

import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.exception.CustomizeException;
import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 15:502019/9/6
 * @Description:
 */
@Data
public class ResultDTO<T> {
    private String message;
    private Integer code;
    private T data;

    public static ResultDTO errorOf(String message, Integer code) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static<T> ResultDTO successOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(2000);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getMessage(), errorCode.getCode());
    }

    public static ResultDTO successOf() {
        return errorOf("登录成功", 2000);
    }

    public static ResultDTO errorOf(CustomizeException e){
        return errorOf(e.getMessage(),e.getCode());
    }
}
