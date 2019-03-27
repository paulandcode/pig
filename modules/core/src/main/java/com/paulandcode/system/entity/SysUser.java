package com.paulandcode.system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/18 16:51
 */
@Data
@AllArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = 5901601126324702627L;
    private String id;
    private String username;
    private String password;
    /**
     * 密码的盐
     */
    private String salt;
    /**
     * 是否锁定
     */
    private Boolean locked;
    /**
     * 是否删除
     */
    private Boolean delFlag;
}
