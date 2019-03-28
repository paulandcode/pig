package com.paulandcode.system.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.system.entity.User;
import com.paulandcode.utils.StringUtils;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 系统用户DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:39
 */
@Mapper
public interface UserDao extends BaseDao<User> {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return com.paulandcode.system.entity.User
     */
    @Select({"SELECT id, username, password, salt, locked, del_flag",
            "FROM sys_user",
            "WHERE username = #{username}"})
    User queryByUsername(String username);

    /**
     * 插入用户
     *
     * @param user 实体类
     */
    @Override
    @Insert({"INSERT INTO sys_user",
            "(id, username, password, salt)",
            "VALUES (#{id}, #{username}, #{password}, #{salt})"})
    void insert(User user);

    /**
     * 批量删除
     *
     * @param ids 主键列表
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "deleteBatch")
    void deleteByIds(List<Object> ids);

    /**
     * 更新数据
     *
     * @param user 用户数据
     * @param ids  主键列表
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "updateBatch")
    void updateByIds(User user, List<Object> ids);

    class Provider {
        public static String deleteBatch(List<Object> ids) {
            return new SQL() {{
                UPDATE("sys_user");
                SET("del_flag = 1");
                WHERE("id IN " + StringUtils.formatIdArr(ids));
            }}.toString();
        }

        public static String updateBatch(User user, List<Object> ids) {
            return new SQL() {{
                UPDATE("sys_user");
                if (!StringUtils.isEmpty(user.getUsername())) {
                    SET("username = #{user.username}");
                }
                if (!StringUtils.isEmpty(user.getPassword())) {
                    SET("password = #{user.password}");
                }
                if (!StringUtils.isEmpty(user.getLocked())) {
                    SET("password = #{user.locked}");
                }
                WHERE("id IN " + StringUtils.formatIdArr(ids));
            }}.toString();
        }
    }
}
