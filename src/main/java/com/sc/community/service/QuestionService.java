package com.sc.community.service;

import com.sc.community.exception.CustomizeErrorCode;
import com.sc.community.exception.CustomizeException;
import com.sc.community.dto.PaginationDTO;
import com.sc.community.dto.QuestionDTO;
import com.sc.community.mapper.QuestionMapper;
import com.sc.community.mapper.UserMapper;
import com.sc.community.model.Question;
import com.sc.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: An
 * @Date: Created in 12:032019/8/29
 * @Description:
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO questionList(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1 || paginationDTO.getTotalPage() == 0) {
            page = 1;
        } else if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.questionList(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        paginationDTO.setPage(page);
        return paginationDTO;
    }

    public PaginationDTO myList(Integer id, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.myCount(id);
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1 || paginationDTO.getTotalPage() == 0) {
            page = 1;
        } else if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.myQuestionList(id, offset, size);
        if(questionList.size()==0){
            return paginationDTO;
        }
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        User user = userMapper.findById(id);
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        paginationDTO.setPage(page);
        return paginationDTO;
    }

    public QuestionDTO findById(Integer id) {
        Question question = questionMapper.findById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        Question questionExists = questionMapper.findById(question.getId());
        if (questionExists != null) {
            questionMapper.updateQuestion(question);
        } else {
            questionMapper.create(question);
        }
    }

    public void viewCount(Integer id) {
        Question question = new Question();
        question.setId(id);
        questionMapper.updateQuestionViewCount(question);
    }

    public Set<Integer> findByTag(String tags) {
        String tag[] = tags.split(",");
        Set<Integer> idList = new HashSet<>();
        for (String tages : tag) {
            List<Integer> id = questionMapper.findByTag(tages);

                for (Integer ids : id) {
                    idList.add(ids);
                }
        }
        return idList;
    }

    public List<Question> relatedQuestion(Set<Integer> tagId) {
        List<Question> questionList = new ArrayList<>();
        for (Integer id : tagId) {
            questionList.add(questionMapper.findById(id));
        }
        return questionList;
    }

    public PaginationDTO search(String search, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.searchCount(search);
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1 || paginationDTO.getTotalPage() == 0) {
            page = 1;
        } else if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.search(search,offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        paginationDTO.setPage(page);
        return paginationDTO;
    }
}
