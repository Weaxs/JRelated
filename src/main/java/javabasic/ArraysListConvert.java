package javabasic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysListConvert {

    public static void main(String[] args) {

    }

    /**
     * Arrays.asList生成的ArrayList是一个Arrays的内部类，且存储的方式还是之前的array
     */
    private void arraysAsListTest() {
        //1. 问题案例1
        int[] myArray = { 1, 2, 3 };
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int [] array=(int[]) myList.get(0);
        System.out.println(array[0]);//1
        //2. 问题案例2
        Integer[] myArrayInt = { 1, 2, 3 };
        List myListInt = Arrays.asList(myArrayInt);
        myListInt.add(4);//运行时报错：UnsupportedOperationException
        myListInt.remove(1);//运行时报错：UnsupportedOperationException
        myListInt.clear();//运行时报错：UnsupportedOperationException
    }

    /**
     * 以下是推荐的arrays转List的方式
     */
    private void arrays2ListTest() {
        // 1
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        // 2  Java8 的Stream
        Integer [] myArray = { 1, 2, 3 };
        List<Integer> myList1 = Arrays.stream(myArray).collect(Collectors.toList());
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int [] myArray2 = { 1, 2, 3 };
        List<Integer> myList2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());

    }

}
