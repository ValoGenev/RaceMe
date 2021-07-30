package spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.entity.UserEntity;
import spring.service.IUserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class LoginController {

    private final IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/social/login", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> login(@RequestParam String code, String type, Authentication a) {

        return ok(userService.createUserWhenLogging((UserEntity) a.getPrincipal()));
    }
}
