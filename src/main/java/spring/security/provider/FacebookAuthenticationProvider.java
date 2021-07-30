package spring.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;
import spring.dto.facebook.FacebookResponseDto;
import spring.dto.facebook.FacebookUserDto;
import spring.dto.gmail.GoogleAccessTokenResponse;
import spring.dto.gmail.GoogleUserInfoResponse;
import spring.entity.UserEntity;
import spring.security.authentication.OAuthCustomAuthentication;
import spring.security.model.UserRole;
import spring.security.model.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FacebookAuthenticationProvider implements AuthenticationProvider {

    private RestTemplate template;

    private final String accessTokenUrl  = "https://graph.facebook.com/v11.0/oauth/access_token?client_id=231495668836527&client_secret=5a28cff3249e3d7ff50ecb12b85e64c7&redirect_uri=http://localhost:8000/social-login&code=";
    private final String userInfoUrl = "https://graph.facebook.com/me?fields=email,name,picture&access_token=";


    @Autowired
    public FacebookAuthenticationProvider(RestTemplate template) {
        this.template = template;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String code = (String) authentication.getPrincipal();


        try {

            ResponseEntity<FacebookResponseDto> tokenResponse = template.exchange(accessTokenUrl+code, HttpMethod.GET,null,FacebookResponseDto.class);

            ResponseEntity<FacebookUserDto> userInfoResponse = template.exchange(userInfoUrl+tokenResponse.getBody().getAccessToken(),HttpMethod.GET,null,FacebookUserDto.class);

            UserEntity userEntity = new UserEntity();
            userEntity.setPicUrl(userInfoResponse.getBody().getPicture().getData().getUrl());
            userEntity.setUsername(userInfoResponse.getBody().getName());
            userEntity.setFullName(userInfoResponse.getBody().getName());
            userEntity.setEmail(userInfoResponse.getBody().getEmail());
            userEntity.setUserRole(UserRole.USER);
            userEntity.setUserType(UserType.FACEBOOK);

            return new OAuthCustomAuthentication(userEntity,null,new ArrayList<>());


        }catch (Exception e){
            throw new BadCredentialsException("INVALID CREDENTIALS ");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OAuthCustomAuthentication.class.equals(aClass);
    }
}
