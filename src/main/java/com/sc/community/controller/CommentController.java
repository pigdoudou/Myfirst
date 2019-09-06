package com.sc.community.controller;

import com.sc.community.dto.CommentDTO;
import com.sc.community.dto.ResultDTO;
import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.exception.CustomizeException;
import com.sc.community.mapper.CommentMapper;
import com.sc.community.model.Comment;
import com.sc.community.model.User;
import com.sc.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: An
 * @Date: Created in 15:202019/9/6
 * @Description:
 */
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment=new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentId(user.getId());
        commentService.insertC(comment);
        return ResultDTO.successOf();
    }
}
