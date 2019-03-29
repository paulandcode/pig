import com.paulandcode.utils.SqlUtils;

public class Test {

    public static void main(String[] args) {
        String a = "id, del_flag";
        System.out.println(SqlUtils.columnsToValueColumnsBatch(a,  2));
    }
}