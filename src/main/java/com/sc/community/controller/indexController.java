package com.sc.community.controller;

import com.sc.community.dto.PaginationDTO;
import com.sc.community.dto.QuestionDTO;
import com.sc.community.mapper.QuestionMapper;
import com.sc.community.mapper.UserMapper;
import com.sc.community.model.User;
import com.sc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 11:012019/8/24
 * @Description:
 */
@Controller
public class indexController{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "size",defaultValue = "5")Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }}
        PaginationDTO paginationDTO = questionService.questionList(page,size);
        model.addAttribute("pagination",paginationDTO);
        return "index";
    }
}
