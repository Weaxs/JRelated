> # 单例模式

>定义
    
    保证一个类仅有一个实例，并提供一个访问它的全局访问点。

    对于频繁使用的对象，可以省略创建对象所花费的时间，这对于那些重量级对象而言，是非常可观的一笔系统开销；
    由于new操作的次数减少，因而对系统内存的使用频率也会降低，这将减轻GC压力，缩短GC停顿时间。
    
> 单例模式的实现

    1.饿汉方式 (线程安全)
    指全局的单例实例在类装载时构建
    JVM在加载这个类时就马上创建此唯一的单例实例，不管你用不用，先创建了再说，如果一直没有被使用，便浪费了空间，典型的空间换时间，每次调用的时候，就不需要再判断，节省了运行时间。
    
    public class Singleton {
           //在静态初始化器中创建单例实例，这段代码保证了线程安全
            private static Singleton uniqueInstance = new Singleton();
            //Singleton类只有一个构造方法并且是被private修饰的，所以用户无法通过new方法创建该对象实例
            private Singleton(){}
            public static Singleton getInstance(){
                return uniqueInstance;
            }
        }
        
    枚举式:
    public enum Singleton {
    	 //定义一个枚举的元素，它就是 Singleton 的一个实例
        INSTANCE;  
        
        public void doSomeThing() {  
    	     System.out.println("枚举方法实现单例");
        }  
    }
        
    2.懒汉方式
    指全局的单例实例在第一次被使用时构建
    单例实例在第一次被使用时构建，而不是在JVM在加载这个类时就马上创建此唯一的单例实例。
    
    非线程安全和synchronized关键字线程安全版本:
    public class Singleton {  
          private static Singleton uniqueInstance;  
          private Singleton (){
          }   
          //没有加入synchronized关键字的版本是线程不安全的
          public static Singleton getInstance() {
              //判断当前单例是否已经存在，若存在则返回，不存在则再建立单例
    	      if (uniqueInstance == null) {  
    	          uniqueInstance = new Singleton();  
    	      }  
    	      return uniqueInstance;  
          }  
     }

    双重检查加锁版本:
    利用双重检查加锁（double-checked locking），首先检查是否实例已经创建，如果尚未创建，“才”进行同步。这样以来，只有一次同步，这正是我们想要的效果。
    public class Singleton {
    
        //volatile保证，当uniqueInstance变量被初始化成Singleton实例时，多个线程可以正确处理uniqueInstance变量
        private volatile static Singleton uniqueInstance;
        private Singleton() {
        }
        public static Singleton getInstance() {
           //检查实例，如果不存在，就进入同步代码块
            if (uniqueInstance == null) {
                //只有第一次才彻底执行这里的代码
                synchronized(Singleton.class) {
                   //进入同步代码块后，再检查一次，如果仍是null，才创建实例
                    if (uniqueInstance == null) {
                        uniqueInstance = new Singleton();
                    }
                }
            }
            return uniqueInstance;
        }
    }
    
    登记式/静态内部类方式:
    public class Singleton {  
        private static class SingletonHolder {  
        private static final Singleton INSTANCE = new Singleton();  
        }  
        private Singleton (){}  
        public static final Singleton getInstance() {  
        return SingletonHolder.INSTANCE;  
        }  
    }
    
    