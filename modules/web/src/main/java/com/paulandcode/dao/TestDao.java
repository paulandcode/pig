package com.paulandcode.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 测试
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/28 20:04
 */
@Mapper
public interface TestDao extends BaseDao<TestEntity> {
    class Provider extends com.paulandcode.common.BaseDao.Provider<TestEntity> {
        {
            insertBatchColumns = "`id`, `name`, `age`, `birthday`, `man`";
        }
    }
}