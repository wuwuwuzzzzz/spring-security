package com.example.springsecurity.expression;

import com.example.springsecurity.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author wxz
 * @date 16:10 2023/2/19
 */
@Component("wxz")
public class WxzExpressionRoot {
    public final boolean hasAuthority(String authority) {
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        // 判断用户集合中是否存在authority
        return permissions.contains(authority);
    }
}
