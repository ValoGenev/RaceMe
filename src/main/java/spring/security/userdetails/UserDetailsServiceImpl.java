package spring.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spring.entity.UserEntity;
import spring.exception.EmailNotFoundException;
import spring.repository.IUserRepository;


public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userInDb = userRepository.findByEmail(email).orElseThrow(()->{
            throw new EmailNotFoundException("EMAIL NOT FOUND");
        });

        return new UserDetailsImpl(userInDb);
    }
}
