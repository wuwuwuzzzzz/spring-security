package com.example.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wxz
 * @date 11:38 2023/2/16
 */
@RestController
public class HelloController {

    @PreAuthorize("hasAuthority('test')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
