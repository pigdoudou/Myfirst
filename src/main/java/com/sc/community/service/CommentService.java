package com.sc.community.service;

import com.sc.community.enums.CommentTypeEnum;
import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.exception.CustomizeException;
import com.sc.community.mapper.CommentMapper;
import com.sc.community.mapper.QuestionMapper;
import com.sc.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void insertC(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_POST);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_ERROR);
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
}
