package com.sc.community.controller;

import com.sc.community.dto.CommentDTO;
import com.sc.community.dto.ReplyDTO;
import com.sc.community.dto.ResultDTO;
import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.model.Comment;
import com.sc.community.model.User;
import com.sc.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 15:202019/9/6
 * @Description:
 */
@Controller
public class CommentController {


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

    @ResponseBody
    @PostMapping("/reply")
    public Object replyPost(@RequestBody CommentDTO commentDTO,
                            HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
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
        commentService.insertR(comment);
        return ResultDTO.successOf();
    }

    @ResponseBody
    @RequestMapping("/comment/{id}")
    public Object replyShow(@PathVariable("id")Integer id){
        List<ReplyDTO> replies2=commentService.findByParentId2(id);
        return ResultDTO.successOf(replies2);
    }
}
