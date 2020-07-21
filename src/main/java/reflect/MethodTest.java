package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTest {

    public static void main(String[] args) {

        try {
            Son son = new Son();
            son.setDefine1("define1");
            son.setDefine3(3);
            son.setField1("field1");
            son.setField3("field3");
//            son.setField4();

            // getMethod 获取父类及子类的public方法
            // getDeclaredMethod 获取当前类的protected和private方法


            Method field1Getter = son.getClass().getMethod("getField1");                        // father
            Method field1Setter = son.getClass().getMethod("setField1", String.class);          // father
            Method define2Getter = son.getClass().getDeclaredMethod("getDefine2");              // son private  不能调用父类的private
            Method define2Setter = son.getClass().getDeclaredMethod("setDefine2", String.class);// son private
//            Method field2Getter = son.getClass().getDeclaredMethod("getField2");                    // java.lang.NoSuchMethodException
            Method define3Setter = son.getClass().getMethod("setDefine3", Integer.class);
//            Method field4Getter = son.getClass().getDeclaredMethod("getField4");                    // java.lang.NoSuchMethodException
//            Method field4Getter2 = son.getClass().getMethod("getField4");                           // java.lang.NoSuchMethodException  不能调用父类protected
//            Method define4Getter = son.getClass().getMethod("getDefine4");                         // java.lang.NoSuchMethodException
            Method define4Getter = son.getClass().getDeclaredMethod("getDefine4");

            Method[] methods = son.getClass().getMethods();
            Method[] declaredMethods = son.getClass().getDeclaredMethods();

            Object setValue = define3Setter.invoke(son, 666);
            Object getValue = define2Getter.invoke(son);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
