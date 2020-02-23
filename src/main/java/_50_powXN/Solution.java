package _50_powXN;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 */
public class Solution {

    public double myPow(double x, int n) {
        //如果n是负数，则将x,n替换为1/x，-n
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        double ans = 1;
        //循环乘n遍x
        for (long i = 0; i < n; i++)
            ans = ans* x;
        return ans;
    }

    private double myPow2(double x, int n) {
        //如果n是负数，将x,n替换为1/x,-n
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x, n);
    }

    //通过得到x^(n/2)，然后让x^(n/2)*x^(n/2)的递归调用，如果n/2可以整除，则x^(n/2)*x^(n/2)，如果n/2不能整除，则x^(n/2)*x^(n/2)*x
    //递归的起点是x^0=1
    private double fastPow(double x, long n) {

        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public static void main (String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myPow2(2.00000 ,-2147483648));
    }

}
