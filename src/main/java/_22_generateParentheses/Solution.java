package _22_generateParentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 *
 * "",0,0,3
 * 递归1
 * "(",1,0,3
 * 此处衍生出递归11和递归12
 * 递归11: "((",2,0,3
 * 此处衍生出递归111和递归112
 * 递归111:"(((",3,0,3
 * 此处衍生出递归1112,递归11122,递归111222  返回到递归112 "((()))"
 * 递归112:"(()",2,1,3
 * 此处衍生出递归1121和递归1122
 * 递归1121:"(()(",3,1,3
 * 此处衍生出递归11212和递归112122  返回到递归1122 "(()())"
 * 递归1122:"(())",2,2,3
 * 此处衍生出递归11221和递归112212，返回到递归12 "(())()"
 * 递归12:"",1,1,3
 * 此处衍生出递归121:"()(",2,1,3并衍生出递归1211和递归1212
 * 递归1211:"()((",3,1,3
 * 此处递归衍生出递归12112和递归121122 返回到递归1212 "()(())"
 * 递归1212:"()()",2,2,3
 * 此处递归衍生出递归12121和递归121212 返回到递归1 "()()()"
 */
public class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }

    public static void main(String args[]) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(3));
    }


}
