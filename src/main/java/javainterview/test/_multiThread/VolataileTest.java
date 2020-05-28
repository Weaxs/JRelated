package javainterview.test._multiThread;

/**
 * volatile只保证可见性
 * 仅适用于以下两个场景
 *
 * 1.运算结果不依赖变量的当前值，或者能够确保只有单一的修改变量的值
 * 2.变量不需要与其他的状态变量共同参与不变约束
 *
 * volatile还可以禁止指令重排序优化
 *
 * 指令重排序是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理
 */
public class VolataileTest {

    private static volatile int race = 0;

    private  static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT;i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i < 10000; i++)
                        increase();
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1)
            Thread.yield();

        //预期结果应该是200000，但是输出结果每次都不一样且小于200000
        //当有多个线程同时进行修改时会出现以下操作
        //getstatic指令把race值取到操作栈顶，volatile关键字保证了race值在此时是正确的
        //但在执行iconst_l、iadd这些指令的时候，其他线程可能已经把race的值加大了，而在操作栈顶的值就变成了过期的数据
        //之后putstatic指令执行后会把较小的race值同步回主存中
        System.out.println(race);


    }

}
