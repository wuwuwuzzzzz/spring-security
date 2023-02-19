package com.example.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurity.domain.ResponseResult;
import com.example.springsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wxz
 * @date 15:04 2023/2/19
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    /**
     * 自定义授权异常处理
     *
     * @author wxz
     * @date 15:06 2023/2/19
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        WebUtils.renderString(response,
            JSON.toJSONString(new ResponseResult(HttpStatus.FORBIDDEN.value(), "您的权限不足")));
    }
}
