package _11_containerWithMostWater;

/**
 * 盛最多水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class Solution {

    /**
     * 双层遍历
     */
    public int maxArea(int[] height) {
        int contain = 0;

        for (int i = 0;i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int tmp = (j-i) * Math.min(height[i], height[j]);

                if (tmp > contain)
                    contain = tmp;
            }
        }

        return contain;
    }

    public static void main(String args[]) {
        int[] height = {8,6,5,4,8,3,7};
        Solution solution = new Solution();
        System.out.println(solution.maxArea2(height));
    }

    /**
     * 双指针一层遍历
     */
    public int maxArea2(int[] height) {
        int contain = 0;

        for (int i = 0,j=height.length - 1;i < j; ) {

            contain = Math.max((j - i) * Math.min(height[i], height[j]), contain);
            if (height[i] > height[j])
                j--;
            else
                i++;
        }

        return contain;
    }

}
