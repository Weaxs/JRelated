package javainterview.test._multiThread;

import java.util.concurrent.*;

public class SynchronizedTest {

    private int a = 0;
    private int b = 0;
    private static int c = 0;
    private int d = 0;
    private int e = 0;
    private Integer f = 0;

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue(10000));
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                test.addA();
            }
        });
        //因为addA中有sleep，且
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //实例方法的同步，只锁同一实例的
//                new Test().addB();//不会因为等待addA方法执行而被锁住
                test.addB();//会因为等待addA方法执行而被锁住
            }
        });
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //实例方法的锁和静态方法的锁不一样
                //实例方法对实例加锁，静态方法对类加锁
                SynchronizedTest.addC();
            }
        });
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //addD用的是实例的锁，所以需要等待a的执行
                test.addD();
            }
        });
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //addE用的是类的锁，所以需要等待c的执行
                test.addE();
            }
        });
        //不论此处是new Runnable()还是new Callable，submit方法中的newTaskFor方法都会把它变成Callable
        Future<?> future = poolExecutor.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return test.addF();
            }
        }
//        new Runnable() {
//            @Override
//            public void run() {
//                test.addF();
//            }
//        }
        );
        System.out.println(future);


    }

    private synchronized void addA() {
        System.out.println("a = " + a);
        a++;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("a = " +a);
    }

    private synchronized void addB() {
        b++;
        System.out.println("b = " +b);
    }

    private static synchronized void addC() {
        System.out.println("c = " +c);
        c++;
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("c = " +c);
    }

    private void addD() {
        synchronized (this) {
            d++;
        }
        System.out.println("d = " +d);
    }

    private void addE() {
        synchronized (SynchronizedTest.class) {
            e++;
        }
        System.out.println("e = " +e);
    }

    private Integer addF() {
        synchronized (f) {
            f++;
        }
        System.out.println("f = " +f);
        return f;
    }

}
