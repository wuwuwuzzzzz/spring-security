package com.example.springsecurity.controller;

import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.domain.User;
import com.example.springsecurity.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author wxz
 * @date 11:50 2023/2/17
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
}
