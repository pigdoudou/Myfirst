package com.sc.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * @Auther: An
 * @Date: Created in 20:092019/9/5
 * @Description:
 */
@Controller
@RequestMapping("/error")
public class CustomizeErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = {"text/html"})
    public ModelAndView errorHtml(HttpServletRequest request,
                                  Model model) {
        HttpStatus status = this.getStatus(request);
        if(status.is4xxClientError()){
            model.addAttribute("message","手滑了吧小伙汁，重新试一试");
        }
        if(status.is5xxServerError()){
            model.addAttribute("message","不好意思，我的锅");
        }
        return new ModelAndView("error");
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
}
