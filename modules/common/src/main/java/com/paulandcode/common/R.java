package com.paulandcode.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Web响应信息
 * 前后端通信时一般0或200为正常, 1或500以及其他为异常
 * 数据库中字段的值一般0为false, 1为true
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/27 17:23
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = -7035368021961298846L;

    /**
     * 构造函数, 默认成功.
     */
    private R() {
        put("code", 0);
    }

    /**
     * 响应未知异常.
     *
     * @return com.paulandcode.common.R
     */
    public static R err() {
        return err(1, "未知异常, 请联系管理员! ");
    }

    /**
     * 响应自定义异常信息.
     *
     * @param msg 异常信息
     * @return com.paulandcode.common.R
     */
    public static R err(String msg) {
        return err(1, msg);
    }

    /**
     * 响应自定义异常信息和状态码.
     *
     * @param code 状态码
     * @param msg  异常信息
     * @return com.paulandcode.common.R
     */
    public static R err(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 响应成功并自定义信息.
     *
     * @param msg 成功信息
     * @return com.paulandcode.common.R
     */
    public static R msg(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    /**
     * 响应成功.
     *
     * @return com.paulandcode.common.R
     */
    public static R ok() {
        return new R();
    }

    /**
     * 响应成功并自定义信息.
     *
     * @param data 成功数据
     * @return com.paulandcode.common.R
     */
    public static R ok(Object data) {
        return ok().data(data);
    }

    /**
     * 响应成功并加入一些键值对.
     *
     * @param map 键值对
     * @return com.paulandcode.common.R
     */
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    /**
     * 响应信息移除指定键值对.
     *
     * @param key 键
     * @return com.paulandcode.common.R
     */
    public R remove(String key) {
        super.remove(key);
        return this;
    }

    /**
     * 响应中加入一个键值对.
     *
     * @param key   键
     * @param value 值
     * @return com.paulandcode.common.R
     */
    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 放入响应数据
     *
     * @param data 响应数据
     * @return com.paulandcode.common.R
     */
    public R data(Object data) {
        return put("data", data);
    }

    /**
     * 放入数据总数
     *
     * @param count 总数
     * @return com.paulandcode.common.R
     */
    public R count(int count) {
        return put("count", count);
    }
}