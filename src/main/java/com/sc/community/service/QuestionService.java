package com.sc.community.service;

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
import java.util.List;

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
        PaginationDTO  paginationDTO = new PaginationDTO();
        Integer totalCount=questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        if(page<1){
            page =1;
        }
        if(page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.questionList(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question:questionList){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPage(page);
        return paginationDTO;
    }
}
