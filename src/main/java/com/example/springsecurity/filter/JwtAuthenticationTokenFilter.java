package com.example.springsecurity.filter;

import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.utils.JwtUtil;
import com.example.springsecurity.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author wxz
 * @date 14:05 2023/2/18
 */
@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    /**
     * 过滤器
     *
     * @param request request
     * @param response response
     * @param filterChain filterChain
     * @author wxz
     * @date 14:24 2023/2/18
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
        throws ServletException, IOException {

        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }

        // 从redis中获取用户信息
        String redis = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redis);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        // 存入securityContextHolder
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
