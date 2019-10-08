package _3_longestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 例如abcdefdsa
     * 先从a开始进入双层for循环至abcdef后
     * 出现重复，使用flag记录重复节点的位置，例如如上例子
     * 可以让i快速地从3位置开始
     *
     */
    public int lengthOfLongestSubstring(String s) {
        int count = 0;
        char[] charr = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> chs = new HashMap<Character, Integer>();
            //用来快速跳转下一个i的索引
            int flag = i;
            for (int j = i; j < s.length(); j++) {
                if (chs.containsKey(charr[j])) {
                    flag = j;
                    break;
                }
                chs.put(charr[j], j);
            }
            i = chs.get(charr[flag]);
            count = count < chs.size()?chs.size():count;
        }

        return count;
    }

    public static void main(String args[]) {
        Solution solution = new Solution();
        int count = solution.lengthOfLongestSubstring(" asd asd");
        System.out.println(count);
    }


    /**
     * 官方答案
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<Character>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }


}
