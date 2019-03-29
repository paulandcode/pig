package com.paulandcode.common;

/**
 * 常量, 不需要修改的配置参数可以不用写在配置文件里, 写在这里即可
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 16:28
 */
public interface Constant {
    /**
     * 散列算法
     */
    String HASH_ALGORITHM_NAME = "MD5";
    /**
     * 散列迭代次数
     */
    int HASH_ITERATIONS = 2;
    /**
     * 是否使用16进制字符串加密, false则使用base64加密
     */
    boolean STORED_CREDENTIALS_HEX_ENCODED = true;
    /**
     * 默认的字符集: UTF-8
     */
    String DEFAULT_CHARSET = "UTF-8";
    /**
     * 响应内容类型为JSON
     */
    String APPLICATION_JSON_UTF8 = "application/json; charset=UTF-8";
    /**
     * 响应内容类型为HTML
     */
    String TEXT_HTML_UTF8 = "text/html; charset=UTF-8";
    /**
     * 默认日期格式
     */
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
