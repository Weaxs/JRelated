package redSpider.basic;


/**
 *
 *
 * // Thread.State 源码
 * public enum State {
 *     NEW,
 *     RUNNABLE,
 *          Thread.start()
 *          Object.notify()
 *          Object.notifyAll()
 *          LockSupport.unpark(Thread)
 *     BLOCKED,
 *     WAITING,
 *          Object.wait()：使当前线程处于等待状态直到另一个线程唤醒它；
 *          Thread.join()：等待线程执行完毕，底层调用的是Object实例的wait方法；
 *          LockSupport.park()：除非获得调用许可，否则禁用当前线程进行线程调度。
 *     TIMED_WAITING,   超时等待
 *          Thread.sleep(long millis)：使当前线程睡眠指定时间；
 *          Object.wait(long timeout)：线程休眠指定时间，等待期间可以通过notify()/notifyAll()唤醒；
 *          Thread.join(long millis)：等待当前线程最多执行millis毫秒，如果millis为0，则会一直执行；
 *          LockSupport.parkNanos(long nanos)： 除非获得调用许可，否则禁用当前线程进行线程调度指定时间；
 *          LockSupport.parkUntil(long deadline)：同上，也是禁止线程进行调度指定时间；
 *     TERMINATED;
 * }
 *
 * 线程中断：
 *      Thread.interrupt()：中断线程。这里的中断线程并不会立即停止线程，而是设置线程的中断状态为true（默认是flase）；
 *      Thread.interrupted()：测试当前线程是否被中断。调一次设为 true,两次变 false
 *      Thread.isInterrupted()：测试当前线程是否被中断。不影响中断状态
 *
 */
public class ThreadStatus {

    public static void main(String[] args) {
        ThreadStatus threadStatus = new ThreadStatus();
//        threadStatus.bloacked2Runnable();`
//        threadStatus.waiting2Runnable();
        threadStatus.timedWaiting2Runnable();
    }

    /**
     * 一是在测试方法blockedTest()内还有一个main线程，二是启动线程后执行run方法还是需要消耗一定时间的
     */
    private void bloacked2Runnable() {
        Thread a = new Thread(this::testMethod, "a");
        Thread b = new Thread(this::testMethod, "b");
        a.start();
        //加上之后a线程输出TIMED_WAITING，因为start后需要一段时间才执行run方法，但是此时A线程还没有被sleep，所以是RUNNABLE
//        try {
//            Thread.sleep(1000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        b.start();
        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }

    private void waiting2Runnable() {
        Thread a = new Thread(this::testMethod, "a");
        Thread b = new Thread(this::testMethod, "b");
        a.start();
        try {
            // Object.wait会释放锁
            a.join(); // 不会释放锁，等待当前线程完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.start();
        System.out.println(a.getName() + ":" + a.getState());// 输出 TERMINATED
        System.out.println(b.getName() + ":" + b.getState());
    }

    private void timedWaiting2Runnable() {
        Thread a = new Thread(this::testMethod, "a");
        Thread b = new Thread(this::testMethod, "b");
        a.start();
        try {
            a.join(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.start();
        System.out.println(a.getName() + ":" + a.getState());// 输出 TIEMD_WAITING
        System.out.println(b.getName() + ":" + b.getState());
    }

    // 同步方法抢夺锁 (锁实例对象)
    private synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
