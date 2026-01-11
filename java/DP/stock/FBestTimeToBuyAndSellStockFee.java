package DP.stock;

/**
 * @ClassName FBestTimeToBuyAndSellStockFee
 * @Description
 * @Author tiger
 * @Date 2026/1/2 14:26
 */
public class FBestTimeToBuyAndSellStockFee {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println("包含手续费的最大现金为：" + solve(prices,fee));
    }

    public static int solve(int[] prices, int fee){
        int len = prices.length;
        // 初始化dp
        int[][] dp = new int[len][2];
        dp[0][0] = 0 - prices[0]; // 买入状态
        dp[0][1] = 0; // 卖出状态

        // 遍历
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }

        // 返回最大现金
        return dp[len - 1][1];
    }
}
