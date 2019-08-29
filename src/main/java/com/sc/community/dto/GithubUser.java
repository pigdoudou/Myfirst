package com.sc.community.dto;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 16:512019/8/25
 * @Description:
 */
@Data
public class GithubUser {
    private String login;
    private Long id;
    private String bio;
    private String avatar_url;
}
