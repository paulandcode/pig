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
     * UTF-8编码
     */
    String UTF_8 = "UTF-8";
}
