package com.sc.community.dto;

import com.sc.community.model.User;
import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 18:092019/9/7
 * @Description:
 */
@Data
public class ReplyDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentId;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private Integer replyCount;
    private User user;
}
