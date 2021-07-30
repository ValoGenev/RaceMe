package spring.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubUserDto {

    @JsonProperty("login")
    private String username;

    @JsonProperty("avatar_url")
    private String picture;

    public GitHubUserDto(String username) {
        this.username = username;
    }

    public GitHubUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "GitHubUserDto{" +
                "username='" + username + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
