package com.paulandcode.common;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基础增删改查
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:50
 */
public abstract class BaseService<E, D extends BaseDao<E>> {
    @Autowired
    public D dao;

    /**
     * 插入单个
     *
     * @param entity 插入的实体类
     * @return int 成功等于1
     */
    public int insert(E entity) {
        return dao.insert(entity);
    }

    /**
     * 批量插入
     *
     * @param list 插入的实体数组
     * @return int 成功大于等于1
     */
    public int insertBatch(List<E> list) {
        return dao.insertBatch(list);
    }

    /**
     * 根据主键删除单个
     *
     * @param id 主键, 一般为String或Integer
     * @return int 成功等于1
     */
    public int deleteById(Object id) {
        return dao.deleteById(id);
    }

    /**
     * 根据主键删除多个
     *
     * @param ids 主键, 一般为String或Integer
     * @return int 成功大于等于1
     */
    public int deleteByIds(List<Object> ids) {
        return dao.deleteByIds(ids);
    }

    /**
     * 根据主键更新单个, 主键必须传
     *
     * @param entity 更新的实体类
     * @return int 成功等于1
     */
    public int updateById(E entity) {
        return dao.updateById(entity);
    }

    /**
     * 根据主键更新多个, 主键必须传
     *
     * @param entity 更新的实体类, 批量更新的公共内容
     * @param ids 批量更新的各个id
     * @return int 成功大于等于1
     */
    public int updateByIds(E entity, List<Object> ids) {
        return dao.updateByIds(entity, ids);
    }

    /**
     * 根据主键查询单个
     *
     * @param id 主键, 一般为String或Integer
     * @return E 查询的单个对象
     */
    public E selectById(Object id) {
        return dao.selectById(id);
    }

    /**
     * 根据主键查询多个
     *
     * @param ids 主键, 一般为String或Integer
     * @return java.util.List<E> 查询的列表
     */
    public List<E> selectByIds(List<Object> ids) {
        return dao.selectByIds(ids);
    }

    /**
     * 根据参数查询列表
     *
     * @param params 多条件查询参数
     * @return java.util.List<E> 查询的列表
     */
    public List<E> selectList(Params params) {
        return dao.selectList(params);
    }
}
