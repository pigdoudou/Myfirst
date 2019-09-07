package com.sc.community.mapper;

import com.sc.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 15:272019/9/6
 * @Description:
 */
public interface CommentMapper {

    @Insert("insert into comment(parent_id,comment_id,type,gmt_create,gmt_modified,content) values(#{parentId},#{commentId},#{type},#{gmtCreate},#{gmtModified},#{content})")
    public void insertC(Comment comment);

    @Select("select * from comment where parent_id=#{id}")
    List<Comment> findByParentId(Integer id);
}
