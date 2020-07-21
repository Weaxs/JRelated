package redSpider.basic;

import java.util.stream.IntStream;

public class ThreadGroup {

    /**
     * 每个Thread必然存在于一个ThreadGroup中，Thread不能独立于ThreadGroup存在。
     * 执行main()方法线程的名字是main，如果在new Thread时没有显式指定，那么默认将父线程（当前执行new Thread的线程）线程组设置为自己的线程组。
     * ThreadGroup管理着它下面的Thread，ThreadGroup是一个标准的向下引用的树状结构，这样设计的原因是防止"上级"线程被"下级"线程引用而无法有效地被GC回收。
     */
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.threadGroupTest();

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
     * Java程序中对线程所设置的优先级只是给操作系统一个建议，操作系统不一定会采纳。
     * 而真正的调用顺序，是由操作系统的线程调度算法决定的。
     */
    private void threadPriority() {
        Thread a = new Thread();
        System.out.println("a默认的线程优先级：" + a.getPriority());
        Thread b = new Thread();
        // newPriority <= MAX_PRIORITY (10) && newPriority >= MIN_PRIORITY (1)
        b.setPriority(10);
        System.out.println("b设置过的线程优先级：" + b.getPriority());
    }

    private void threadPriorityWhetherUse() {
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(new T1());
            thread.setPriority(i);
            thread.start();
        });
    }

}

class T1 implements Runnable {

    @Override
    public void run() {
        System.out.println(String.format("当前执行的线程是：%s，优先级：%d", Thread.currentThread().getName(), Thread.currentThread().getPriority()));
    }
}
