package com.sc.community.dto;

/**
 * @Auther: An
 * @Date: Created in 16:512019/8/25
 * @Description:
 */
public class GithubUser {
    private String login;
    private Long id;
    private String bio;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
