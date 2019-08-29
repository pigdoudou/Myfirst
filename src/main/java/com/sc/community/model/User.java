package com.sc.community.model;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 10:292019/8/27
 * @Description:
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
