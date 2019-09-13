package com.sc.community.service;

import com.sc.community.dto.ReplyDTO;
import com.sc.community.enums.CommentTypeEnum;
import com.sc.community.enums.NoticeStatusEnum;
import com.sc.community.enums.NoticeTypeEnum;
import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.exception.CustomizeException;
import com.sc.community.mapper.CommentMapper;
import com.sc.community.mapper.NoticeMapper;
import com.sc.community.mapper.QuestionMapper;
import com.sc.community.mapper.UserMapper;
import com.sc.community.model.Comment;
import com.sc.community.model.Notice;
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

    @Autowired
    private NoticeMapper noticeMapper;


    //插入一级回复
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
        //判断是否二级回复
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            if (commentMapper.findByParentId(comment.getParentId()) == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_REPLY_ERROR);
            } else {
                commentMapper.insertC(comment);
                questionMapper.updateQuestionCommentCount(comment);
            }
        } else {
            if (questionMapper.findById(comment.getParentId()) == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            } else {
                //插入回复
                commentMapper.insertC(comment);
                //回复数更新
                questionMapper.updateQuestionCommentCount(comment);
                //新增回复通知
                Notice notice=new Notice();
                notice.setGmtCreate(System.currentTimeMillis());
                notice.setType(NoticeTypeEnum.REPLY_QUESTION.getName());
                notice.setNotifier(comment.getCommentId());
                notice.setOuterId(comment.getParentId());
                notice.setReceiver(questionMapper.findById(comment.getParentId()).getCreator());
                notice.setStatus(NoticeStatusEnum.UNREAD.getStatus());
                notice.setOuterTitle(questionMapper.findById(comment.getParentId()).getTitle());
                noticeMapper.insert(notice);
            }
        }
    }

    public List<ReplyDTO> findByParentId(Integer id) {
        List<Comment> comment = commentMapper.findByParentId(id);
        List<ReplyDTO> replyDTOList=new ArrayList<>();
        for(Comment comments:comment){
            ReplyDTO replyDTO=new ReplyDTO();
            BeanUtils.copyProperties(comments,replyDTO);
            User user=userMapper.findById(comments.getCommentId());
            replyDTO.setUser(user);
            replyDTOList.add(replyDTO);
        }
        return replyDTOList;
    }


    //插入二级回复
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
                //评论数更新
                commentMapper.replyCount(comment.getParentId());
                //回复数更新
                Comment comment1= commentMapper.findByParentId3(comment.getParentId());
                questionMapper.updateQuestionCommentCount(comment1);
                //插入二级回复
                commentMapper.insertC(comment);
                //新增回复通知
                Notice notice=new Notice();
                notice.setGmtCreate(System.currentTimeMillis());
                notice.setType(NoticeTypeEnum.REPLY_COMMENT.getName());
                notice.setNotifier(comment.getCommentId());
                notice.setOuterId(commentMapper.findByParentId3(comment.getParentId()).getParentId());
                notice.setReceiver(commentMapper.findByParentId3(comment.getParentId()).getCommentId());
                notice.setStatus(NoticeStatusEnum.UNREAD.getStatus());
                notice.setOuterTitle(questionMapper.findById(notice.getOuterId()).getTitle());
                noticeMapper.insert(notice);
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
