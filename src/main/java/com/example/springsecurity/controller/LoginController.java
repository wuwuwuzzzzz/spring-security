package com.example.springsecurity.controller;

import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.domain.User;
import com.example.springsecurity.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wxz
 * @date 11:50 2023/2/17
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param user 用户
     * @return com.example.springsecurity.domain.ResponseResult
     * @author wxz
     * @date 15:44 2023/2/18
     */
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    /**
     * 退出登录
     *
     * @return com.example.springsecurity.domain.ResponseResult
     * @author wxz
     * @date 15:44 2023/2/18
     */
    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
