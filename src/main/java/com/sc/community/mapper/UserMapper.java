package com.sc.community.mapper;

import com.sc.community.model.Question;
import com.sc.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: An
 * @Date: Created in 10:342019/8/27
 * @Description:
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param(value = "token")String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id")Integer id);
}
