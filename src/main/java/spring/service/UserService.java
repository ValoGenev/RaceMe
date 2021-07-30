package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.entity.UserEntity;
import spring.repository.IUserRepository;

import java.util.Optional;

public class UserService implements IUserService {


    private final IUserRepository userRepository;


    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUserWhenLogging(UserEntity userEntity) {

        Optional<UserEntity> userInDb = userRepository.findByEmail(userEntity.getEmail());

        if(userInDb.isPresent()){
            return userInDb.get();
        }else {
           userEntity = userRepository.save(userEntity);
        }

        return userEntity;
    }


}
