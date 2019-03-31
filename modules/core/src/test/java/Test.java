import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) {
        Field[] declaredFields = B.class.getDeclaredFields();
        Field[] declaredFieldss = B.class.getFields();
        for (Field declaredField : declaredFields) {
            declaredField.getName();
            try {
                declaredField.get(new B());
                declaredField.get(new B());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}

class A {
     protected int a = 1;
     public int b = 2;
}

class B extends A {
    public int b = 6;
    protected int c = 3;
}

class C extends A {
     {a = 3;
    }
}