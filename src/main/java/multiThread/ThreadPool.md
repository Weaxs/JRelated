> 线程池

    线程池的常见参数
    1.corePoolSize 核心线程数
        最小可以同时运行的线程数量。
    2.maximumPoolSize 最大线程池数
        当队列中存放的任务达到队列容量的时候，当前可以同时运行的线程数量变为最大线程数。
    3.workQueue 工作队列
        当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，新任务就会被存放在队列中。
        基于数组的有界阻塞队列  ArrayBlockingQueue
            底层采用数组来实现。一旦创建，容量不能改变。其并发控制采用可重入锁来控制，不管是插入操作还是读取操作，都需要获取到锁才能进行操作。当队列容量满时，尝试将元素放入队列将导致操作阻塞，尝试从一个空队列中取一个元素也会同样阻塞。
        基于链表的无界阻塞队列  LinkedBlockingQueue
            底层基于单向链表实现的阻塞队列。满足FIFO的特性。通常在创建LinkedBlockingQueue对象时，会指定其大小，如果未指定，容量等于Integer.MAX_VALUE。
        一个不缓存任务的阻塞队列  SynchronousQueue
        具有优先级的无界阻塞队列  PriorityBlockingQueue
            默认情况下元素采用自然顺序进行排序，也可以通过自定义类实现compareTo()方法来指定元素排序规则，或者初始化时通过构造器参数Comparator来指定排序规则。
            采用的是 ReentrantLock，队列为无界队列。
    4.keepAliveTime 线程存活时间
        当线程池中的线程数量大于 corePoolSize 的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了 keepAliveTime才会被回收销毁。
    5.unit 时间单位
        keepAliveTime参数的时间单位。
    6.threadFactory 线程工厂
        executor 创建新线程的时候会用到。
    7.handler 饱和策略/拒绝策略
        ThreadPoolExecutor.AbortPolicy          抛出RejectedExecutionException来拒绝新任务的处理(默认)。
        ThreadPoolExecutor.CallerRunsPolicy     调用执行自己的线程运行任务，也就是直接在调用execute方法的线程中运行(run)被拒绝的任务，如果执行程序已关闭，则会丢弃该任务。因此这种策略会降低对于新任务提交速度，影响程序的整体性能。如果您的应用程序可以承受此延迟并且你要求任何一个任务请求都要被执行的话，你可以选择这个策略。
        ThreadPoolExecutor.DiscardPolicy        不处理新任务，直接丢弃掉。
        ThreadPoolExecutor.DiscardOldestPolicy  此策略将丢弃最早的未处理的任务请求。
        
    线程池的工作流程:
    1.提交任务
    2.判断核心线程是否已满，没满则创建线程，满了则进行下一步
    3.判断等待队列是否已满，没满则加入队列，满了则进行下一步
    4.判断线程池是否已满，没满则创建线程，满了则进行下一步
    5.按照饱和策略处理
    
    Runnable 和 Callable
        Runnable接口不会返回结果或者抛出检查异常
        Callable接口可以，返回的结果包在Future类中
        
    execute 和 submit
        execute方法的参数为Runnable，所以提交之后不会返回结果
        submit方法的参数为Callable，提交的时候会返回结果，返回一个Future类
            (注:Runnable参数的submit方法会在内部调用newTaskFor将Runnable转换为Callable)
        
    shutdown  和 shutdownNow
        shutdown方法关闭线程池，线程池的状态变为SHUTDOWN。线程池不再接受新任务了，但是队列里的任务得执行完毕。
        shutdownNow方法关闭线程池，线程的状态变为STOP。线程池会终止当前正在运行的任务，并停止处理排队的任务并返回正在等待执行的List。
        
    isTerminated  和  isShutdown
        isShutDown当调用shutdown法后返回为true。
        isTerminated当调用shutdown方法后，并且所有提交的任务完成后返回为true
        