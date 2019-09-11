package com.sc.community.model;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 21:072019/9/10
 * @Description:
 */
@Data
public class Notice {
    private Integer id;
    private Integer notifier;
    private Integer receiver;
    private Integer outerId;
    private String type;
    private Long gmtCreate;
    private Integer status;
    private String outerTitle;
}
