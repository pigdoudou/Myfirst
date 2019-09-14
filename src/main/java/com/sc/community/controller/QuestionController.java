package com.sc.community.controller;

import com.sc.community.dto.QuestionDTO;
import com.sc.community.dto.ReplyDTO;
import com.sc.community.model.Question;
import com.sc.community.service.CommentService;
import com.sc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

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
                           Model model) {
        //查询问题以及user信息
        QuestionDTO questionDTO = questionService.findById(id);
        //通过标签查询相关话题
        Set<Integer> tagId = questionService.findByTag(questionDTO.getTag());
            //相关话题去除当前问题
            tagId.remove(id);
            if (tagId.isEmpty()) {
                model.addAttribute("message","暂无相关问题");
            }else {
            //获取相关问题信息
            List<Question> relatedQuestion = questionService.relatedQuestion(tagId);
            model.addAttribute("related", relatedQuestion);}
        //浏览数
        questionService.viewCount(id);
        //查询一级回复
        List<ReplyDTO> replies = commentService.findByParentId(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", replies);
        return "question";
    }
}
