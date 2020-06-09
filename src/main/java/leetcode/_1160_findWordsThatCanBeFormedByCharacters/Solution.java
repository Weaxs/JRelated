package leetcode._1160_findWordsThatCanBeFormedByCharacters;

import java.util.HashMap;
import java.util.Map;

/**
 * 拼写单词
 *
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 *
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 *
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 *
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 *
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 */
public class Solution {

    //解法一：哈希表计数
    //时间复杂度：O(n)，其中 n 为所有字符串的长度和。我们需要遍历每个字符串，包括 chars 以及数组 words 中的每个单词。
    //空间复杂度：O(S)，其中 S 为字符集大小，在本题中 S 的值为 26（所有字符串仅包含小写字母）。程序运行过程中，最多同时存在两个哈希表，使用的空间均不超过字符集大小 S，因此空间复杂度为 O(S)
    public int countCharacters(String[] words, String chars) {
        int ans = 0;
        //记chars的key-value计数
        Map<Character, Integer> charCounts = new HashMap<>();
        for (char ch:chars.toCharArray()) {
            Integer count = charCounts.getOrDefault(ch, 0);
            charCounts.put(ch, ++count);
        }

        for (String word:words) {
            //记word的key-value计数
            Map<Character, Integer> wordCount = new HashMap<>();
            for (Character wordCh:word.toCharArray()) {
                Integer count = wordCount.getOrDefault(wordCh, 0);
                wordCount.put(wordCh, ++count);
            }
            Boolean isMatch = true;
            //比较word中的字母和chars中的字母数
            for (Map.Entry<Character, Integer> entry:wordCount.entrySet()) {
                if (charCounts.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch)
                ans += word.length();

        }

        return ans;
    }

    //解法二：哈希表计数
    //思路同解法一，但中间少次循环
    private int countCharacters2(String[] words, String chars) {
        int ans = 0;
        Map<Character, Integer> charCounts = new HashMap<>();
        for (char ch:chars.toCharArray()) {
            Integer count = charCounts.getOrDefault(ch, 0);
            charCounts.put(ch, ++count);
        }

        for (String word:words) {
            //创建哈希表为charCounts的表
            Map<Character, Integer> wordCount = new HashMap<>(charCounts);
            Boolean isMatch = true;
            //遍历word中的字母
            //如果匹配到了map则对应的value值减一
            //如果匹配不到或者匹配到的value为0，则说明chars缺少字母，直接false结束循环
            for (Character wordCh:word.toCharArray()) {
                Integer count = wordCount.getOrDefault(wordCh, 0);
                if (count == 0) {
                    isMatch = false;
                    break;
                } else {
                    wordCount.put(wordCh, --count);
                }
            }

            if (isMatch)
                ans += word.length();

        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"hello","world","leetcode"};
        System.out.println(solution.countCharacters2(words, "welldonehoneyr"));
    }

}
