package com.sc.community.controller;

import com.sc.community.mapper.QuestionMapper;
import com.sc.community.model.Question;
import com.sc.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: An
 * @Date: Created in 18:552019/8/27
 * @Description:
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model
    ) {
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        } else if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        } else if (description == null || description == "") {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        } else {
            model.addAttribute("tag", tag);
            model.addAttribute("description", description);
            model.addAttribute("title", title);
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                model.addAttribute("error", "用户未登录");
                return "publish";
            } else {
                Question question = new Question();
                question.setTitle(title);
                question.setTag(tag);
                question.setDescription(description);
                question.setCreator(user.getId());
                question.setGmtCreate(System.currentTimeMillis());
                question.setGmtModified(question.getGmtCreate());
                questionMapper.create(question);
                return "redirect:";
            }
        }
    }
}
