package _122_bestTimeToBuyAndSellStockII;

/**
 * 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class Solution {

    /**
     * 我们需要考虑到紧跟谷的每一个峰值以最大化利润。如果我们试图跳过其中一个峰值来获取更多利润，那么我们最终将失去其中一笔交易中获得的利润，从而导致总利润的降低。
     *
     * 例如，在上述情况下，如果我们跳过 peak_i和 valley_j
     * ​试图通过考虑差异较大的点以获取更多的利润，获得的净利润总是会小与包含它们而获得的静利润，因为 C 总是小于 A+B。
     *
     * 通俗的话讲波峰+波谷的组合总是大于 最大-最小
     */
    public int maxProfit(int[] prices) {
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;

        for (int i = 0;i < prices.length - 1;) {
            //寻找波谷 如果当前数字比后一个数字大 则跳过
            for (;i < prices.length - 1 && prices[i] >= prices[i + 1];i++);
            valley = prices[i];
            //寻找波峰
            for (;i < prices.length - 1 && prices[i] < prices[i + 1];i++);
            peak = prices[i];
            maxprofit += peak - valley;
        }

        return maxprofit;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {1,1,1,1,1};
        System.out.println(solution.maxProfit(prices));
    }

}
