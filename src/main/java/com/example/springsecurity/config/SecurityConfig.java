package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wxz
 * @date 21:13 2023/2/16
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 创建BCryptPasswordEncoder注入容器
     *
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @author wxz
     * @date 21:15 2023/2/16
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return org.springframework.security.authentication.AuthenticationManager
     * @author wxz
     * @date 12:00 2023/2/17
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @param http 请求
     * @author wxz
     * @date 12:12 2023/2/17
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 关闭csrf
            .csrf().disable()
            // 不通过Session获取SecurityContext
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
            // 对于接口 允许匿名访问
            .antMatchers("/user/login").anonymous()
            // 除上面外的接口都需要权限验证
            .anyRequest().authenticated();
    }
}
