package multiThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 死锁
 *
 * 1.互斥条件：该资源任意一个时刻只由一个线程占用。
 * 2.请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * 3.不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
 * 4.循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
 */
public class DeadLockTest {

    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue(10000));

    private void deadLockByPoolExecutor() {
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (resource1) {
                    System.out.println(resource1);
                    synchronized (resource2) {
                        System.out.println(resource2);
                    }
                }
            }
        });

        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (resource2) {
                    System.out.println(resource2);
                    synchronized (resource1) {
                        System.out.println(resource1);
                    }
                }
            }
        });
    }

    private void deadLockByThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource1) {
                    System.out.println(resource1);
                    synchronized (resource2) {
                        System.out.println(resource2);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource2) {
                    System.out.println(resource2);
                    synchronized (resource1) {
                        System.out.println(resource1);
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {

    }

}
