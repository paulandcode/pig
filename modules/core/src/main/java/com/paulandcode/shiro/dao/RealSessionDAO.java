package com.paulandcode.shiro.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.shiro.entity.SessionEntity;
import org.apache.ibatis.annotations.*;

import static com.paulandcode.utils.SQLUtils.*;

/**
 * Shiro会话的DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 22:32
 */
@Mapper
public interface RealSessionDAO extends BaseDao<SessionEntity> {
    String TABLE = "core_shiro_session";
    String SELECT_COLUMNS = "`id`, `session`";

    /**
     * 增
     *
     * @param entity 实体类
     */
    @Override
    @Insert({
            INSERT_INTO,
            TABLE,
            VALUES,
            "(#{id}, #{session})"
    })
    void insert(SessionEntity entity);

    /**
     * 删
     *
     * @param id 主键
     */
    @Override
    @Delete({
            DELETE_FROM,
            TABLE,
            WHERE,
            "`id` = #{id}"
    })
    void deleteById(Object id);

    /**
     * 改
     *
     * @param entity 实体类
     */
    @Override
    @Update({
            UPDATE,
            TABLE,
            SET,
            "`session` = #{session}",
            WHERE,
            "`id` = #{id}"
    })
    void updateById(SessionEntity entity);

    /**
     * 查
     *
     * @param id 主键
     * @return com.paulandcode.shiro.entity.SessionEntity
     */
    @Override
    @Select({
            SELECT,
            SELECT_COLUMNS,
            FROM,
            TABLE,
            WHERE,
            "`id` = #{id}"
    })
    SessionEntity selectById(Object id);
}