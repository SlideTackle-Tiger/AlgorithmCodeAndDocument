package DP.stock;

/**
 * @author flash
 * @version 1.0
 * @description: 买卖股票的最大价值3，你最多可以完成两笔交易
 * @date 2026/1/1 14:52
 */
public class CBestTimeToBuyAndSellStockThree {


    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println("可以买卖两次股票的最大价值为：" + solve(prices));
    }

    public static int solve(int[] prices){
        // 初始化dp数组
        int len = prices.length;
        int[][] dp = new int[len][5];
        dp[0][0] = 0;dp[0][1] = 0 - prices[0];dp[0][2] = 0; dp[0][3] = 0 - prices[0];dp[0][4] = 0;

        // 遍历
        for(int i = 1; i < len;i++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        // 思考？我们应该返回什么 第一次卖出 和第二次卖出的最大值，但由于第二次卖出已经包含了第一次卖出的状态（传递）我们直接返回第二次卖出的价格即可。
        return dp[len - 1][4];
    }
}
