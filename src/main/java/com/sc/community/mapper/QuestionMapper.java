package com.sc.community.mapper;

import com.sc.community.model.Comment;
import com.sc.community.model.Question;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from question order by gmt_create desc limit #{offset},#{size}")
    List<Question> questionList(@Param("offset")Integer offset,@Param("size")Integer size);

    @Insert("insert into question(description,title,gmt_create,gmt_modified,creator,tag) values(#{description},#{title},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question where creator=#{id} limit #{offset},#{size}")
    List<Question> myQuestionList(@Param("id")Integer id, @Param("offset")Integer offset,@Param("size")Integer size);

    @Select("select count(1) from question where creator=#{id}")
    Integer myCount(Integer id);

    @Select("select * from question where id=#{id}")
    Question findById(Integer id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmtCreate} where id=#{id}")
    void updateQuestion(Question question);

/*
    @Select("select view_count from question where id = #{id}")
    int getViewCount(Integer id);
*/

    @Update("update question set view_count=view_count+1 where id=#{id}")
    void updateQuestionViewCount(Question question);

    @Update("update question set comment_count=comment_count+1 where id=#{parentId}")
    void updateQuestionCommentCount(Comment comment);

    @Select("select id from question where tag like concat(concat('%',#{tags}),'%') ")
    List<Integer> findByTag(String tags);
}
