package com.paulandcode.common;

import com.paulandcode.utils.SQLUtils;
import com.paulandcode.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static com.paulandcode.utils.SQLUtils.*;

/**
 * 基础增删改查DAO, 在Dao子接口上要加@Mapper注解
 * 子接口的内部类要继承该类的Provider类
 * 若需要重写时, 可以重写BaseDao里的方法, 也可以只重写BaseDao.Provider里的方法
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
    @InsertProvider(type = Provider.class, method = "insert")
    void insert(E entity);

    /**
     * 批量插入, 参数为集合时, 要用@Param注解
     * 注意: 由于是批量进行, 所以指定插入字段后就会全部插入, 无法根据某个字段是否为NULL插入.
     * 即使数据库里有默认值, 只要此处值为NULL, 则仍会插入NULL, 而不是默认值.
     * 有非空字段并有默认值的, 要额外注意此处.
     *
     * @param list 插入的实体列表
     */
    @InsertProvider(type = Provider.class, method = "insertBatch")
    void insertBatch(@Param("list") List<E> list);

    /**
     * 根据主键删除单个, 逻辑删除
     *
     * @param id 主键, 一般为String或Integer
     */
    @UpdateProvider(type = Provider.class, method = "delete")
    void deleteById(Object id);

    /**
     * 根据主键删除多个, 逻辑删除
     *
     * @param ids 主键列表, 一般为String或Integer
     */
    @DeleteProvider(type = Provider.class, method = "deleteBatch")
    void deleteByIds(@Param("ids") List<Object> ids);

    /**
     * 根据主键更新单个, 主键必须传
     *
     * @param entity 更新的实体类
     */
    @DeleteProvider(type = Provider.class, method = "update")
    void updateById(E entity);

    /**
     * 根据主键更新多个, 主键必须传
     *
     * @param entity 更新的实体类, 批量更新的公共内容
     * @param ids    批量更新的主键列表
     */
    @DeleteProvider(type = Provider.class, method = "updateBatch")
    void updateByIds(@Param("entity") E entity, @Param("ids") List<Object> ids);

    /**
     * 根据主键查询单个
     *
     * @param id 主键, 一般为String或Integer
     * @return E 查询的单个对象
     */
    @SelectProvider(type = Provider.class, method = "select")
    E selectById(Object id);

    /**
     * 根据主键查询多个
     *
     * @param ids 主键列表, 一般为String或Integer
     * @return java.util.List<E> 查询的列表
     */
    @SelectProvider(type = Provider.class, method = "selectBatch")
    List<E> selectByIds(@Param("ids") List<Object> ids);

    /**
     * 根据参数查询列表
     *
     * @param p 多条件查询参数
     * @return java.util.List<E> 查询的列表
     */
    @SelectProvider(type = Provider.class, method = "selectList")
    List<E> selectList(P p);

    @Slf4j
    class Provider<E extends BaseEntity> {
        /**
         * 批量插入的字段, 必须在子Dao接口中重新定义定义, 以英文逗号隔开
         */
        protected static String insertBatchColumns = "`id`";
        /**
         * 查询列表时的字段, 默认为全部字段, 会在下面的构造代码块中初始化, 可在子Dao接口中重新定义定义, 以英文逗号隔开
         */
        protected StringBuilder selectColumns;
        /**
         * 要插入的表名, 要求表名下划线形式, 实体类名为大驼峰并以Entity结尾, 如: 表名为test_user, 实体类名为TestUserEntity
         * 会在下面的构造代码块被初始化, 构造对象时会先执行构造代码块. 可在子Dao接口中重新定义定义
         */
        protected String tableName;
        /**
         * 实体类所有属性, 也就是表的所有字段, 会在下面的构造代码块中初始化
         */
        private List<Field> fields;
        /**
         * 获得E.class的实际类型
         */
        @SuppressWarnings("unchecked")
        private final Class<E> entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        {
            String className = entityClass.getSimpleName();
            className = className.substring(0, 1).toLowerCase() + className.substring(1);
            // 根据类名获得表名
            tableName = SQLUtils.toUnderline(className.substring(0, className.lastIndexOf("Entity"))).toLowerCase();
            // 获得BaseEntity.class
            Class<? super E> superclass = entityClass.getSuperclass();
            // 获得类中所有类型的属性, 但不包括父类中的属性
            Field[] declaredFields = entityClass.getDeclaredFields();
            Field[] superDeclaredFields = superclass.getDeclaredFields();
            fields = new ArrayList<>();
            selectColumns = new StringBuilder("`");
            addFields(declaredFields);
            addFields(superDeclaredFields);
            StringUtils.removeLastChar(selectColumns, 3);
        }

        /**
         * 装载实体类的属性
         *
         * @param fields 实体类的属性
         */
        private void addFields(Field[] fields) {
            for (Field field : fields) {
                if (!"serialVersionUID".equals(field.getName())) {
                    field.setAccessible(true);
                    this.fields.add(field);
                    selectColumns.append(SQLUtils.toUnderline(field.getName())).append("`, `");
                }
            }
        }

        /**
         * 获得通用的插入或更新SQL语句
         *
         * @param entity 实体类
         * @param type   类型: 插入或更新
         * @return org.apache.ibatis.jdbc.SQL
         */
        private SQL getSQL(E entity, String type, String entityName) {
            return new SQL() {{
                if (UPDATE.equals(type)) {
                    UPDATE(tableName);
                } else {
                    INSERT_INTO(tableName);
                }
                for (Field field : fields) {
                    try {
                        // 属性名
                        String fieldName = field.getName();
                        // 获取字段值, 只要不是NULL都会插入/更新
                        if (field.get(entity) != null) {
                            if (UPDATE.equals(type)) {
                                SET("`" + SQLUtils.toUnderline(fieldName) + "` = #{" + (entityName == null ? "" : (entityName + ".")) + fieldName + "}");
                            } else {
                                VALUES("`" + SQLUtils.toUnderline(fieldName) + "`", "#{" + fieldName + "}");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }};
        }

        /**
         * 通用插入方法, 这里需要注意:
         * 1. 这里的Entity类, 必须符合BaseEntity中的三条规定
         * 2. 如果想插入/更新某个字段, 且插入/更新的是空字符串, 则传空字符串, 不能传NULL.
         * 若不想插入/更新这个字段, 则传NULL(即不传)(这种情况, 如果是插入, 且数据库有默认值, 则会被插入默认值)
         * 3. 但是这样的话, 对于时间或数字等非字符串字段的更新会有问题: 某个时间或数字等非字符串字段需要更新为NULL时,
         * 传空字符串会报错, 但传NULL又无法更新. 对于这种情况, 需要前端进行必填校验, 不允许用户更新为NULL.
         * 4. 如果前端非要将时间或数字等非字符串字段更新为NULL, 只能在BaseDao的子接口里重写该方法.
         *
         * @param entity 该对象中封装的值都会被插入
         * @return java.lang.String
         */
        public String insert(E entity) {
            return getSQL(entity, INSERT_INTO, null).toString();
        }

        public String insertBatch(@Param("list") List<E> list) {
            return INSERT_INTO +
                    tableName +
                    " (" + insertBatchColumns + ")" +
                    VALUES +
                    columnsToValueColumnsBatch(insertBatchColumns, list.size());
        }

        public String delete(Object id) {
            return new SQL() {{
                UPDATE(tableName);
                SET("`del_flag` = 1");
                WHERE("`id` = #{id}");
            }}.toString();
        }

        public String deleteBatch(@Param("ids") List<Object> ids) {
            return new SQL() {{
                UPDATE(tableName);
                SET("`del_flag` = 1");
                WHERE("`id`" + IN + SQLUtils.formatIdList(ids));
            }}.toString();
        }

        public String update(E entity) {
            return getSQL(entity, UPDATE, null).WHERE("`id` = #{id}").toString();
        }

        public String updateBatch(@Param("entity") E entity, @Param("ids") List<Object> ids) {
            return getSQL(entity, UPDATE, "entity").WHERE("`id`" + IN + SQLUtils.formatIdList(ids)).toString();
        }

        public String select(Object id) {
            return new SQL() {{
                SELECT(selectColumns.toString());
                FROM(tableName);
                WHERE("`id` = #{id}");
            }}.toString();
        }

        public String selectBatch(@Param("ids") List<Object> ids) {
            return new SQL() {{
                SELECT(selectColumns.toString());
                FROM(tableName);
                WHERE("`id`" + IN + SQLUtils.formatIdList(ids));
            }}.toString();
        }

        public String selectList(P p) {
            return new SQL() {{
                SELECT(selectColumns.toString());
                FROM(tableName);
                WHERE("`del_flag` = 0");
            }}.toString() + LIMIT + p.getOffset() + ", " + p.getLimit();
        }
    }
}