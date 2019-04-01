import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) {
        new A();
        new B();
        new C();
    }


}

class A {
     protected static int a = 1;
    {
        System.out.println(a);
    }
}

class B extends A {
    static {
        a = 2;
    }
}

class C extends A {
    static {
        a = 3;
    }
}