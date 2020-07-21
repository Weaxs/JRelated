package redSpider.basic;

import java.util.stream.IntStream;

public class ThreadGroupTest {

    /**
     * 每个Thread必然存在于一个ThreadGroup中，Thread不能独立于ThreadGroup存在。
     * 执行main()方法线程的名字是main，如果在new Thread时没有显式指定，那么默认将父线程（当前执行new Thread的线程）线程组设置为自己的线程组。
     * ThreadGroup管理着它下面的Thread，ThreadGroup是一个标准的向下引用的树状结构，这样设计的原因是防止"上级"线程被"下级"线程引用而无法有效地被GC回收。
     */
    public static void main(String[] args) {
        ThreadGroupTest threadGroup = new ThreadGroupTest();
        threadGroup.threadPriorityWhetherUse();

    }

    private void threadGroupTest() {
        Thread thread = new Thread(() -> {
            System.out.println("thread当前线程组名称：" + Thread.currentThread().getThreadGroup().getName());
            System.out.println("thread当前线程名称：" + Thread.currentThread().getName());
        });

        thread.start();
        System.out.println("main方法线程的名称：" + Thread.currentThread().getName());
    }

    /**
     * 设置优先级 及 默认优先级
     */
    private void threadPriority() {
        Thread a = new Thread();
        System.out.println("a默认的线程优先级：" + a.getPriority());
        Thread b = new Thread();
        // newPriority <= MAX_PRIORITY (10) && newPriority >= MIN_PRIORITY (1)
        b.setPriority(10);
        System.out.println("b设置过的线程优先级：" + b.getPriority());
    }

    /**
     * Java程序中对线程所设置的优先级只是给操作系统一个建议，操作系统不一定会采纳。
     * 而真正的调用顺序，是由操作系统的线程调度算法决定的。
     *
     * 线程的调度策略采用抢占式，优先级高的线程比优先级低的线程会有更大的几率优先执行。
     * 在优先级相同的情况下，按照“先到先得”的原则。
     * 每个Java程序都有一个默认的主线程，就是通过JVM启动的第一个线程main线程。
     */
    private void threadPriorityWhetherUse() {
        IntStream.range(1, 11).forEach(i -> {
            Thread thread = new Thread(new T1());
            thread.setPriority(i);
            thread.start();
        });
    }

    private void threadAndGroupPriority() {
        ThreadGroup threadGroup = new ThreadGroup("t1") {
            // 在线程成员抛出unchecked exception
            // 会执行此方法
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                super.uncaughtException(t, e);
            }
        };
        threadGroup.setMaxPriority(6);
        Thread thread = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {

            }
        }, "thread");
        thread.setPriority(9);
        System.out.println("我是线程组的优先级"+threadGroup.getMaxPriority());
        System.out.println("我是线程的优先级"+thread.getPriority());
    }

}

class T1 implements Runnable {

    @Override
    public void run() {
        System.out.println(String.format("当前执行的线程是：%s，优先级：%d", Thread.currentThread().getName(), Thread.currentThread().getPriority()));
    }
}
