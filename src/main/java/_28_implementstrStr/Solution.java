package _28_implementstrStr;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 */
public class Solution {

    /**
     * String类的indexOf方法
     */
    public int strStr(String haystack, String needle) {
        int max = haystack.length() - needle.length();
        if (needle.length() == 0) {
            return 0;
        }
        char[] haystacks = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        for (int i = 0;i <= max;i++) {
            if (haystacks[i] != needles[0] ) {
                continue;
            }
            int j = i + 1;
            int end = i + needle.length();
            for (int k = 1;j < end && haystacks[j] == needles[k] ;k++,j++);
            if (j == end)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.strStr("","ll"));

    }

    /**
     * KMP算法
     *
     * KMP 属于典型的牺牲空间换取时间的算法，要评价它的好坏，得判断这些牺牲的空间值不值。
     *
     * 目的：减少重新匹配的次数，让主串遍历永不回头
     *
     * 方法：通过利用已有的匹配信息，借助已匹配串的前缀与后缀关系，在重新匹配时跳过已有的前缀
     *
     * 适用：从它的方法原理我们可以看出，pattern 串的中间必须出现与其前缀相同的内容，这个算法才能够派上用场，出现重复的越多，就越有价值，因此像匹配 橡胶橡胶、chop-chop、恍恍惚惚、win-win 这种叠词较为适合，而最为适合的情景是匹配二进制串（都是 0101 的重复）；
     *
     * 缺陷：现实中，中间内容与前缀相同的单词、词汇并不多见，而长句更是除了排比句之外就很少见了，因此，在花费时间空间生成了有限状态机之后，很有可能会出现一直都是重置状态而很少降价状态的情况出现。对于长句而言，状态机所占用的空间是巨大的，而并不高效，相反纯暴力解法对于短 pattern 串。而言，总体运行时间却并不比它慢😂。
     *
     */
    public int strStr2(String haystack, String needle) {
        int strLen = haystack.length(), subLen = needle.length();
        if (subLen == 0) return 0;
        if (strLen == 0) return -1;
        // 构建状态机
        int[][] FSM = new int[subLen][256];
        int X = 0, match = 0;
        for (int i = 0; i < subLen; i++) {
            match = (int) needle.charAt(i);
            for (int j = 0; j < 256; j++) {
                // 当前状态 + 匹配失败字符 = 孪生词缀状态 + 匹配字符
                FSM[i][j] = FSM[X][j];
            }
            FSM[i][match] = i + 1;
            if (i > 1) {
                // 下一孪生前缀状态 = X + match
                X = FSM[X][match];
            }
        }
        // 匹配子串
        int state = 0;
        for (int i = 0; i < strLen; i++) {
            state = FSM[state][haystack.charAt(i)];
            if (state == subLen) {
                return i - subLen + 1;
            }
        }
        return -1;
    }

}
