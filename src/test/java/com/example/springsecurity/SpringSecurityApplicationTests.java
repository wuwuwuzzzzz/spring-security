package com.example.springsecurity;

import com.example.springsecurity.domain.User;
import com.example.springsecurity.mapper.MenuMapper;
import com.example.springsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String encode = b.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelectPermsByUserId() {
        List<String> list = menuMapper.selectPermsByUserId(1L);
        System.out.println(list);
    }
}
