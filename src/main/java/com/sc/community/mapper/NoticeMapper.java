package com.sc.community.mapper;

import com.sc.community.dto.NoticeDTO;
import com.sc.community.model.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: An
 * @Date: Created in 21:112019/9/10
 * @Description:
 */
public interface NoticeMapper {

    @Insert("insert into notice(notifier,receiver,outer_id,type,gmt_create,status,outer_title) values(#{notifier},#{receiver},#{outerId},#{type},#{gmtCreate},#{status},#{outerTitle})")
    void insert(Notice notice);

    @Select("select count(1) from notice where receiver = #{id}")
    Integer myCount(Integer id);

    @Select("select * from notice where receiver=#{id} order by gmt_create desc limit #{offset},#{size}")
    List<Notice> myNoticeList(@Param("id")Integer id,@Param("offset")Integer offset, @Param("size")Integer size);
}
