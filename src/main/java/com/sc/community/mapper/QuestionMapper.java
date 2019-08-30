package com.sc.community.mapper;

import com.sc.community.dto.QuestionDTO;
import com.sc.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 17:292019/8/28
 * @Description:
 */
@Mapper
public interface QuestionMapper {
    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question limit #{offset},#{size}")
    List<Question> questionList(@Param("offset")Integer offset,@Param("size")Integer size);

    @Insert("insert into question(description,title,gmt_create,gmt_modified,creator,tag) values(#{description},#{title},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
