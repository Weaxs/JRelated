package _5_longestPalindromicSubstring;


/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 */
public class Solution {

    /**
     * 解法一
     *
     * 时间复杂度O(n²）
     * 空间复杂度O(n²）
     */
    public String longestPalindrome(String s) {

        int length = s.length();
        boolean[][] p = new boolean[length][length];
        int maxLen = 0;
        String target = "";

        for (int len = 1; len <= length; len++) {//遍历长度
            for (int start = 0; start < length; start++) {//遍历起始点
                int end = start + len - 1;//定义结束点位置

                if (end >= length)   break;

                //len=1为同一点，len=2为相邻点，故加此条件
                p[start][end] = (len == 1 || len == 2 || p[start + 1][end - 1]) && s.charAt(start) == s.charAt(end);

                if (p[start][end] && len > maxLen) {
                    maxLen = len;//添加的话，放出最早的最长回文串；不添加，结果为最后的最长回文串
                    target = s.substring(start, end + 1);
                }
            }
        }

        return target;
    }

    public static void main(String[] args) {
        String s = "aidabcdfefdcbanqio";
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome2(s));
    }

    /**
     * 解法二
     * 由于P(i,j)=(P(i+1,j−1)&&S[i]==S[j])
     * 当i倒叙时  可以取出i+1
     * 当j倒叙时  可以取出j-1
     * 简而言之
     * 此时可以用一位数组布尔型存储p
     * 在计算第i行p(j)时，使用到p(j-1)还未被刷新，及还是第i+1行的p(j-1)
     *
     * 时间复杂度O(n²）
     * 空间复杂度O(n）
     */
    private String longestPalindrome2(String s) {
        String res = "";
        int length = s.length();
        boolean[] p = new boolean[length];

        for (int i = length - 1;i >= 0; i--){
            for (int j = length -1; j >= i; j--) {
                //j -i < 3 为同一/相邻字符
                p[j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || p[j - 1]);
                if (p[j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }

            }
        }

        return res;
    }

    /**
     * 解法三
     * 逐个选中一点/两点进行左右同时扩散
     *
     * 时间复杂度O(n²）
     * 空间复杂度O(1）
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    /**
     * 解法四：马拉车算法
     *
     */
    public String longestPalindrome4(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int i_mirror = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[i_mirror]);// 防止超出 R
            } else {
                P[i] = 0;// 等于 R 的情况
            }

            // 碰到之前讲的三种情况时候，需要利用中心扩展法
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // 判断是否需要更新 R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }

        }

        // 找出 P 的最大值
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标
        return s.substring(start, start + maxLen);
    }

    public String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret += "#" + s.charAt(i);
        ret += "#$";
        return ret;
    }


}
