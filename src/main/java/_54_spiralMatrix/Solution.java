package _54_spiralMatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 *
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
            return ans;
        int row = matrix.length;
        int col = matrix[0].length;
        //用来保存具体的矩阵节点有没有被加入过
        boolean[][] seen = new boolean[row][col];
        //di代表顺时针循环的方向(共向右、向下、向左、向上四个方向)
        int r = 0, c = 0, di = 1;
        //这种循环一圈会多循环4次，因为都是到达边界后，对r和c的值进行校正
        for (int i = 0; i < row * col; ) {
            if (0 <= r && r < row && 0 <= c && c < col && !seen[r][c]) {
                ans.add(matrix[r][c]);
                seen[r][c] = true;
                i++;
                switch (di % 4) {
                    case 1:
                        c++;
                        break;
                    case 2:
                        r++;
                        break;
                    case 3:
                        c--;
                        break;
                    case 0:
                        r--;
                        break;
                }
            } else {
                switch (di % 4) {
                    case 1:
                        //向右超出边界后会多加1所以减去，同时加r让他到下一个元素去
                        c--;r++;
                        break;
                    case 2:
                        //向下超出边界后r会多加1所以减去，同时减c让他到下一个元素去
                        r--;c--;
                        break;
                    case 3:
                        //向左超出边界后c会多减1所以加上，同时减r让他到下一个元素去
                        c++;r--;
                        break;
                    case 0:
                        //向上超出边界后r会多减1所以加上，同时加c让他到下一个元素去
                        r++;c++;
                        break;
                }
                di++;
            }

        }
        return ans;
    }

    /**
     * 模拟,官方答案1
     * 基本类似于上面的方式，只不过又添加两个4个元素的数组来替换上面的switch
     * 防止边界超出
     * 当然上面的方法也可以在不加两个数组的情况下改成这样
     */
    private List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            //cr和cc代表下一个元素，在循环到边界最后一个元素的时候即可检测到已经到边界
            //不用向上面的方法对超过边界的值进行校正
            int cr = r + dr[di];
            int cc = c + dc[di];
            //下一个元素没超出边界，则把cr和cc的值给r和c
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                r = cr;
                c = cc;
            //下一个元素超出边界，则直接用r和c计算下一个元素的值
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }

    /**
     * 按层模拟
     */
    private List < Integer > spiralOrder3(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0)
            return ans;
        //第一层行和列的边界值
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        //r1=r2时，还有一行数据；c1=c2时，还有一列数据(先按行输出第一个，然后按列向下);r1=r2 && c1=c2时，还有一个数据
        while (r1 <= r2 && c1 <= c2) {
            //向右输出一行
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            //向下输出一行
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);

            if (r1 < r2 && c1 < c2) {
                //向左输出一行
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                //向下输出一行
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            //缩小一圈，循环里层
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    public static void main (String args[]) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        Solution solution = new Solution();
        System.out.println(solution.spiralOrder3(matrix));
    }

}
