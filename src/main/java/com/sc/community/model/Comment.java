package com.sc.community.model;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 15:272019/9/6
 * @Description:
 */
@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentId;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
}
