package com.paulandcode.common;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 基础增删改查DAO
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2018/12/28 17:11
 */
public interface BaseDao<E> {
    /**
     * 插入单个
     *
     * @param entity 插入的实体类
     * @return int 成功等于1
     */
    int insert(E entity);

    /**
     * 批量插入
     *
     * @param list 插入的实体数组
     * @return int 成功大于等于1
     */
    int insertBatch(List<E> list);

    /**
     * 根据主键删除单个
     *
     * @param id 主键, 一般为String或Integer
     * @return int 成功等于1
     */
    int deleteById(Object id);

    /**
     * 根据主键删除多个
     *
     * @param ids 主键, 一般为String或Integer
     * @return int 成功大于等于1
     */
    int deleteByIds(List<Object> ids);

    /**
     * 根据主键更新单个, 主键必须传
     *
     * @param entity 更新的实体类
     * @return int 成功等于1
     */
    int updateById(E entity);

    /**
     * 根据主键更新多个, 主键必须传
     *
     * @param entity 更新的实体类, 批量更新的公共内容
     * @param ids 批量更新的各个id
     * @return int 成功大于等于1
     */
    int updateByIds(E entity, List<Object> ids);

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
     * @param ids 主键, 一般为String或Integer
     * @return java.util.List<E> 查询的列表
     */
    List<E> selectByIds(List<Object> ids);

    /**
     * 根据参数查询列表
     *
     * @param params 多条件查询参数
     * @return java.util.List<E> 查询的列表
     */
    List<E> selectList(Params params);
}