package com.paulandcode.system.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.system.entity.SysUser;
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
public interface SysUserDao extends BaseDao<SysUser> {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return com.paulandcode.system.entity.SysUser
     */
    @Select({"SELECT id, username, password, salt, locked, del_flag",
            "FROM sys_user",
            "WHERE username = #{username}"})
    SysUser queryByUsername(String username);

    /**
     * 插入用户
     *
     * @param sysUser 实体类
     * @return int
     */
    @Override
    @Insert({"INSERT INTO sys_user",
            "(id, username, password, salt)",
            "VALUES (#{id}, #{username}, #{password}, #{salt})"})
    int insert(SysUser sysUser);

    /**
     * 批量删除
     *
     * @param ids 主键列表
     * @return int
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "deleteBatch")
    int deleteByIds(List<Object> ids);

    /**
     * 更新数据
     *
     * @param sysUser 用户数据
     * @param ids 主键列表
     * @return int
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "updateBatch")
    int updateByIds(SysUser sysUser, List<Object> ids);

    class Provider {
        public static String deleteBatch(List<Object> ids) {
            return new SQL() {{
                UPDATE("sys_user");
                SET("del_flag = 1");
                WHERE("id IN " + StringUtils.formatIdArr(ids));
            }}.toString();
        }

        public static String updateBatch(SysUser sysUser, List<Object> ids) {
            return new SQL() {{
                UPDATE("sys_user");
                if (!StringUtils.isEmpty(sysUser.getUsername())) {
                    SET("username = #{sysUser.username}");
                }
                if (!StringUtils.isEmpty(sysUser.getPassword())) {
                    SET("password = #{sysUser.password}");
                }
                if (!StringUtils.isEmpty(sysUser.getLocked())) {
                    SET("password = #{sysUser.locked}");
                }
                WHERE("id IN " + StringUtils.formatIdArr(ids));
            }}.toString();
        }
    }
}
