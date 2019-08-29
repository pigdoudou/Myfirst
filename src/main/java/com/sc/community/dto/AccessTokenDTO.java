package com.sc.community.dto;

import lombok.Data;

/**
 * @Auther: An
 * @Date: Created in 16:072019/8/25
 * @Description:
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
