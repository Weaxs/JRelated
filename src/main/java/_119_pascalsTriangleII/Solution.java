package _119_pascalsTriangleII;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 杨辉三角 II
 *
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 */
public class Solution {

    //动态规划
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0)
            return new ArrayList<>();
        Deque<List<Integer>> stack = new LinkedList<>();
        List<Integer> firstLayer = new ArrayList<>();
        firstLayer.add(1);
        stack.push(firstLayer);
        for (int i = 1;i <= rowIndex; i++) {
            List<Integer> layerPre = stack.poll();
            List<Integer> layer = new ArrayList<>();
            layer.add(1);
            for (int j = 1;j < i;j++) {
                layer.add(layerPre.get(j) + layerPre.get(j-1));
            }
            layer.add(1);
            stack.push(layer);
        }
        return stack.poll();
    }

    //1 1 1 -> 1 2 1
    //1 2 1 1 -> 1 2 3 1 -> 1 3 3 1
    //1 3 3 1 1 —> 1 3 3 4 1 -> 1 3 6 4 1 -> 1 4 6 4 1
    //以此类推
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i <= rowIndex; i++){
            res.add(1);
            for(int j = i - 1; j > 0; j--){
                res.set(j, (res.get(j) + res.get(j - 1)));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getRow(0));
    }

}
