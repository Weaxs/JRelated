package redSpider.basic;

public class ThreadRunnnableTest {

    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        new Thread(new MyRunnable()).start();

        // Java 8 函数式编程，可以省略MyThread类
        new Thread(() -> {
            System.out.println("Java 8 匿名内部类");
        }).start();

        // new Thread(ThreadGroup g, Runnable target, String name,long stackSize, AccessControlContext acc, boolean inheritThreadLocals)
        //g：线程组，指定这个线程是在哪个线程组下
        //target：指定要执行的任务
        //name：线程的名字，多个线程的名字是可以重复的。如果不指定名字
        //inheritThreadLocals：继承ThreadLocals



    }

}
// Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread");
    }
}
// Runnable
class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("MyRunnable");
    }
}