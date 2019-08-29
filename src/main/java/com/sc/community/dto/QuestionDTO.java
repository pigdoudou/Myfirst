package com.sc.community.dto;

import com.sc.community.model.User;
import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 11:562019/8/29
 * @Description:
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String description;
    private String title;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private String tag;
    private User user;
}
