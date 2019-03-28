package com.paulandcode.system.service;

import com.paulandcode.common.BaseService;
import com.paulandcode.system.dao.UserDao;
import com.paulandcode.system.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户服务
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/26 18:08
 */
@Service
public class UserService extends BaseService<User, UserDao> {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return com.paulandcode.system.entity.User
     */
    public User queryByUsername(String username) {
        return dao.queryByUsername(username);
    }

    @Override
    public void insert(User entity) {
        super.insert(entity);
    }
}
