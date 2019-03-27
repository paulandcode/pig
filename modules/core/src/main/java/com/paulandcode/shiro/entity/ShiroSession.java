package com.paulandcode.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * shiro会话
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 22:57
 */
@Data
@AllArgsConstructor
public class ShiroSession implements Serializable {
    private static final long serialVersionUID = -1058318866957363412L;
    private String id;
    private String session;
}
