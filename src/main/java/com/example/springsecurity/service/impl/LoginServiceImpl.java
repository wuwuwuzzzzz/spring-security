package com.example.springsecurity.service.impl;

import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.domain.User;
import com.example.springsecurity.service.LoginService;
import com.example.springsecurity.utils.JwtUtil;
import com.example.springsecurity.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author wxz
 * @date 11:54 2023/2/17
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        // AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);

        // 如果认证没成功，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 如果认证成功，使用userId生成一个jwt，jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJwt(userId);
        Map<String, String> map = new HashMap<>(5);
        map.put("token", jwt);

        // 把完整用户信息存入Redis，userId作为key
        redisCache.setCacheObject("login:" + userId, loginUser, 30, TimeUnit.SECONDS);

        return new ResponseResult(200, "登录成功", map);
    }
}
