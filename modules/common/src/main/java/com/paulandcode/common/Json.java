package com.paulandcode.common;

import com.alibaba.fastjson.JSON;

import java.util.List;

import static com.paulandcode.common.Constant.DEFAULT_DATE_FORMAT;

/**
 * 引用com.alibaba.fastjson.Json的三个常用方法, 设置FastJson的日期默认格式化
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/29 21:13
 */
public class Json {
    public static String toJSONString(Object object) {
        return JSON.toJSONStringWithDateFormat(object, DEFAULT_DATE_FORMAT);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
}