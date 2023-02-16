package com.example.springsecurity.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author wxz
 * @date 17:03 2023/2/16
 */
public class WebUtils {

    /**
     * 将字符串渲染到客户端
     * @author wxz
     * @date 17:04 2023/2/16
     * @param response 渲染对象
     * @param s 待渲染的字符串
     * @return java.lang.String
     */
    public static String renderString(HttpServletResponse response, String s) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
