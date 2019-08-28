package com.sc.community.mapper;

import com.sc.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: An
 * @Date: Created in 17:292019/8/28
 * @Description:
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(description,title,gmt_create,gmt_modified,creator,tag) values(#{description},#{title},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
