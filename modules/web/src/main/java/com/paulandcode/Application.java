package com.paulandcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletComponentScan注解后, Servlet, Filter, Listener
 *     可以直接通过@WebServlet, @WebFilter, @WebListener注解自动注册
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/27 22:27
 */
@SpringBootApplication
@ServletComponentScan("com.paulandcode.common")
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}