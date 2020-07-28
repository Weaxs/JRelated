package map;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    private void mapLoop() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("a", "1");
        map.put("b", 2);
//        map.put("c", null); // java.lang.NullPointerException

        for (Map.Entry<String, Object> entry:map.entrySet()) {
            if ("a".equals(entry.getKey())) {
                map.put("d", entry.getValue());
            }
        }
        System.out.println(JSON.toJSON(map));
    }

    public static void main(String[] args) {
        ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();
        concurrentHashMapTest.mapLoop();
    }
}
