package spring.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubResponseDto {

    @JsonProperty("access_token")
    private String accessToken;

    public GitHubResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public GitHubResponseDto() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "GitHubResponseDto{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
