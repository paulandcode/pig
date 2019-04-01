package com.paulandcode.system.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.system.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import static com.paulandcode.utils.SQLUtils.*;

/**
 * 系统用户DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:39
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return com.paulandcode.system.entity.UserEntity
     */
    @Select({
            SELECT,
            "`id`, `username`, `password`, `salt`, `locked`, `del_flag`",
            FROM,
            "core_sys_user",
            WHERE,
            "`username` = #{username}"
    })
    UserEntity queryByUsername(String username);

    class Provider extends com.paulandcode.common.BaseDao.Provider<UserEntity> {
        {
            insertBatchColumns = "`id`, `username`, `password`, `salt`";
            tableName = "core_sys_" + tableName;
        }
    }
}
