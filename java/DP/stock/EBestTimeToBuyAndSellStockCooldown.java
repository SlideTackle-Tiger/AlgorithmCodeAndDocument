package DP.stock;

/**
 * @ClassName EBestTimeToBuyAndSellStockCooldown
 * @Description 买卖股票的最佳时机涵冷冻期
 * @Author tiger
 * @Date 2026/1/2 11:46
 */
public class EBestTimeToBuyAndSellStockCooldown {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println("含有冷静期的最大金额为：" + solve(prices));

    }

    public static int solve(int[] prices){
        int len = prices.length;
        // 初始化dp
        int[][] dp = new int[len][4];
        dp[0][0] = 0 - prices[0]; // 买入状态
        dp[0][1] = 0; // 卖出后保持状态
        dp[0][2] = 0; // 卖出状态
        dp[0][3] = 0; // 冷静期状态

        // 遍历
        for(int i = 1; i < len; i ++){
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][1], dp[0][2]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2];
        }

        // 返回
        return Math.max(dp[len - 1][1], Math.max(dp[len - 1][2], dp[len - 1][3]));

    }
}
