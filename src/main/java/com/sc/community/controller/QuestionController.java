package com.sc.community.controller;

import com.sc.community.dto.CommentDTO;
import com.sc.community.dto.QuestionDTO;
import com.sc.community.dto.ReplyDTO;
import com.sc.community.service.CommentService;
import com.sc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 10:252019/9/2
 * @Description:
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.findById(id);
        questionService.viewCount(id);
        List<ReplyDTO> replies= commentService.findByParentId(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",replies);
        return "question";
    }
}
