package com.paulandcode.common;

import java.util.HashMap;

/**
 * 参数
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/26 12:53
 */
public class P extends HashMap<String, String> {
    private static final long serialVersionUID = -8922845867719592408L;

    P() {

    }

    P(HashMap<String, String> params) {
        this.putAll(params);
    }
}
