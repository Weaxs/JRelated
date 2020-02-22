package _49_groupAnagrams;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 */
public class Solution {

    /**
     * 方法一：排序数组分类
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> strsMap = new HashMap<>();

        for (String str:strs) {
            //转成数组排序，这样不管是ate,tea等，均变为aet
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = String.valueOf(chs);
            //不包含就代表是一个新组
            if (!strsMap.containsKey(key))
                strsMap.put(key, new ArrayList<>());
            strsMap.get(key).add(str);
        }
        return new ArrayList<>(strsMap.values());
    }

    /**
     * 方法二：按计数分类
     */
    private List<List<String>> groupAnagrams2(String[] strs) {
        //判空
        if (strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> ans = new HashMap<>();
        //分别代表a-z 26个字母
        int[] count = new int[26];
        for (String str:strs) {
            //每次一个新的字符串时初始化count数组
            Arrays.fill(count, 0);
            //循环字符串上的字母并对count赋值
            for (char c: str.toCharArray())
                count[c - 'a']++;

            //注释这部分为leetcode官方答案，无非是把int数组转换为String，并在每个数组中间插入了一个#
//            StringBuilder sb = new StringBuilder("");
//            for (int i = 0; i < 26; i++) {
//                sb.append('#');
//                sb.append(count[i]);
//            }
//            String key = sb.toString();

            //阿里的fastjson，讲对象的值转换为字符串
            String key = JSON.toJSONString(count);
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList<>());
            ans.get(key).add(str);
        }
        return new ArrayList<>(ans.values());
    }

    public static void main(String[] agrs) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution solution = new Solution();
        System.out.println(solution.groupAnagrams2(strs) + "\n");
    }

}
