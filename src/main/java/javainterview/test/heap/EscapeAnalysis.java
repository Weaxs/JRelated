package javainterview.test.heap;

/**
 * 逃逸分析
 *
 * JDK7之后默认开启逃逸分析
 * -XX:+DoEscapeAnalysis 显式开启逃逸分析
 * -XX:+PrintEscapeAnalysis 查看逃逸分析的筛选结果
 *
 * 关注new的对象实体是否会在方法外被调用
 *
 * 对JVM的优化
 * ① 栈上分配
 *      根据逃逸分析结果，如果对象没有逃逸出方法，有可能被优化成栈上发配，线程结束，栈空间被回收
 * ② 同步省略 （锁消除）
 *      判断同步快锁使用的锁对象是否只能够被一个线程访问而没有被发布到其他线程
 *      则JIT编译器在编译同步块时会取消对这部分代码的同步
 *
 *
 */
public class EscapeAnalysis {

    public EscapeAnalysis obj;

    /**
     *  方法返回EscapeAnalysis对象，发生逃逸
     */
    public EscapeAnalysis getInstance() {
        return obj == null? new EscapeAnalysis() : obj;
    }

    /**
     * 为成员属性赋值，发生逃逸
     */
    public void setObj() {
        this.obj = new EscapeAnalysis();
    }

    /**
     * 对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useEscapeAnalysis() {
        EscapeAnalysis e = new EscapeAnalysis();
    }

    /**
     * 引用成员变量的值，发生逃逸
     * new的是obj
     */
    public void useObj() {
        EscapeAnalysis e = getInstance();
    }

}

//栈上分配说明
class StackAllocation {

    public void f() {
        for (int i = 0;i < 1000000; i++) {
            alloc();
        }
    }

    private void alloc() {
        StackAllocation allocation = new StackAllocation();
    }

}


//同步省略说明
class SynchronizedTest {

    public void f() {
        Object hollis = new Object();
        synchronized (hollis) {
            System.out.println(hollis);
        }
//        类似于
//        Object hollis = new Object();
//        System.out.println(hollis);
    }

}
