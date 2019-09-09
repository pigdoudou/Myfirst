package com.sc.community.service;

import com.sc.community.dto.ReplyDTO;
import com.sc.community.enums.CommentTypeEnum;
import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.exception.CustomizeException;
import com.sc.community.mapper.CommentMapper;
import com.sc.community.mapper.QuestionMapper;
import com.sc.community.mapper.UserMapper;
import com.sc.community.model.Comment;
import com.sc.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 16:052019/9/6
 * @Description:
 */
@Service
public class CommentService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insertC(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_POST);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_ERROR);
        }
        if(comment.getContent()==null || StringUtils.isBlank(comment.getContent())){
            throw new CustomizeException(CustomizeErrorCode.CONTENT_NULL);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            if (commentMapper.findByParentId(comment.getParentId()) == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_REPLY_ERROR);
            } else {
                commentMapper.insertC(comment);
            }
        } else {
            if (questionMapper.findById(comment.getParentId()) == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            } else {
                commentMapper.insertC(comment);
                questionMapper.updateQuestionCommentCount(comment);
            }
        }
    }

    public List<ReplyDTO> findByParentId(Integer id) {
        List<Comment> comment = commentMapper.findByParentId(id);
        List<ReplyDTO> replyDTOList=new ArrayList<>();
        for(Comment comments:comment){
            ReplyDTO replyDTO=new ReplyDTO();
            BeanUtils.copyProperties(comments,replyDTO);
            Integer creator = questionMapper.findById(id).getCreator();
            User user = userMapper.findById(creator);
            replyDTO.setUser(user);
            replyDTOList.add(replyDTO);
        }
        return replyDTOList;
    }

    @Transactional
    public void insertR(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_POST);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_ERROR);
        }
        if(comment.getContent()==null || StringUtils.isBlank(comment.getContent())){
            throw new CustomizeException(CustomizeErrorCode.CONTENT_NULL);
        }
            if (commentMapper.existByParentId(comment.getParentId())==false) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_REPLY_ERROR);
            } else {
                commentMapper.replyCount(comment.getParentId());
                commentMapper.insertC(comment);
            }

    }

    public List<ReplyDTO> findByParentId2(Integer parentId) {
           List<Comment> commentList= commentMapper.findByParentId2(parentId);
           List<ReplyDTO> replyDTOList=new ArrayList<>();
           for(Comment comment:commentList){
               ReplyDTO replyDTO=new ReplyDTO();
               BeanUtils.copyProperties(comment,replyDTO);
               User user = userMapper.findById(comment.getCommentId());
               replyDTO.setUser(user);
               replyDTOList.add(replyDTO);
           }
        return replyDTOList;
    }
}
