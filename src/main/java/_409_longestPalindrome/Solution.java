package _409_longestPalindrome;

/**
 * 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class Solution {

    //贪心算法
    public int longestPalindrome(String s) {
        //大小写区分所以128
        int[] counts = new int[128];

        for (char ch:s.toCharArray()) {
            counts[ch]++;
        }
        int length = 0;

        for (int count:counts) {
            //先除为了先整除成整数
            length += count / 2 * 2;
            //如果length是偶数，且count是奇数，length + 1
            if (count % 2 == 1 && length % 2 == 0)
                length++;
        }
        return length;

    }

    public static void main(String args[]) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("abccccdd"));
    }

}
