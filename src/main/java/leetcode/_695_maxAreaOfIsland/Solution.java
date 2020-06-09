package leetcode._695_maxAreaOfIsland;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 岛屿的最大面积
 *
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 */
public class Solution {

    //解法一：深度优先搜索DFS (递归)  时间复杂度：O(R * C)   空间复杂度：O(R * C) (栈空间)
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0;i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == 1)
                    res = Math.max(res, dfs(i, j, grid));
        }
        return res;

    }

    //深度优先
    private int dfs(int i, int j, int[][] grid) {
        //越界
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0)
            return 0;
        //置空搜寻过的岛
        grid[i][j] = 0;
        int num = 1;
        num += dfs(i + 1, j ,grid);//下面的点
        num += dfs(i - 1, j ,grid);//上面的点
        num += dfs(i, j + 1 ,grid);//右面的点
        num += dfs(i, j - 1, grid);//左边的点

        return num;

    }

    //解法二：深度优先搜索DFS + 栈  时间复杂度：O(R * C)   空间复杂度：O(R * C)
    private int maxAreaOfIsland2(int[][] grid) {
        int res = 0;
        Deque<Ponit> stack = new LinkedList<>();
        for (int i = 0;i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++)
                if (grid[i][j] == 1) {
                    int curr_ans = 0;
                    stack.push(new Ponit(i, j));
                    for (;!stack.isEmpty();) {
                        Ponit tmp = stack.pop();
                        int curr_i = tmp.getI();
                        int curr_j = tmp.getJ();
                        if (curr_i < 0 || curr_j < 0 || curr_i == grid.length || curr_j == grid[i].length || grid[curr_i][curr_j] == 0)
                            continue;
                        curr_ans++;
                        grid[curr_i][curr_j] = 0;
                        stack.push(new Ponit(curr_i + 1, curr_j));
                        stack.push(new Ponit(curr_i - 1, curr_j));
                        stack.push(new Ponit(curr_i, curr_j + 1));
                        stack.push(new Ponit(curr_i, curr_j - 1));

                    }
                    res = Math.max(res, curr_ans);
                }

        }
        return res;

    }

    //解法三  广度遍历 + 队列
    private int maxAreaOfIsland3(int[][] grid) {
        int res = 0;
        Deque<Ponit> queue = new LinkedList<>();
        for (int i = 0;i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
//                if (grid[i][j] == 1) {
                    int curr_ans = 0;
                    queue.offer(new Ponit(i, j));
                    for (;!queue.isEmpty();) {
                        Ponit tmp = queue.poll();
                        int curr_i = tmp.getI();
                        int curr_j = tmp.getJ();
                        if (curr_i < 0 || curr_j < 0 || curr_i == grid.length || curr_j == grid[i].length || grid[curr_i][curr_j] != 1)
                            continue;
                        curr_ans++;
                        grid[curr_i][curr_j] = 0;
                        queue.offer(new Ponit(curr_i + 1, curr_j));
                        queue.offer(new Ponit(curr_i - 1, curr_j));
                        queue.offer(new Ponit(curr_i, curr_j + 1));
                        queue.offer(new Ponit(curr_i, curr_j - 1));

                    }
                    res = Math.max(res, curr_ans);
                }

        }
        return res;

    }


    public static void main(String args[]) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        Solution solution = new Solution();
        System.out.println(solution.maxAreaOfIsland3(grid));
    }

}

class Ponit {
    private Integer i;
    private Integer j;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    Ponit(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }
}
