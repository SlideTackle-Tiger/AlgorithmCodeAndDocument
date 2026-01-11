package DP.stock;

/**
 * @author flash
 * @version 1.0
 * @description: 买卖股票的最佳时机二，现在你可以多次买入售出股票了
 * @date 2026/1/1 13:35
 */
public class BBestTimeToBuyAndSellStockTwo {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println("买股票的最佳价值:" + solve(prices));
    }

    public static int solve(int[] prices){
        int len = prices.length;
        // 初始化dp
        int[][] dp = new int[len][2];
        dp[0][0] = 0 - prices[0]; dp[0][1] = 0;

        // 遍历
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];
    }
}
