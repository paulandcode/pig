package com.paulandcode.common;

import java.util.HashMap;

/**
 * 参数
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/26 12:53
 */
public class Params extends HashMap<String, String> {
    private static final long serialVersionUID = -8922845867719592408L;

    Params() {

    }

    Params(HashMap<String, String> params) {
        this.putAll(params);
    }
}
