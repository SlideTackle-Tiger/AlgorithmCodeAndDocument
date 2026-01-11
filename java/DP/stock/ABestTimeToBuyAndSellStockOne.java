package DP.stock;

/**
 * @author flash
 * @version 1.0
 * @description: 买卖股票的最佳时机一
 * @date 2026/1/1 11:19
 */
public class ABestTimeToBuyAndSellStockOne {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println("买股票的最佳价值:" + solve(prices));
    }

    public static int solve(int[] prices){
        int len = prices.length;
        // 初始化dp数组
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];dp[0][1] = 0;
        printDP(0,dp);

        // 遍历
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i-1][0] + prices[i]);
            printDP(i,dp);
        }
        return dp[len - 1][1];
    }

    public static void printDP(int day,int[][] dp){
        System.out.println("当前天数：" + day);
        for(int i = 0; i < dp.length;i++){
            System.out.println(dp[i][0] + "," + dp[i][1]);
        }
    }
}
