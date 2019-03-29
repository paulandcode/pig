package com.paulandcode.system.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.utils.SqlUtils;
import com.paulandcode.system.entity.UserEntity;
import com.paulandcode.utils.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.paulandcode.utils.SqlUtils.*;

/**
 * 系统用户DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:39
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {
    /**
     * 系统用户表名
     */
    String CORE_SYS_USER = "core_sys_user";

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return com.paulandcode.system.entity.UserEntity
     */
    @Select({
        SELECT,
            "id, username, password, salt, locked, del_flag",
        FROM,
            CORE_SYS_USER,
        WHERE,
            "username = #{username}"
    })
    UserEntity queryByUsername(String username);

    /**
     * 插入用户
     *
     * @param entity 实体类
     */
    @Override
    @Insert({
        INSERT_INTO,
            CORE_SYS_USER,
        "(id, username, password, salt)",
            VALUES,
        "(#{id}, #{username}, #{password}, #{salt})"
    })
    void insert(UserEntity entity);

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
     * @param entity 用户数据
     * @param ids  主键列表
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "updateBatch")
    void updateByIds(@Param("entity") UserEntity entity, @Param("ids") List<Object> ids);

    class Provider {
        public static String deleteBatch(List<Object> ids) {
            return new SQL() {{
                UPDATE(CORE_SYS_USER);
                SET("del_flag = 1");
                WHERE("id IN " + SqlUtils.formatIdArr(ids));
            }}.toString();
        }

        /**
         * 批量更新, 当有多个参数时, 此处和上面都要用@Param
         *
         * @param entity 用户
         * @param ids 主键列表
         * @return java.lang.String
         */
        public static String updateBatch(@Param("entity") UserEntity entity,
                                         @Param("ids") List<Object> ids) {
            return new SQL() {{
                UPDATE(CORE_SYS_USER);
                if (!StringUtils.isEmpty(entity.getUsername())) {
                    SET("username = #{entity.username}");
                }
                if (!StringUtils.isEmpty(entity.getPassword())) {
                    SET("password = #{entity.password}");
                }
                if (!StringUtils.isEmpty(entity.getLocked())) {
                    SET("password = #{entity.locked}");
                }
                WHERE("id IN " + SqlUtils.formatIdArr(ids));
            }}.toString();
        }
    }
}
