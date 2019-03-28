package com.paulandcode.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.paulandcode.common.Constant.UTF_8;

/**
 * 通用过滤器, 用来设置编码和打印请求参数
 * Order(1)主键表示执行顺序, 值越小, 越先执行
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/27 19:54
 */
@Order(1)
@WebFilter(filterName = "common", urlPatterns = "/*")
@Slf4j
public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // Spring Boot默认的请求编码就是UTF-8
        req.setCharacterEncoding(UTF_8);
        // Spring Boot默认的响应编码就是UTF-8
        resp.setCharacterEncoding(UTF_8);
        // 响应内容类型, 默认为text/html
        resp.setContentType("text/html; charset=utf-8");
        log.debug(req.getParameterMap().toString());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
