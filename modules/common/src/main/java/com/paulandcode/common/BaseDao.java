package com.paulandcode.common;

import java.util.List;

/**
 * 基础增删改查DAO, 在Dao子接口上要加@Mapper注解
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2018/12/28 17:11
 */
public interface BaseDao<E extends BaseEntity> {
    /**
     * 插入单个
     *
     * @param entity 插入的实体类
     */
    void insert(E entity);

    /**
     * 批量插入
     *
     * @param list 插入的实体列表
     */
    void insertBatch(List<E> list);

    /**
     * 根据主键删除单个
     *
     * @param id 主键, 一般为String或Integer
     */
    void deleteById(Object id);

    /**
     * 根据主键删除多个
     *
     * @param ids 主键列表, 一般为String或Integer
     */
    void deleteByIds(List<Object> ids);

    /**
     * 根据主键更新单个, 主键必须传
     *
     * @param entity 更新的实体类
     */
    void updateById(E entity);

    /**
     * 根据主键更新多个, 主键必须传
     *
     * @param entity 更新的实体类, 批量更新的公共内容
     * @param ids    批量更新的主键列表
     */
    void updateByIds(E entity, List<Object> ids);

    /**
     * 根据主键查询单个
     *
     * @param id 主键, 一般为String或Integer
     * @return E 查询的单个对象
     */
    E selectById(Object id);

    /**
     * 根据主键查询多个
     *
     * @param ids 主键列表, 一般为String或Integer
     * @return java.util.List<E> 查询的列表
     */
    List<E> selectByIds(List<Object> ids);

    /**
     * 根据参数查询列表
     *
     * @param p 多条件查询参数
     * @return java.util.List<E> 查询的列表
     */
    List<E> selectList(P p);
}