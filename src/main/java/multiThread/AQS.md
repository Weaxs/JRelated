> AQS (AbstractQueuedSynchronizer)

    如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。
    如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制
    这个机制AQS是用CLH队列锁实现的，即将暂时获取不到锁的线程加入到队列中
    
    AQS使用一个int成员变量来表示同步状态，通过内置的FIFO队列来完成获取资源线程的排队工作。
    AQS使用CAS对该同步状态进行原子操作实现对其值的修改。
    
    两种资源共享方式：
    Exclusive（独占）
        公平锁：按照线程在队列中的排队顺序，先到者先拿到锁
        非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的
    Share（共享）
    
    AQS提供的模板方法：
    isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
    tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
    tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
    tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
    tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
    
    AQS组件:
    Semaphore(信号量)-允许多个线程同时访问： 信号量可以指定多个线程同时访问某个资源。
    CountDownLatch （倒计时器）： 用来协调多个线程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行。
    CyclicBarrier(循环栅栏)： 主要应用场景和 CountDownLatch 类似。
                            让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
    
    
    
    
    示例 1：
    java.util.concurrent.Semaphore类  
    public Semaphore(int permits) {
            sync = new NonfairSync(permits);
        }
    public Semaphore(int permits, boolean fair) {
            sync = fair ? new FairSync(permits) : new NonfairSync(permits);
        }
    如上所示，在创建Semaphore类时需要传递一个信号量的值，这个值传给内部类并保存在AQS类中的state字段
    每次调用tryAcquire方法获取锁时可以传递信号量的值(不传默认是1)，对state字段减去当前线程信号量的值，如果大于0则获取到锁，否则返回
    类Semaphore中定义了一个内部类如下
    abstract static class Sync extends AbstractQueuedSynchronizer {
            
            Sync(int permits) {
                setState(permits);
            }
            final int getPermits() {
                return getState();
            }
            加锁的时候减去当前线程的信号量(默认为1)
            final int nonfairTryAcquireShared(int acquires) {
                for (;;) {
                    int available = getState();
                    int remaining = available - acquires;
                    if (remaining < 0 ||
                        compareAndSetState(available, remaining))
                        return remaining;
                }
            }
            释放锁的时候加上当前线程的信号量(默认为1)
            protected final boolean tryReleaseShared(int releases) {
                for (;;) {
                    int current = getState();
                    int next = current + releases;
                    if (next < current) // overflow
                        throw new Error("Maximum permit count exceeded");
                    if (compareAndSetState(current, next))
                        return true;
                }
            }
        }

    示例 2：
    java.util.concurrent.CountDownLatch类 
    public CountDownLatch(int count) {
            if (count < 0) throw new IllegalArgumentException("count < 0");
            this.sync = new Sync(count);
       }
    public void await() throws InterruptedException {
            sync.acquireSharedInterruptibly(1);
        }
    public boolean await(long timeout, TimeUnit unit)
            throws InterruptedException {
            return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
        }
    public void countDown() {
            sync.releaseShared(1)
        }
    AQS类中的对应方法
    public final void acquireSharedInterruptibly(int arg)
                throws InterruptedException {
            if (Thread.interrupted())
                throw new InterruptedException();
                
            //根据底下tryAcquireShared方法重写可知,state不为0时返回-1，则执行doAcquireSharedInterruptibly方法
            
            if (tryAcquireShared(arg) < 0)
                doAcquireSharedInterruptibly(arg);
        }
    public final boolean tryAcquireSharedNanos(int arg, long nanosTimeout)
                throws InterruptedException {
            if (Thread.interrupted())
                throw new InterruptedException();
                
            //根据底下tryAcquireShared方法重写可知,state不为0时返回-1则 || 前面是false，则执行doAcquireSharedInterruptibly方法
            
            return tryAcquireShared(arg) >= 0 ||
                doAcquireSharedNanos(arg, nanosTimeout);
        }
    private void doAcquireSharedInterruptibly(int arg)
            throws InterruptedException {
            final Node node = addWaiter(Node.SHARED);
            try {
                for (;;) {
                    final Node p = node.predecessor();
                    if (p == head) {
                        int r = tryAcquireShared(arg);
                        if (r >= 0) {
                            setHeadAndPropagate(node, r);
                            p.next = null; // help GC
                            return;
                        }
                    }
                    if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt())
                        throw new InterruptedException();
                }
            } catch (Throwable t) {
                cancelAcquire(node);
                throw t;
            }
        }
    如上图所示，CountDownLatch在创建的时候回传了一个count值代表state的初始值
    每次调用countDown方法会对state减1
    await()方法会对调用的线程进行挂起
    await(long timeout, TimeUnit unit)方法会在超时时间内对线程挂起，要么时间超市要么stae为0，如果超时则抛出异常
    在AQS内部的doAcquireSharedInterruptibly方法对线程死循环，直到state为0时进行返回，从而实现对线程的挂起
    
    类CountDownLatch中定义了一个内部类
    private static final class Sync extends AbstractQueuedSynchronizer {
    
            Sync(int count) {
                setState(count);
            }
    
            int getCount() {
                return getState();
            }
            //
            protected int tryAcquireShared(int acquires) {
                return (getState() == 0) ? 1 : -1;
            }
    
            protected boolean tryReleaseShared(int releases) {
                // Decrement count; signal when transition to zero
                for (;;) {
                    int c = getState();
                    if (c == 0)
                        return false;
                    int nextc = c - 1;
                    if (compareAndSetState(c, nextc))
                        return nextc == 0;
                }
            }
        }
    CountDownLatch （倒计时器）适用场景：
    1. 某一线程在开始运行前等待 n 个线程执行完毕。
        给count值设为null，对某一线程进行await，并在前n个线程执行完毕时调用countDown方法
    2.实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行
        初始化一个共享的 CountDownLatch 对象，将其计数器初始化为 1 ：new CountDownLatch(1)，多个线程在开始执行任务前首先 coundownlatch.await()
        在主线程调用countDown方法使多个线程同时开始执行
        
    CountDownLatch的不足：是一次性的，在state=0后这个CountDownLatch对象就不可用了