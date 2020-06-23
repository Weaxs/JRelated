package leetcode._171_excelSheetColumnNumber;

public class Solution {

    public int titleToNumber(String s) {
        int sum = 0;
        for (char ch:s.toCharArray()) {
            sum = sum * 26 + (ch - 'A') + 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.titleToNumber("AMJ"));
    }

}