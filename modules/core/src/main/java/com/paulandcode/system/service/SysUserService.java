package com.paulandcode.system.service;

import com.paulandcode.common.BaseService;
import com.paulandcode.system.dao.SysUserDao;
import com.paulandcode.system.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * 系统用户服务
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/26 18:08
 */
@Service
public class SysUserService extends BaseService<SysUser, SysUserDao> {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return com.paulandcode.system.entity.SysUser
     */
    public SysUser queryByUsername(String username) {
        return dao.queryByUsername(username);
    }
}
