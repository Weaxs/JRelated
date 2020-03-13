package _169_majorityElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 */
public class Solution {

    //解法一：用哈希表记录每个元素的次数 时间复杂度：O(n), 空间复杂度 O(n)
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num:nums) {
            if (counts.get(num) == null)
                counts.put(num, 1);
            else
                counts.replace(num, counts.get(num) + 1);
        }

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();

    }

    //解法二： 排序法 时间复杂度O(nlogn) 空间复杂度 O(1)
    //因为出现次数大于 ⌊ n/2 ⌋ ，故排序后的中间位置一定是次元素
    private int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    //解法三：Boyer-Moore 投票算法
    //nums:      [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    //candidate:  7  7  7  7  7  7   5  5   5  5  5  5   7  7  7  7
    //count:      1  2  1  2  1  0   1  0   1  2  1  0   1  2  3  4
    //
    //
    //
    private int majorityElement3(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num:nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (candidate == num)?1:-1;
        }

        return candidate;

    }

    public static void main(String args[]) {
        Solution solution = new Solution();
        int[] nums = {3,3,2};
        System.out.println(solution.majorityElement(nums));
    }

}
