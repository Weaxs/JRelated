package javainterview.test.class_loader;

/**
 * 初始化阶段是执行类构造器方法<clinit>()的过程
 *
 * 所有的类变量初始化语句和类型的静态初始化器
 * 如果该类有父类，则在子类执行clinit前会先执行完毕父类的clinit(同<init>())
 *
 * 虚拟机保证一个类的clinit方法在多线程下被同步加锁，确保一个类只会被执行一次
 */
public class ClinitTest {

    private static int num = 1;

    static {
        num = 2;
        number = 20;
        System.out.println(num);
//        System.out.println(number);//报错：非法的前向引用
    }

    //连接的准备阶段会将nummber赋值为0
    //连接的初始化阶段会将number赋值20，然后赋值10，为按顺序赋值
    private static int number = 10;


    static class Father {
        public static int A = 1;
        static {
            A = 2;
        }
    }

    static class Son extends Father{
        public static int B = A;//B的值是2，因为先执行Father类的clinit
    }

}
