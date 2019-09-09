package com.sc.community.mapper;

import com.sc.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 15:272019/9/6
 * @Description:
 */
public interface CommentMapper {

    @Insert("insert into comment(parent_id,comment_id,type,gmt_create,gmt_modified,content) values(#{parentId},#{commentId},#{type},#{gmtCreate},#{gmtModified},#{content})")
    void insertC(Comment comment);


    @Select("select * from comment where parent_id=#{id} and type = 1 order by gmt_create desc")
    List<Comment> findByParentId(Integer id);

    @Select("select * from comment where id=#{parentId}")
    Boolean existByParentId(Integer parentId);

    @Select("select * from comment where parent_id=#{parentId} and type=2 order by gmt_create asc")
    List<Comment> findByParentId2(Integer parentId);

    @Update("update comment set reply_count=reply_count+1 where id=#{parentId}")
    void replyCount(Integer parentId);
}
