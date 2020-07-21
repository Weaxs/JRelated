package reflect;

import java.lang.reflect.Field;

public class FieldTest {

    public static void main(String[] args) {
        Son son = new Son();
        son.setDefine1("define1");
        son.setDefine3(3);
        son.setField1("field1");
        son.setField3("field3");
        son.setField4("field4");

        try {
//            Field define1 = son.getClass().getField("define1");     //father private  java.lang.NoSuchFieldException
//            Field field4 = son.getClass().getField("field4");       // father protected  java.lang.NoSuchFieldException
//            Field define1 = son.getClass().getDeclaredField("define1"); //father private  java.lang.NoSuchFieldException
//            Field field4 = son.getClass().getDeclaredField("field4"); // father protected  java.lang.NoSuchFieldException
            Field field3 = son.getClass().getField("field3");     // father public

//            Field define4 = son.getClass().getField("define4");     // son protected java.lang.NoSuchFieldException
            Field define4 = son.getClass().getDeclaredField("define4"); // son protected
            Field define3 = son.getClass().getDeclaredField("define3"); // son private
            Field define2 = son.getClass().getField("define2"); // son public

            Object getValue = field3.get(son);
            define3.set(son, "test");

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } 
    }

}
