package leetcode.Interview._01._06.compressStringlcci;

/**
 * 字符串压缩
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 * 示例1:
 *
 *  输入："aabcccccaaa"
 *  输出："a2b1c5a3"
 * 示例2:
 *
 *  输入："abbccd"
 *  输出："abbccd"
 *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * 提示：
 *
 * 字符串长度在[0, 50000]范围内。
 *
 *
 */
public class Solution {

    //没什么好说的，不难反正是
    private String compressString(String S) {
        if (S.length() < 1)
            return S;

        char firstCh = S.charAt(0);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (char ch:S.toCharArray()) {
            if (ch == firstCh) {
                count++;
            } else {
                sb.append(firstCh);
                sb.append(count);
                count = 1;
                firstCh = ch;
            }
        }
        sb.append(firstCh);
        sb.append(count);
        return sb.toString().length()>=S.length()?S:sb.toString();
    }

    public static void main(String args[]) {
        Solution solution = new Solution();
        System.out.println(solution.compressString("aa"));
    }
}
