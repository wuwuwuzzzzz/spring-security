package com.example.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxz
 * @date 11:38 2023/2/16
 */
@RestController
public class HelloController {

    /**
     * @return java.lang.String
     * @author wxz
     * @date 16:17 2023/2/19
     */
    @PreAuthorize("@wxz.hasAuthority('system:dept:list')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
