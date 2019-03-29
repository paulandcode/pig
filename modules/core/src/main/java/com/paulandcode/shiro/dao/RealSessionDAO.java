package com.paulandcode.shiro.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.shiro.entity.SessionEntity;
import org.apache.ibatis.annotations.*;

import static com.paulandcode.utils.SqlUtils.*;

/**
 * Shiro会话的DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 22:32
 */
@Mapper
public interface RealSessionDAO extends BaseDao<SessionEntity> {
    String CORE_SHIRO_SESSION = "core_shiro_session";

    /**
     * 增
     *
     * @param entity 实体类
     */
    @Override
    @Insert({
        INSERT_INTO,
            CORE_SHIRO_SESSION,
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
            CORE_SHIRO_SESSION,
        WHERE,
            "id = #{id}"
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
            CORE_SHIRO_SESSION,
        SET,
            "session = #{session}",
        WHERE,
            "id = #{id}"
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
            "id, session",
        FROM,
            CORE_SHIRO_SESSION,
        WHERE,
            "id = #{id}"
    })
    SessionEntity selectById(Object id);
}