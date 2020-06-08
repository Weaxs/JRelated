package _125_validPalindrome;

/**
 * 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 */
public class Solution {

    public boolean isPalindrome(String s) {
        s = s.toUpperCase();
        char[] chs = s.toCharArray();

        for (int i = 0, j = chs.length - 1; i < j;i++,j--) {
            //i过滤字母以及数字以外的字符
            for (;i < chs.length && !(chs[i] >= 'A' && chs[i] <='Z') && !(chs[i] >= '0' && chs[i] <= '9');i++);
            //j过滤字母以及数字以外的字符
            for (;j > 0 && !(chs[j] >= 'A' && chs[j] <='Z') && !(chs[j] >= '0' && chs[j] <= '9');j--);

            if (i < chs.length && j > 0 && chs[i] != chs[j])
                return false;
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        String str = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        //去除非字母和数字
        for(char c : str.toCharArray()){
            if(Character.isLetterOrDigit(c))    sb.append(c);
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.isPalindrome("A man,bbb .,<><?a plan, a canal: Pabbb ()@#$%nama"));
        System.out.println(solution.isPalindrome(".21312,"));
    }

}
