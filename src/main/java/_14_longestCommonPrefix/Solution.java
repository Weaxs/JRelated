package _14_longestCommonPrefix;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 */
public class Solution {

    public String longestCommonPrefix(String[] strs) {
        String common = "";
        int j = 1;
        if (strs == null || strs.length < 1)    return common;
        boolean exitLoop = true;
        for (;;) {
            if (j > strs[0].length()){
                break;
            }
            String commonPre = strs[0].substring(0, j);
            boolean isCommon = true;
            for(String str:strs) {
                if (j > str.length()){
                    isCommon = false;
                    break;
                }
                if (!str.substring(0, j).equals(commonPre)) {
                    isCommon = false;
                    break;
                }
            }
            if (isCommon) {
                common = commonPre;
                j++;
            } else {
                break;
            }
        }

        return common;
    }

    public static void main(String args[]) {
        Solution solution = new Solution();
        String[] a = {"asdjhwinsd","baodq","cahr"};
        System.out.println(solution.longestCommonPrefix2(a));
    }


    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }


}
