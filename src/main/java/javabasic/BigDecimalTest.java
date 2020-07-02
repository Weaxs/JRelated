package javabasic;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimalTest decimalTest = new BigDecimalTest();
        decimalTest.floatTest();
    }

    /**
     * 使用Float会造成精度丢失
     */
    private void floatTest() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);//0.100000024
        System.out.println(b);//0.099999964
        System.out.println(a == b);//false
    }

    private void bigDecimalTest() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);// 0.1
        BigDecimal y = b.subtract(c);// 0.1
        System.out.println(x.equals(y));// true
        System.out.println(x.compareTo(y) == 0);// true
    }



}
