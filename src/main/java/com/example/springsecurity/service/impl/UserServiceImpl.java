package com.example.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.domain.User;
import com.example.springsecurity.mapper.UserMapper;
import com.example.springsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author wxz
 * @date 20:41 2023/2/16
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUserName, username);
        User user = userMapper.selectOne(query);
        // 如果没有查询到用户抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        // 查询用户对应权限信息
        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));

        // 把数据封装成UserDetails返回
        return new LoginUser(user, list);
    }
}
