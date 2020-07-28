package map;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap底层是  数组 + 链表
 * key通过hashcode扰动函数处理后得到Hash值
 */
public class HashMapTest {

    private void mapLoop() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", 2);
        map.put("c", null);

        // 在 Map.Entry循环时，生成一个EntryIterator类，继承自HashIterator，HashIterator类中有一个变量expectedModCount
        // 当在内部put后，expectedModCount != modCount
        // HashIterator类 nextNode方法中 判断 expectedModCount == modCount
        //如果不相等  则抛出了 java.util.ConcurrentModificationException 异常
        for (Map.Entry<String, Object> entry:map.entrySet()) {
            if ("a".equals(entry.getKey())) {
                map.put("d", entry.getValue());// put()之后 会++modCount
            }
        }
        System.out.println(JSON.toJSON(map));
    }

    public static void main(String[] args) {
        HashMapTest mapTest = new HashMapTest();
        mapTest.mapLoop(); // java.util.ConcurrentModificationException
    }

}
