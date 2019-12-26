package _38_countAndSay;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 报数
 *
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 *
 */
public class Solution {

    /**
     * 使用递归调用
     * n==1时为1
     * 其余时候进行递归调用计数
     */
    private String countAndSay(int n) {
        if(n == 1) return "1";
        int count = 1;
        StringBuilder res = new StringBuilder();
        String str = countAndSay(n-1);
        for(int i = 0;i < str.length();i++){
            if(i + 1 < str.length() && str.charAt(i) == str.charAt(i+1)){ //计算相同数字个数
                count++;
            } else {
                res.append(count);
                res.append(str.charAt(i));
                count = 1;
            }
        }
        return res.toString();
    }

    /**
     * 使用队列完成
     * 可以一看
     * 但我觉得还是递归通俗易懂
     */
    private String countAndSay2(int n) {
        StringBuilder res = new StringBuilder();
        int count = 0, tmp = 1 ,bt = 0 ,qsize = 1;
        Deque<Integer> que = new LinkedList<>();
        que.offer(1);
        for(int i = 2;i <=n ;i++){
            while(qsize > 0){
                if(que.getFirst() == tmp)
                    count++;
                else {
                    que.offer(count);
                    que.offer(bt);
                    count=1;
                    tmp = que.getFirst();
                }
                bt = que.getFirst();
                que.pop();
                qsize--;
            }
            que.offer(count);
            que.offer(bt);
            tmp = que.getFirst();
            count = 0;
            qsize = que.size();
        }
        while(!que.isEmpty()){
            res.append(que.getFirst());
            que.pop();
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay2(4));
    }

}
