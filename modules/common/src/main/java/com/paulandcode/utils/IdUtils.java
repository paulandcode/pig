package com.paulandcode.utils;

import java.util.UUID;

/**
 * 生成32位UUID
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 20:55
 */
public class IdUtils {
    /**
     * 生成32位UUID
     *
     * @return java.lang.String
     */
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
