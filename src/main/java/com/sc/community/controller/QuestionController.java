package com.sc.community.controller;

import com.sc.community.dto.QuestionDTO;
import com.sc.community.mapper.QuestionMapper;
import com.sc.community.service.QuestionService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: An
 * @Date: Created in 10:252019/9/2
 * @Description:
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.findById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
