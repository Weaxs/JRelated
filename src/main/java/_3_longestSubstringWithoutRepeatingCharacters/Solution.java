package _3_longestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
