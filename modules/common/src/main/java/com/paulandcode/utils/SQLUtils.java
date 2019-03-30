package com.paulandcode.utils;

import com.paulandcode.common.Constant;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL中的关键字, 以及工具类
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/28 19:37
 */
public interface SQLUtils {
    String SELECT = "SELECT ";
    String FROM = " FROM ";
    String WHERE = " WHERE ";
    String INSERT_INTO = "INSERT INTO ";
    String VALUES = " VALUES ";
    String DELETE_FROM = "DELETE FROM ";
    String UPDATE = "UPDATE ";
    String SET = " SET ";
    String IN = " IN ";
    String LIMIT = " LIMIT ";
    String GROUP_BY = " GROUP BY ";
    String ORDER_BY = " ORDER BY ";
    String DESC = " DESC ";
    String ASC = " ASC ";
    String ON = " ON ";
    String AND = " AND ";
    String OR = " OR ";
    /**
     * 逗号, 后面带空格
     */
    String COMMA = ", ";
    /**
     * 下划线
     */
    char UNDERLINE = '_';

    /**
     * 将多个id拼接成SQL
     *
     * @param ids id字符串, 以英文逗号隔开, 例: asd, sdfds, ,123655, ,
     * @return java.lang.String 例: ('asd', 'sdfds', '', '123655')
     */
    static String formatIds(String ids) {
        return formatIdList(Arrays.asList(ids.split(Constant.COMMA)));
    }

    /**
     * 将多个id拼接成SQL
     *
     * @param ids id列表
     * @return java.lang.String 例: ('asd', 'sdfds', ,'', '123655')
     */
    static String formatIdList(List<Object> ids) {
        StringBuilder sb = new StringBuilder("(");
        for (Object id : ids) {
            sb.append("'").append(id.toString().trim()).append("'").append(COMMA);
        }
        return StringUtils.removeLastTwoChar(sb).append(")").toString();
    }

    /**
     * 数组由下划线转换为驼峰
     *
     * @param arr 带有下划线的数组
     * @return java.lang.String[]
     */
    static String[] arrUnderlineToHump(String[] arr) {
        if (arr == null) {
            return null;
        }
        String[] newArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = toHump(arr[i]);
        }
        return newArr;
    }

    /**
     * 数组由驼峰转换为下划线
     *
     * @param arr 带有下划线的数组
     * @return java.lang.String[]
     */
    static String[] arrHumpToUnderline(String[] arr) {
        if (arr == null) {
            return null;
        }
        String[] newArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = toUnderline(arr[i]);
        }
        return newArr;
    }

    /**
     * 将Map中的key由下划线转换为驼峰
     *
     * @param map 带有下划线的map
     * @return java.util.Map<java.lang.String   ,   V>
     */
    static <V> Map<String, V> mapKeyUnderlineToHump(Map<String, V> map) {
        if (map == null) {
            return null;
        }
        Map<String, V> newMap = new HashMap<>(map.size());
        for (Map.Entry<String, V> entry : map.entrySet()) {
            String key = entry.getKey();
            String newKey = toHump(key);
            newMap.put(newKey, entry.getValue());
        }
        return newMap;
    }

    /**
     * 将Map中的key由驼峰转换为下划线
     *
     * @param map 带有驼峰的map
     * @return java.util.Map<java.lang.String   ,   V>
     */
    static <V> Map<String, V> mapKeyHumpToUnderline(Map<String, V> map) {
        if (map == null) {
            return null;
        }
        Map<String, V> newMap = new HashMap<>(map.size());
        for (Map.Entry<String, V> entry : map.entrySet()) {
            String key = entry.getKey();
            String newKey = toUnderline(key);
            newMap.put(newKey, entry.getValue());
        }
        return newMap;
    }

    /**
     * 下划线转驼峰
     *
     * @param colName 字符串
     * @return java.lang.String
     */
    static String toHump(String colName) {
        if (colName == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] str = colName.toLowerCase().split(String.valueOf(UNDERLINE));
        for (String s : str) {
            if (s.length() == 1) {
                sb.append(s.toUpperCase());
                continue;
            }
            if (s.length() > 1) {
                sb.append(s.substring(0, 1).toUpperCase());
                sb.append(s.substring(1));
            }
        }
        String result = sb.toString();
        return result.substring(0, 1).toLowerCase() + result.substring(1);
    }

    /**
     * 驼峰转下划线
     *
     * @param colName 字符串
     * @return java.lang.String
     */
    static String toUnderline(String colName) {
        if (colName == null) {
            return null;
        }
        String result = colName.replaceAll("[A-Z]", String.valueOf(UNDERLINE) + "$0");
        return result.toLowerCase();
    }

    /**
     * 将一个或多个字段转为MyBatis用的SQL插入语句, 其中下换线自动转为驼峰
     *
     * @param columns 英文逗号隔开的字段, 例: id, name, age, del_flag
     * @return java.lang.String SQL插入语句, 例: (#{id}, #{name}, #{age}, #{delFlag})
     */
    static String columnsToValueColumns(String columns) {
        return "(#{" + toHump(columns).replace(COMMA, "}, #{") + "})";
    }

    /**
     * 将一个或多个字段转为MyBatis用的SQL批量插入语句, 其中下换线自动转为驼峰
     *
     * @param columns 英文逗号隔开的字段, 例: id, del_flag
     * @param size    批量插入的条数
     * @param argName Dao接口方法上的参数名参数名, 默认为list
     * @return java.lang.String 例: (#{list[0].id}, #{list[0].delFlag}), (#{list[1].id}, #{list[1].delFlag})
     */
    static String columnsToValueColumnsBatch(String columns, int size, String argName) {
        if (argName == null) {
            argName = "list";
        }
        StringBuilder sb = new StringBuilder();
        MessageFormat mf = new MessageFormat("(#'{'" + argName + "[{0}]." +
                toHump(columns).replace("`", "")
                        .replace(COMMA, "}, #'{'" + argName + "[{0}].") + "})");
        for (int i = 0; i < size; i++) {
            sb.append(mf.format(new Object[]{i})).append(COMMA);
        }
        return StringUtils.removeLastTwoChar(sb).toString();
    }

    /**
     * 将一个或多个字段转为MyBatis用的SQL批量插入语句, 其中下换线自动转为驼峰
     *
     * @param columns 英文逗号隔开的字段, 例: id, del_flag
     * @param size    批量插入的条数
     * @return java.lang.String
     */
    static String columnsToValueColumnsBatch(String columns, int size) {
        return columnsToValueColumnsBatch(columns, size, null);
    }
}
