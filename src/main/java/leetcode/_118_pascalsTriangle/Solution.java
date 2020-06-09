package leetcode._118_pascalsTriangle;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 *
 */
public class Solution {

    //类似与官方解答
    //动态规划
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0 )
            return ans;
        ans.add(new ArrayList<>());
        ans.get(0).add(1);

        for (int layerCount = 1; layerCount < numRows; layerCount++) {
            List<Integer> preLayer = ans.get(layerCount - 1);
            List<Integer> layer = new ArrayList<>();
            layer.add(1);
            //第二行不会进入循环，第一个不会进入，最后一个也不会
            //比如第5行，layerCount最大是4，count最大是3，循环只会加入123，没有0和4
            for (int count = 1; count < layerCount; count++) {
                layer.add(preLayer.get(count-1) + preLayer.get(count));
            }
            layer.add(1);
            ans.add(layer);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generate(5));
    }

}
