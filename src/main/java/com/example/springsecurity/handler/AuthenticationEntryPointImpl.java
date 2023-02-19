package com.example.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wxz
 * @date 14:53 2023/2/19
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    /**
     * 自定义认证异常处理
     *
     * @author wxz
     * @date 15:06 2023/2/19
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        WebUtils.renderString(response,
            JSON.toJSONString(new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户认证失败请查询登录")));
    }
}
