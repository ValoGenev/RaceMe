package spring.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;
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

public class GoogleAuthenticationProvider implements AuthenticationProvider {

    private RestTemplate template;

    private final String accessTokenUrl  = "https://oauth2.googleapis.com/token?client_id=903094524397-l5ahe7ri356a19p2iej65oagmp35sjud.apps.googleusercontent.com&client_secret=8mjagYmXCRwRCLvzH3lrokeF&grant_type=authorization_code&redirect_uri=http://localhost:8000/social-login&code=";
    private final String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=";


    @Autowired
    public GoogleAuthenticationProvider(RestTemplate template) {
        this.template = template;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String code = (String) authentication.getPrincipal();

        System.out.println("kodut e "+code);


        try {

            ResponseEntity<GoogleAccessTokenResponse> tokenResponse = template.exchange(accessTokenUrl+code,HttpMethod.POST,null,GoogleAccessTokenResponse.class);

            ResponseEntity<GoogleUserInfoResponse> userInfoResponse = template.exchange(userInfoUrl+tokenResponse.getBody().getAccessToken(),HttpMethod.GET,null,GoogleUserInfoResponse.class);

            UserEntity userEntity = new UserEntity();
            userEntity.setPicUrl(userInfoResponse.getBody().getPicture());
            userEntity.setUsername(userInfoResponse.getBody().getName());
            userEntity.setFullName(userInfoResponse.getBody().getName());
            userEntity.setEmail(userInfoResponse.getBody().getEmail());
            userEntity.setUserRole(UserRole.USER);
            userEntity.setUserType(UserType.GOOGLE);


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
