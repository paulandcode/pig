package com.paulandcode.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * 基础增删改查, 遵循REST风格, 即: PUT<==>改, POST<==>增, GET<==>查, DELETE<==>删, 并且在路径上传ID
 * 在Controller子类上要加@RestController注解
 * 方法上的@RequestMapping在多个Controller上重复, 所以Controller子类上的@RequestMapping必须有且不能重复
 * 注意: 下面用到@RequestBody的地方用到ajax时需要传的data和contentType格式如下:
 * data: Json.stringify({name: "张三", age: 12}),
 * contentType: "application/json; charset=utf-8",
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/27 17:23
 */
public abstract class BaseController<E extends BaseEntity, S extends BaseService<E, ?>> {
    @Autowired
    protected S service;

    /**
     * 插入单个
     *
     * @param entity 实体类
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public R create(@RequestBody E entity) {
        service.insert(entity);
        return R.ok();
    }

    /**
     * 批量插入
     *
     * @param list 实体列表
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "createBatch", method = RequestMethod.POST)
    public R createBatch(@RequestBody List<E> list) {
        service.insertBatch(list);
        return R.ok();
    }

    /**
     * 根据主键删除单个, 逻辑删除
     *
     * @param id 主键, 一般为String或Integer
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public R delete(@PathVariable Object id) {
        service.deleteById(id);
        return R.ok();
    }

    /**
     * 根据主键删除多个, 逻辑删除
     *
     * @param ids 主键列表, 一般为String或Integer
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public R deleteByIds(@RequestBody List<Object> ids) {
        service.deleteByIds(ids);
        return R.ok();
    }

    /**
     * 根据主键更新单个, 主键必须传
     *
     * @param entity 更新的实体类
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "updateById", method = RequestMethod.PUT)
    public R updateById(@RequestBody E entity) {
        service.updateById(entity);
        return R.ok();
    }

    /**
     * 根据主键更新多个, 主键必须传
     *
     * @param updateData 更新数据, 包括:
     *      entity 更新的实体类, 批量更新的公共内容
     *      ids 批量更新的主键集合
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "updateByIds", method = RequestMethod.PUT)
    public R updateByIds(@RequestBody UpdateData<E> updateData) {
        service.updateByIds(updateData.entity, updateData.ids);
        return R.ok();
    }

    /**
     * 根据主键查询单个
     *
     * @param id 主键, 一般为String或Integer
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "info/{id}", method = RequestMethod.GET)
    public R info(@PathVariable Object id) {
        return R.ok(service.selectById(id));
    }

    /**
     * 根据主键查询多个
     * 注意: 由于这里是GET请求, 所以不能用@RequestBody注解接收
     * GET请求拼接的URL最多为1024字节, 每个ID长度32字节的话, 可以有32个(不考虑其他字符), 满足参数只有多个ID的情况
     *
     * @param ids 主键, 以英文逗号隔开
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "selectByIds/{ids}", method = RequestMethod.GET)
    public R selectByIds(@PathVariable String ids) {
        return R.ok(service.selectByIds(Arrays.asList(ids.split(","))));
    }

    /**
     * 根据参数查询列表, 默认为分页查询, 且没有其他查询条件, 若要加条件, 可在URL上追加
     *
     * @param page 页码
     * @param limit 每页条数
     * @return com.paulandcode.common.R
     */
    @RequestMapping(value = "list/{page}/{limit}", method = RequestMethod.GET)
    public R list(@PathVariable Integer page, @PathVariable Integer limit) {
        return R.ok(service.selectList(new P(page, limit)));
    }
}

/**
 * 更新的实体类, 解决@RequestBody只能传一个对象的问题
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/27 17:23
 */
class UpdateData<E> {
    public E entity;
    public List<Object> ids;
}