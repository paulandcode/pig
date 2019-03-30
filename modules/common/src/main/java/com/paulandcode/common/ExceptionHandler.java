package com.paulandcode.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.paulandcode.common.Constant.APPLICATION_JSON_UTF8;

/**
 * 异常处理器, 捕获运行时异常
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/27 19:41
 */
@Slf4j
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler,
                                         Exception e) {
        try {
            // 因为要返回JSON所以响应内容类型设置为JSON
            resp.setContentType(APPLICATION_JSON_UTF8);

            // NumberFormatException
            // CommonException


//            if (e instanceof DuplicateKeyException){
//                r = R.error("数据库中已存在该记录");
//            }else if(e instanceof AuthorizationException){
//                r = R.error("没有权限，请联系管理员授权").put("tip", "没有权限，请联系管理员授权").put("content", null);
//            }else{
//                r = R.error();
//            }

            //记录异常日志
            log.error(e.getMessage(), e);

            String json = Json.toJSONString(R.err(e.getMessage()));
            resp.getWriter().print(json);
        } catch (Exception ex) {
            log.error("ExceptionHandler 异常处理失败! ", ex);
            ex.printStackTrace();
        }
        return new ModelAndView();
    }
}