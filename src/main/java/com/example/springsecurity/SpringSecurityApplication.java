package com.example.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author wxz
 * @date 11:35 2023/2/16
 */
@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringSecurityApplication.class, args);
        System.out.println(111);
    }

}
