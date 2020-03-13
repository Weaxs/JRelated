package _58_lengthOfLastWord;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 *
 *  
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 *
 */
public class Solution {

    public int lengthOfLastWord(String s) {
        if (s == null)
            return 0;
        int length = 0;
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0;i--) {
            if (chars[i] == ' ' && length > 0)
                break;
            else if (chars[i] != ' '){
                length++;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLastWord(""));
    }

}
