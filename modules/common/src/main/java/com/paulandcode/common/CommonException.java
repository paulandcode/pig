package com.paulandcode.common;

import lombok.Data;

/**
 * 通用异常
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/28 11:27
 */
@Data
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 5132288807648359545L;
    private String msg;
    private int code = 500;

    public CommonException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CommonException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CommonException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
