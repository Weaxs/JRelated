> # TreadLocal
>简介

    ThreadLocal类主要解决的就是让每个线程绑定自己的值。
    如果你创建了一个ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的本地副本，这也是ThreadLocal变量名的由来。他们可以使用 get（） 和 set（） 方法来获取默认值或将其值更改为当前线程所存的副本的值，从而避免了线程安全问题。
    例如在用户访问的时候可以将用户信息放在ThreadLocal中，确保每个用户线程绑定自己用户信息的值。
    
> 原理

    public class Thread implements Runnable {
     ......
    //与此线程有关的ThreadLocal值。由ThreadLocal类维护
    ThreadLocal.ThreadLocalMap threadLocals = null;
    
    //与此线程有关的InheritableThreadLocal值。由InheritableThreadLocal类维护
    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
     ......
    }
    从上面Thread类 源代码可以看出Thread类中有一个threadLocals和一个inheritableThreadLocals量，它们都是ThreadLocalMap类型的变量,我们可以把 ThreadLocalMap理解为ThreadLocal类实现的定制化的HashMap。
    默认情况下这两个变量都是null，只有当前线程调用ThreadLocal类的set或get方法时才创建它们，实际上调用这两个方法的时候，我们调用的是ThreadLocalMap类对应的get()、set()方法。
    
    public T get() {
            Thread t = Thread.currentThread();
            ThreadLocalMap map = getMap(t);
            if (map != null) {
                ThreadLocalMap.Entry e = map.getEntry(this);
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    T result = (T)e.value;
                    return result;
                }
            }
            return setInitialValue();
        }
    public void set(T value) {
            Thread t = Thread.currentThread();
            ThreadLocalMap map = getMap(t);
            if (map != null) {
                map.set(this, value);
            } else {
                createMap(t, value);
            }
        }
    ThreadLocal类的set或get方法中
    会使用Thread.currentThread()调用获取当前线程，并拿到当前线程中的threadLocals，对其进行get/set操作
    所以在同一线程中声明两个ThreadLocal，会使用Tread内部都是用仅有的那个ThreadLocalMap(效果一样)

    static class Entry extends WeakReference<ThreadLocal<?>> {
                /** The value associated with this ThreadLocal. */
                Object value;
    
                Entry(ThreadLocal<?> k, Object v) {
                    super(k);
                    value = v;
                }
            }
    ThreadLocalMap底层是一个Entry内部类数组(类似HashMap)
    Entry中的key只能是ThreadLocal对象
            value是一个Object类
    Entry类继承了WeakRefrence弱引用类，但只有key弱引用
    
    比如我们在同一个线程中声明了两个 ThreadLocal 对象的话，会使用 Thread内部都是使用仅有那个ThreadLocalMap 存放数据的
    ThreadLocalMap的 key 就是 ThreadLocal对象，value 就是 ThreadLocal 对象调用set方法设置的值。
    
> ThreadLocal 内存泄露问题

    上面提到ThreadLocalMap中使用的key为ThreadLocal的弱引用,而value是强引用。
    所以，如果ThreadLocal没有被外部强引用的情况下，在垃圾回收的时候key会被清理掉，而value不会被清理掉。
    这样一来，ThreadLocalMap中就会出现key为null的Entry。假如我们不做任何措施的话，value永远无法被GC回收，这个时候就可能会产生内存泄露。
    
    所以使用完ThreadLocal方法后最好手动调用remove()方法。
    
    