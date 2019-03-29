package com.paulandcode.dao;

import com.paulandcode.common.BaseDao;
import com.paulandcode.common.P;
import com.paulandcode.utils.SqlUtils;
import com.paulandcode.entity.TestEntity;
import com.paulandcode.utils.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.paulandcode.utils.SqlUtils.*;

/**
 * 测试
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/28 20:04
 */
@Mapper
public interface TestDao extends BaseDao<TestEntity> {
    String TABLE = "test";
    String INSERT_COLUMNS = "`id`, `name`, `age`, `birthday`, `man`";
    String SELECT_COLUMNS = "`id`, `name`, `age`, `birthday`, `man`, `remark`, `create_by`, `update_by`, " +
            "`create_date`, `update_date`, `column1`, `column2`, `column3`, `del_flag`";

    /**
     * 插入单个
     *
     * @param entity 插入的实体类
     */
    @Override
    @InsertProvider(type = Provider.class, method = "insert")
    void insert(TestEntity entity);

    /**
     * 批量插入, 参数为集合时, 要用@Param注解
     * 注意: 由于是批量进行, 所以指定插入字段后就会全部插入, 无法根据某个字段是否为NULL插入.
     * 即使数据库里有默认值, 只要此处值为NULL, 则仍会插入NULL, 而不是默认值.
     * 有非空字段并有默认值的, 要额外注意此处.
     *
     * @param list 插入的实体列表
     */
    @Override
    @InsertProvider(type = Provider.class, method = "insertBatch")
    void insertBatch(@Param("list") List<TestEntity> list);

    /**
     * 根据主键删除单个, 逻辑删除
     *
     * @param id 主键, 一般为String或Integer
     */
    @Override
    @Update({
            UPDATE,
            TABLE,
            SET,
            "del_flag = 1",
            WHERE,
            "id = #{id}"
    })
    void deleteById(Object id);

    /**
     * 根据主键删除多个, 逻辑删除
     *
     * @param ids 主键列表, 一般为String或Integer
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "deleteBatch")
    void deleteByIds(@Param("ids") List<Object> ids);

    /**
     * 根据主键更新单个, 主键必须传
     *
     * @param entity 更新的实体类
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "update")
    void updateById(TestEntity entity);

    /**
     * 根据主键更新多个, 主键必须传
     *
     * @param entity 更新的实体类, 批量更新的公共内容
     * @param ids    批量更新的主键列表
     */
    @Override
    @DeleteProvider(type = Provider.class, method = "updateBatch")
    void updateByIds(@Param("entity") TestEntity entity, @Param("ids") List<Object> ids);

    /**
     * 根据主键查询单个
     *
     * @param id 主键, 一般为String或Integer
     * @return E 查询的单个对象
     */
    @Override
    @Select({
            SELECT,
            SELECT_COLUMNS,
            FROM,
            TABLE,
            WHERE,
            "id = #{id}"
    })
    TestEntity selectById(Object id);

    /**
     * 根据主键查询多个
     *
     * @param ids 主键列表, 一般为String或Integer
     * @return java.util.List<E> 查询的列表
     */
    @Override
    @SelectProvider(type = Provider.class, method = "selectBatch")
    List<TestEntity> selectByIds(@Param("ids") List<Object> ids);

    /**
     * 根据参数查询列表
     *
     * @param p 多条件查询参数
     * @return java.util.List<E> 查询的列表
     */
    @Override
//    @DeleteProvider(type = Provider.class, method = "selectList")
    List<TestEntity> selectList(P p);

    class Provider {
        public static String insert(TestEntity entity) {
            return new SQL() {{
                INSERT_INTO(TABLE);
                VALUES("id", "#{id}");
                if (!StringUtils.isEmpty(entity.getName())) {
                    VALUES("name", "#{name}");
                }
                if (!StringUtils.isEmpty(entity.getAge())) {
                    VALUES("age", "#{age}");
                }
                if (!StringUtils.isEmpty(entity.getBirthday())) {
                    VALUES("birthday", "#{birthday}");
                }
                if (!StringUtils.isEmpty(entity.getMan())) {
                    VALUES("man", "#{man}");
                }
                if (!StringUtils.isEmpty(entity.getRemark())) {
                    VALUES("remark", "#{remark}");
                }
                if (!StringUtils.isEmpty(entity.getCreateBy())) {
                    VALUES("create_by", "#{createBy}");
                }
                if (!StringUtils.isEmpty(entity.getUpdateBy())) {
                    VALUES("update_by", "#{updateBy}");
                }
                if (!StringUtils.isEmpty(entity.getCreateDate())) {
                    VALUES("create_date", "#{createDate}");
                }
                if (!StringUtils.isEmpty(entity.getUpdateDate())) {
                    VALUES("update_date", "#{updateDate}");
                }
                if (!StringUtils.isEmpty(entity.getColumn1())) {
                    VALUES("column1", "#{column1}");
                }
                if (!StringUtils.isEmpty(entity.getColumn2())) {
                    VALUES("column2", "#{column2}");
                }
                if (!StringUtils.isEmpty(entity.getColumn3())) {
                    VALUES("column3", "#{column3}");
                }
                if (!StringUtils.isEmpty(entity.getDelFlag())) {
                    VALUES("del_flag", "#{delFlag}");
                }
            }}.toString();
        }

        public static String insertBatch(@Param("list") List<TestEntity> list) {
            return INSERT_INTO + " " +
                    TABLE +
                    " (" + INSERT_COLUMNS + ") " +
                    VALUES + " " +
                    columnsToValueColumnsBatch(INSERT_COLUMNS, list.size());
        }

        public static String deleteBatch(@Param("ids") List<Object> ids) {
            return new SQL() {{
                UPDATE(TABLE);
                SET("del_flag = 1");
                WHERE("id IN " + SqlUtils.formatIdArr(ids));
            }}.toString();
        }

        public static String update(TestEntity entity) {
            return new SQL() {{
                UPDATE(TABLE);
                if (!StringUtils.isEmpty(entity.getName())) {
                    SET("name = #{name}");
                }
                if (!StringUtils.isEmpty(entity.getAge())) {
                    SET("age = #{age}");
                }
                if (!StringUtils.isEmpty(entity.getBirthday())) {
                    SET("birthday = #{birthday}");
                }
                if (!StringUtils.isEmpty(entity.getMan())) {
                    SET("man = #{man}");
                }
                if (!StringUtils.isEmpty(entity.getRemark())) {
                    SET("remark = #{remark}");
                }
                if (!StringUtils.isEmpty(entity.getCreateBy())) {
                    SET("create_by = #{createBy}");
                }
                if (!StringUtils.isEmpty(entity.getUpdateBy())) {
                    SET("update_by = #{updateBy}");
                }
                if (!StringUtils.isEmpty(entity.getCreateDate())) {
                    SET("create_date = #{createDate}");
                }
                if (!StringUtils.isEmpty(entity.getUpdateDate())) {
                    SET("update_date = #{updateDate}");
                }
                if (!StringUtils.isEmpty(entity.getColumn1())) {
                    SET("column1 = #{column1}");
                }
                if (!StringUtils.isEmpty(entity.getColumn2())) {
                    SET("column2 = #{column2}");
                }
                if (!StringUtils.isEmpty(entity.getColumn3())) {
                    SET("column3 = #{column3}");
                }
                if (!StringUtils.isEmpty(entity.getDelFlag())) {
                    SET("del_flag = #{delFlag}");
                }
                WHERE("id = #{id}");
            }}.toString();
        }

        public static String updateBatch(@Param("entity") TestEntity entity,
                                         @Param("ids") List<Object> ids) {
            return new SQL() {{
                UPDATE(TABLE);
                if (!StringUtils.isEmpty(entity.getName())) {
                    SET("name = #{entity.name}");
                }
                if (!StringUtils.isEmpty(entity.getAge())) {
                    SET("age = #{entity.age}");
                }
                if (!StringUtils.isEmpty(entity.getBirthday())) {
                    SET("birthday = #{entity.birthday}");
                }
                if (!StringUtils.isEmpty(entity.getMan())) {
                    SET("man = #{entity.man}");
                }
                if (!StringUtils.isEmpty(entity.getRemark())) {
                    SET("remark = #{entity.remark}");
                }
                if (!StringUtils.isEmpty(entity.getCreateBy())) {
                    SET("create_by = #{entity.createBy}");
                }
                if (!StringUtils.isEmpty(entity.getUpdateBy())) {
                    SET("update_by = #{entity.updateBy}");
                }
                if (!StringUtils.isEmpty(entity.getCreateDate())) {
                    SET("create_date = #{entity.createDate}");
                }
                if (!StringUtils.isEmpty(entity.getUpdateDate())) {
                    SET("update_date = #{entity.updateDate}");
                }
                if (!StringUtils.isEmpty(entity.getColumn1())) {
                    SET("column1 = #{entity.column1}");
                }
                if (!StringUtils.isEmpty(entity.getColumn2())) {
                    SET("column2 = #{entity.column2}");
                }
                if (!StringUtils.isEmpty(entity.getColumn3())) {
                    SET("column3 = #{entity.column3}");
                }
                if (!StringUtils.isEmpty(entity.getDelFlag())) {
                    SET("del_flag = #{entity.delFlag}");
                }
                WHERE("id IN " + SqlUtils.formatIdArr(ids));
            }}.toString();
        }

        public static String selectBatch(@Param("ids") List<Object> ids) {
            return new SQL() {{
                SELECT(SELECT_COLUMNS);
                FROM(TABLE);
                WHERE("id IN " + SqlUtils.formatIdArr(ids));
            }}.toString();
        }
    }
}