package _20_validParentheses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class Solution {

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        stack.push('0');
        for (char ch:s.toCharArray()) {
            switch (ch) {
                case ')':
                    if ('(' == stack.pop())   break;
                    else return false;
                case '}':
                    if ('{' == stack.pop())   break;
                    else return false;

                case ']':
                    if ('[' == stack.pop())   break;
                    else return false;
                default:
                    stack.push(ch);

            }
        }

        if (stack.peek() == '0')
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid(")[]}"));
    }

    public boolean isValid2(String s) {
        Map<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');

        Stack<Character> stack = new Stack<Character>();

        for (char c:s.toCharArray()) {
            if (mappings.containsKey(c)) {

                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }


}
