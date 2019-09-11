package com.sc.community.dto;

import com.sc.community.model.User;
import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 12:572019/9/11
 * @Description:
 */
@Data
public class NoticeDTO {
    private Integer id;
    private Integer notifier;
    private Long gmtCreate;
    private Integer status;
    private String type;
    private String outerTitle;
    private User user;
    private Integer outerId;
}
