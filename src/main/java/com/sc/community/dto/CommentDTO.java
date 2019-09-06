package com.sc.community.dto;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 15:242019/9/6
 * @Description:
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
