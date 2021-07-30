package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.dto.User;
import spring.repository.IUserRepository;
import spring.service.IUserService;
import spring.service.UserService;

@Configuration
public class ApplicationConfig {

    @Autowired
    IUserRepository userRepository;

    @Bean
    IUserService userService(){
        return new UserService(userRepository);
    }


}
