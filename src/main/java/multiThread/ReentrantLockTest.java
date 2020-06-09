package multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    /**
     * 1. finally中释放锁，保证在或得到锁后，最终能被释放
     * 2.在try外获取锁
     *      ① 在获取锁失败的时候，不用经过finally释放锁
     *      ② 在获取锁失败时，执行到finally，如果别的线程恰好获取了锁，会被释放掉
     */
    private void lock() {
        // Lock范式
        Lock lock = new ReentrantLock();
        // ”等同于“ synchronized 的 moniterenter指令
        lock.lock();
        try {

        } finally {
            // "等同于" synchronized 的 moniterexit指令
            lock.unlock();
        }

    }

}
