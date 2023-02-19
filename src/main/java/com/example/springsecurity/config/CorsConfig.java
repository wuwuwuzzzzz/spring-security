package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wxz
 * @date 15:24 2023/2/19
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry
            // 设置跨域允许的路径
            .addMapping("/**")
            // 设置跨域允许的请求的域名
            .allowedOriginPatterns("*")
            // 设置是否允许携带Cookie
            .allowCredentials(true)
            // 设置允许的请求方式
            .allowedMethods("GET", "POST", "DELETE", "PUT")
            // 设置允许的Header属性
            .allowedHeaders("*")
            // 跨域允许的时间
            .maxAge(3600);
    }

}
