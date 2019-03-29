package com.paulandcode.common;

import com.paulandcode.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基础增删改查, 在Service子类上, 要加@Service注解
 * 这里的接口只返回数据, 没有用com.paulandcode.common.R包装, 方便不同Service实现类之间相互调用
 * 根据需要, Service实现类新加的方法也可以直接返回com.paulandcode.common.R类型
 * 某类型的Service实现类最好只调用对应类型的Dao实现类, 不要调用其他类型的Dao实现类
 * 若需要查询其他类型的数据, 可以通过这个类型的Service实现类来调用
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:50
 */
public abstract class BaseService<E extends BaseEntity, D extends BaseDao<E>> {
    @Autowired
    protected D dao;

    /**
     * 插入单个
     *
     * @param entity 插入的实体类
     */
    public void insert(E entity) {
        // 插入前后台通过UUID生成主键
        entity.setId(IdUtils.getId());
        dao.insert(entity);
    }

    /**
     * 批量插入
     *
     * @param list 插入的实体列表
     */
    public void insertBatch(List<E> list) {
        for (E entity : list) {
            // 插入前后台通过UUID生成主键
            entity.setId(IdUtils.getId());
        }
        dao.insertBatch(list);
    }

    /**
     * 根据主键删除单个
     *
     * @param id 主键, 一般为String或Integer
     */
    public void deleteById(Object id) {
        dao.deleteById(id);
    }

    /**
     * 根据主键删除多个
     *
     * @param ids 主键列表, 一般为String或Integer
     */
    public void deleteByIds(List<Object> ids) {
        dao.deleteByIds(ids);
    }

    /**
     * 根据主键更新单个, 主键必须传
     *
     * @param entity 更新的实体类
     */
    public void updateById(E entity) {
        dao.updateById(entity);
    }

    /**
     * 根据主键更新多个, 主键必须传
     *
     * @param entity 更新的实体类, 批量更新的公共内容
     * @param ids    批量更新的id列表
     */
    public void updateByIds(E entity, List<Object> ids) {
        dao.updateByIds(entity, ids);
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
     * @param p 多条件查询参数
     * @return java.util.List<E> 查询的列表
     */
    public List<E> selectList(P p) {
        return dao.selectList(p);
    }
}