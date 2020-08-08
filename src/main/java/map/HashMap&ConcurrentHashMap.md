## HashMap
        
HashMap底层是  数组 + 链表
    
    Node<K,V>[] table;
    static class Node<K,V> implements Map.Entry<K,V> {
            final int hash;
            final K key;
            V value;
            Node<K,V> next;
    }
    
key通过hashcode扰动函数处理后得到Hash值，然后通过 (n - 1) & hash 判断当前元素存放的位置（这里的 n 指的是数组的长度）
    

如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同

如果相同的话，直接覆盖，不相同就通过拉链法解决冲突(1.8之后是尾插法，插到链表的尾部)

注：当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间。
            
    JDK 1.8的hash方法(扰动函数)
    static final int hash(Object key) {
          int h;
          // key.hashCode()：返回散列值也就是hashcode
          // ^ ：按位异或
          // >>>:无符号右移，忽略符号位，空位都以0补齐
          return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
      }
      
    数组下标的计算方法是 (n - 1) & hash
    取余(%)操作中如果除数是2的幂次则等价于与其除数减一的与(&)操作（也就是说hash%length==hash&(length-1)的前提是length是2的n次方；）。
    并且采用二进制位操作&，相对于%能够提高运算效率，这就解释了HashMap的长度为什么是 2 的幂次方。
      
### HashMap扩容

当hashmap中的元素个数超过数组大小*loadFactor时，就会进行数组扩容，loadFactor的默认值为0.75。
也就是说，默认情况下，数组大小为16，那么当hashmap中元素个数超过16*0.75=12的时候，就把数组的大小扩展为2*16=32，

### JDK1.7中的线程不安全 (头插法)

 在扩容中有一个transfer方法，同时由于1.7的尾插法

当并发执行扩容操作时会造成环形链和数据丢失的情况

### JDK1.8中的线程不安全 (尾插法)

1. 在put方法中插入

if ((p = tab[i = (n - 1) & hash]) == null)判断了是否发生Hash冲突
    
假设两个线程A、B都在进行put操作，并且hash函数计算出的插入下标是相同的，当线程A执行完第六行代码后由于时间片耗尽导致被挂起，而线程B得到时间片后在该下标处插入了元素，完成了正常的插入，然后线程A获得时间片，由于之前已经进行了hash碰撞的判断，所有此时不会再进行判断，而是直接进行插入，这就导致了线程B插入的数据被线程A覆盖了，从而线程不安全。

2. put方法中size的计算

put方法中的++size对HashMap的size进行了++

假设两个线程A、B，这两个线程同时进行put操作时，假设当前HashMap的zise大小为10，当线程A执行到第38行代码时，从主内存中获得size的值为10后准备进行+1操作，但是由于时间片耗尽只好让出CPU，线程B快乐的拿到CPU还是从主内存中拿到size的值10进行+1操作，完成了put操作并将size=11写回主内存，然后线程A再次拿到CPU并继续执行(此时size的值仍为10)，当执行完put操作后，还是将size=11写回内存，此时，线程A、B都执行了一次put操作，但是size的值只增加了1，所有说还是由于数据覆盖又导致了线程不安全。

## ConcurrentHashMap

底层同HashMap,数组 + 链表/红黑二叉树
    
采用 CAS 和 synchronized 来保证并发安全

synchronized 只锁定当前链表或红黑二叉树的首节点，这样只要 hash 不冲突，就不会产生并发，效率又提升 N 倍。
     