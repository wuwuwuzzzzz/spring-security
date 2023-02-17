package com.example.springsecurity.service;

import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.domain.User;

/**
 * @author wxz
 * @date 11:53 2023/2/17
 */
public interface LoginService {
    /**
     * 登录方法
     *
     * @param user 用户
     * @return com.example.springsecurity.domain.ResponseResult
     * @author wxz
     * @date 11:56 2023/2/17
     */
    ResponseResult login(User user);
}
