> HashMap
        
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
        如果相同的话，直接覆盖，不相同就通过拉链法解决冲突
    注：当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，
       将链表转化为红黑树，以减少搜索时间。
            
    JDK 1.8的hash方法(扰动函数)
    static final int hash(Object key) {
          int h;
          // key.hashCode()：返回散列值也就是hashcode
          // ^ ：按位异或
          // >>>:无符号右移，忽略符号位，空位都以0补齐
          return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
      }
      
    数组下标的计算方法是“ (n - 1) & hash”
    取余(%)操作中如果除数是2的幂次则等价于与其除数减一的与(&)操作（也就是说hash%length==hash&(length-1)的前提是length是2的n次方；）。” 
    并且采用二进制位操作&，相对于%能够提高运算效率，这就解释了HashMap的长度为什么是 2 的幂次方。
      
    HashMap扩容
        当hashmap中的元素个数超过数组大小*loadFactor时，就会进行数组扩容，loadFactor的默认值为0.75
        也就是说，默认情况下，数组大小为16，那么当hashmap中元素个数超过16*0.75=12的时候，就把数组的大小扩展为2*16=32，

> ConcurrentHashMap

    底层同HashMap,数组 + 链表/红黑二叉树
    
    采用 CAS 和 synchronized 来保证并发安全
    synchronized 只锁定当前链表或红黑二叉树的首节点，这样只要 hash 不冲突，就不会产生并发，效率又提升 N 倍。
     