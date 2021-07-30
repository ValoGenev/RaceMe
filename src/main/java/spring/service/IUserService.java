package spring.service;

import spring.entity.UserEntity;

public interface IUserService {

    UserEntity createUserWhenLogging(UserEntity userEntity);
}
