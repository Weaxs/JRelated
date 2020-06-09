package multiThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义互斥锁
 */
public class MyMutex implements Lock {

    //聚合自定义同步器
    private final MySync sync = new MySync();

    // 静态内部类  自定义同步器
    private static class MySync extends AbstractQueuedSynchronizer {

        /**
         * 尝试获取锁
         */
        @Override
        protected boolean tryAcquire(int arg) {
            //调用AQS提供的方法， 通过CAS保证原子性
            // status为 0 表示锁空闲
            if (compareAndSetState(0, arg)) {
                // 标记获取到的同步状态(更新state成功)的线程
                //主要是为了判断是否可重入
                setExclusiveOwnerThread(Thread.currentThread());
                //获取同步状态成功  返回true
                return true;
            }
            // 获取同步状态失败 返回false
            return false;
        }

        /**
         * 尝试释放锁
         */
        @Override
        protected boolean tryRelease(int arg) {
            //没有锁却让释放，抛出IMSE
            if (getState() == 0)
                throw new IllegalMonitorStateException();

            // 可以释放 清空当前线程
            setExclusiveOwnerThread(null);
            // 将state设置回初始态
            setState(0);
            return true;
        }

        /**
         * 是否是独占持有
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 等待-通知机制
        // 每个condition都有一个与之对应的等待队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        // 阻塞式的获取锁  调用同步器模板方法独占式 获取同步状态
        // 底层调用了tryAcquire
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // 调用同步器模板方法可中断式获取同步状态
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        // 调用自己重写的方法  非阻塞式获取同步状态 (获取锁失败后返回false而非阻塞)
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // 调用同步器模板方法，可响应中断和超时时间限制
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        // 释放锁
        // 底层调用了tryRelease
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        // 使用自定义的条件
        return sync.newCondition();
    }
}
