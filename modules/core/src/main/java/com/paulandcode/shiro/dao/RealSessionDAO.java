package com.paulandcode.shiro.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.shiro.entity.ShiroSession;
import org.apache.ibatis.annotations.*;

/**
 * Shiro会话的DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 22:32
 */
@Mapper
public interface RealSessionDAO extends BaseDao<ShiroSession> {
    /**
     * 增
     *
     * @param entity 实体类
     * @return int
     */
    @Override
    @Insert("INSERT INTO sys_shiro_session VALUES (#{id, jdbcType=VARCHAR}, #{session, jdbcType=VARCHAR})")
    int insert(ShiroSession entity);

    /**
     * 删
     *
     * @param id 主键
     * @return int
     */
    @Override
    @Delete("DELETE FROM sys_shiro_session WHERE id = #{id, jdbcType=VARCHAR}")
    int deleteById(Object id);

    /**
     * 改
     *
     * @param entity 实体类
     * @return int
     */
    @Override
    @Update("UPDATE sys_shiro_session SET session = #{session, jdbcType=VARCHAR} WHERE id = #{id, jdbcType=VARCHAR}")
    int updateById(ShiroSession entity);

    /**
     * 查
     *
     * @param id 主键
     * @return com.paulandcode.shiro.entity.ShiroSession
     */
    @Override
    @Select("SELECT id, session FROM sys_shiro_session WHERE id = #{id, jdbcType=VARCHAR}")
    ShiroSession selectById(Object id);
}
