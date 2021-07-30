package spring.dto.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacebookResponseDto {

    @JsonProperty("access_token")
    private String accessToken;


    public FacebookResponseDto() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "FacebookResponseDto{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
