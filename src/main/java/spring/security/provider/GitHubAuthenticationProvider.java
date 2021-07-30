package spring.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import spring.dto.github.GitHubEmailsResponseDto;
import spring.dto.github.GitHubResponseDto;
import spring.dto.github.GitHubUserDto;
import spring.entity.UserEntity;
import spring.security.authentication.OAuthCustomAuthentication;
import spring.security.model.UserRole;
import spring.security.model.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GitHubAuthenticationProvider implements AuthenticationProvider {

    private final RestTemplate template;


    private final String accessTokenUrl  = "https://github.com/login/oauth/access_token?client_id=717b8d2929d1a2a42222&client_secret=2e769915a83cca47981e093574ce05fb60c7bbb7&scope=user:email&code=";
    private final String userInfoUrl = "https://api.github.com/user";



    @Autowired
    public GitHubAuthenticationProvider(RestTemplate template) {
        this.template = template;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String code = (String) authentication.getPrincipal();

        String token="";

        GitHubResponseDto response = template.getForObject(accessTokenUrl+code, GitHubResponseDto.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","token "+token+ Objects.requireNonNull(response).getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {

            ResponseEntity<GitHubUserDto> gitHubUser = template.exchange(userInfoUrl, HttpMethod.GET, entity, GitHubUserDto.class);

            ResponseEntity<GitHubEmailsResponseDto[]> gitHubEmails = template.exchange(userInfoUrl+"/emails", HttpMethod.GET, entity, GitHubEmailsResponseDto[].class);

            GitHubEmailsResponseDto email = Arrays.stream(Objects.requireNonNull(gitHubEmails.getBody())).filter(GitHubEmailsResponseDto::isPrimary).findFirst().orElse(null);

            UserEntity userEntity = new UserEntity();
            userEntity.setPicUrl(gitHubUser.getBody().getPicture());
            userEntity.setUsername(gitHubUser.getBody().getUsername());
            userEntity.setFullName(gitHubUser.getBody().getUsername());
            userEntity.setEmail(email.getEmail());
            userEntity.setUserRole(UserRole.USER);
            userEntity.setUserType(UserType.GITHUB);


            return new OAuthCustomAuthentication(userEntity,null,new ArrayList<>());


        }catch (HttpClientErrorException s){
            throw new BadCredentialsException("BAD CREDENTIALS");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OAuthCustomAuthentication.class.equals(aClass);
    }
}
