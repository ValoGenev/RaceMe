package spring.security.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import spring.repository.IUserRepository;
import spring.security.filters.CustomExceptionFilter;
import spring.security.filters.OAuthFilter;
import spring.security.provider.FacebookAuthenticationProvider;
import spring.security.provider.GitHubAuthenticationProvider;
import spring.security.provider.GoogleAuthenticationProvider;
import spring.security.userdetails.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    IUserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl(userRepository);
    }


    @Bean
    CustomExceptionFilter customExceptionFilter(){
        return new CustomExceptionFilter();
    }

    @Bean
    OAuthFilter oAuthFilter() throws Exception {
        return new OAuthFilter(authenticationManager());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
                .antMatchers("/social/login").permitAll()
                .antMatchers("**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAt(oAuthFilter(),BasicAuthenticationFilter.class);
        http.addFilterAfter(customExceptionFilter(), LogoutFilter.class);

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Bean
    public GitHubAuthenticationProvider gitHubAuthenticationProvider(){
        return new GitHubAuthenticationProvider(restTemplate());
    }

    @Bean
    FacebookAuthenticationProvider facebookAuthenticationProvider(){
        return new FacebookAuthenticationProvider(restTemplate());
    }

    @Bean
    GoogleAuthenticationProvider googleAuthenticationProvider(){
        return new GoogleAuthenticationProvider(restTemplate());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth.authenticationProvider(gitHubAuthenticationProvider());
           auth.authenticationProvider(googleAuthenticationProvider());
           auth.authenticationProvider(facebookAuthenticationProvider());

    }



}
