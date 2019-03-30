import com.paulandcode.utils.SQLUtils;
import com.paulandcode.utils.StringUtils;

import static com.paulandcode.common.Constant.COMMA;

/**
 * 给SQL字段加上`, 写MySQL的Dao代码时用
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/30 15:45
 */
public class AddDot {
    public static void main(String[] args) {
        System.out.println(addDot("id, username, password, salt, locked, del_flag"));
    }

    /**
     * 给SQL字段加上`, 写MySQL的Dao代码时用
     *
     * @param columns 例: "id, username, password, salt, locked, del_flag"
     * @return java.lang.String 例: `id`, `username`, `password`, `salt`, `locked`, `del_flag`
     */
    public static String addDot(String columns) {
        String[] columnArr = columns.split(COMMA);
        StringBuilder sb = new StringBuilder();
        for (String column : columnArr) {
            sb.append('`').append(column.trim()).append("`").append(SQLUtils.COMMA);
        }
        return StringUtils.removeLastTwoChar(sb).toString();
    }
}
