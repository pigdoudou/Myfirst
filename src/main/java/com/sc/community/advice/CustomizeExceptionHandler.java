package com.sc.community.advice;

import com.alibaba.fastjson.JSON;
import com.sc.community.dto.ResultDTO;
import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: An
 * @Date: Created in 10:562019/9/3
 * @Description:
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handler(HttpServletRequest request, Throwable ex, Model model,
                   HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            if (ex instanceof CustomizeException) {
                resultDTO= ResultDTO.errorOf((CustomizeException) ex);
            } else {
                resultDTO=ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSON.toJSONString(resultDTO));
                printWriter.close();
            } catch (IOException e) {
            }
            return null;
        } else {

            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

}
