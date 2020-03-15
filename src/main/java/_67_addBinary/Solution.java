package _67_addBinary;

import java.math.BigInteger;

/**
 * 二进制求和
 *
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * 逐个相加的传统写法不在重复写了
 *
 * "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101"
 * "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"
 */
public class Solution {

    //1.使用内置函数？
    //当a,b足够大时，转换不成Integer
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    //2.位操作
    private String addBinary2(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);

    }

    public static void main(String[] args) {

    }

}
