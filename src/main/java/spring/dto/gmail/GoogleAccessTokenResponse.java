package spring.dto.gmail;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    public GoogleAccessTokenResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "GoogleAccessTokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
