package redSpider.basic;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.stream.IntStream;

/**
 * 顺序执行
 */
public class LockSynchronizedTest {



}

// 1. 对象锁
class ObjectLock {
    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("Thread A " + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(10);       // 防止B先执行
        new Thread(new ThreadB()).start();
    }
}

// 2. 通知等待机制
// 等待/通知机制使用的是使用同一个对象锁
// 如果你两个线程使用的是不同的对象锁，那它们之间是不能用等待/通知机制通信的。
// sleep方法是不会释放当前的锁的，而wait方法会
class WaitAndNotify {
    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i ++) {
                    try {
                        System.out.println(("ThreadA: " + i));
                        lock.notify();  // 叫醒B
                        lock.wait();    // 等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();  //叫醒B
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        System.out.println("ThreadB: " + i);
                        lock.notify();  // 叫醒A
                        lock.wait();    // 等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();  //叫醒A
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }
}

// 3. 信号量
class Signal {
    private static volatile int singal = 0;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (singal < 5) {
                if (singal % 2 == 0) {
                    System.out.println("ThreadA: " + singal);
                    singal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            while (singal < 5) {
                if (singal % 2 == 1) {
                    System.out.println("threadB: " + singal);
                    singal = singal + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }
}

// 4. 管道
class Pipe {
    static class ReaderThread implements Runnable {

        private PipedReader reader;

        public ReaderThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            try {
                while ((receive = reader.read()) != -1) {
                    System.out.println((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable {
        private PipedWriter pipedWriter;

        public WriterThread (PipedWriter pipedWriter) {
            this.pipedWriter = pipedWriter;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            int receive = 0;
            try {
                pipedWriter.write("test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //线程ReaderThread开始执行，
    //线程ReaderThread使用管道reader.read()进入”阻塞“，
    //线程WriterThread开始执行，
    //线程WriterThread用writer.write("test")往管道写入字符串，
    //线程WriterThread使用writer.close()结束管道写入，并执行完毕，
    //线程ReaderThread接受到管道输出的字符串并打印，
    //线程ReaderThread执行完毕。
    public static void main(String[] args) throws IOException, InterruptedException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        writer.connect(reader);     // 读写管道一定要连接

        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(1000);
        new Thread(new WriterThread(writer)).start();

    }
}
