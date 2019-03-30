import com.paulandcode.common.P;

public class Test {

    public static void main(String[] args) {
        P p = new P().put("a", "aaa").setPageAndLimit(1, 2);
        System.out.println(p);
        System.out.println(p.get("a"));
    }
}
