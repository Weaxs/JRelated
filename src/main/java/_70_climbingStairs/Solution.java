package _70_climbingStairs;

public class Solution {

    //解法一：递归暴力解决
    public int climbStairs(int n) {
//        if (n == 1)
//            return n;
        return climbStairs(n, 0);
    }

    private int climbStairs(int n, int upNum) {

        if (n - upNum == 0)
            return 1;
        else if (n - upNum < 0) {
            return 0;
        }
        return climbStairs(n - upNum, 2) + climbStairs(n - upNum, 1);
    }

    //解法二：动态规划  时间/空间复杂度  O(n)
    //第 ii 阶可以由以下两种方法得到：
    //
    //在第 (i-1)(i−1) 阶后向上爬一阶。
    //
    //在第 (i-2)(i−2) 阶后向上爬 22 阶。
    //
    //所以到达第 i 阶的方法总数就是到第 (i-1)(i−1) 阶和第 (i-2)(i−2) 阶的方法数之和
    //以此从第1阶和第2阶类推
    private int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i= 3; i <= n;i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //解法三：斐波那契数列   时间复杂度O(n)   空间复杂度 O(1)
    // Fib(n)=Fib(n−1)+Fib(n−2)
    private int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n;i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    //解法四：斐波那契公式
    private int climbStairs4(int n) {
        double sqrt5=Math.sqrt(5);
        double fibn=Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
        return (int)(fibn/sqrt5);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs(3));
    }

}
