package com.paulandcode.shiro.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.shiro.entity.Session;
import org.apache.ibatis.annotations.*;

/**
 * Shiro会话的DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 22:32
 */
@Mapper
public interface RealSessionDAO extends BaseDao<Session> {
    /**
     * 增
     *
     * @param entity 实体类
     */
    @Override
    @Insert("INSERT INTO core_shiro_session VALUES (#{id}, #{session})")
    void insert(Session entity);

    /**
     * 删
     *
     * @param id 主键
     */
    @Override
    @Delete("DELETE FROM core_shiro_session WHERE id = #{id}")
    void deleteById(Object id);

    /**
     * 改
     *
     * @param entity 实体类
     */
    @Override
    @Update("UPDATE core_shiro_session SET session = #{session} WHERE id = #{id}")
    void updateById(Session entity);

    /**
     * 查
     *
     * @param id 主键
     * @return com.paulandcode.shiro.entity.Session
     */
    @Override
    @Select("SELECT id, session FROM core_shiro_session WHERE id = #{id}")
    Session selectById(Object id);
}
