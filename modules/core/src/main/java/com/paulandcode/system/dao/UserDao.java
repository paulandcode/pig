package com.paulandcode.system.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.system.entity.CoreSysUserEntity;
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
public interface UserDao extends BaseDao<CoreSysUserEntity> {
    /**
     * 系统用户表名
     */
    String TABLE = "core_sys_user";
    String SELECT_COLUMNS = "`id`, `username`, `password`, `salt`, `locked`, `del_flag`";

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return com.paulandcode.system.entity.CoreSysUserEntity
     */
    @Select({
            SELECT,
            SELECT_COLUMNS,
            FROM,
            TABLE,
            WHERE,
            "`username` = #{username}"
    })
    CoreSysUserEntity queryByUsername(String username);

    class Provider extends com.paulandcode.common.BaseDao.Provider<CoreSysUserEntity> {
        {
            insertBatchColumns = "`id`, `username`, `password`, `salt`";
        }
    }
}
