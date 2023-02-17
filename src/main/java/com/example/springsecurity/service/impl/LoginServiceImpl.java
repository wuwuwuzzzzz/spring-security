package com.example.springsecurity.service.impl;

import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.domain.User;
import com.example.springsecurity.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 *
 * @author wxz
 * @date 11:54 2023/2/17
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {

        // AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);

        // 如果认证没成功，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 如果认证成功，使用userId生成一个jwt，jwt存入ResponseResult返回
        authenticate.getPrincipal();

        // 把完整用户信息存入Redis，userId作为key

        return null;
    }
}
