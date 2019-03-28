package com.paulandcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Application
 * ServletComponentScan注解后, Servlet, Filter, Listener可以直接通过@WebServlet, @WebFilter, @WebListener注解自动注册
 *
 * @author ThinkGem
 * @version 2018-10-13
 */
@SpringBootApplication
@ServletComponentScan("com.paulandcode.common")
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}