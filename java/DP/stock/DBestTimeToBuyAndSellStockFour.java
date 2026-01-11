package DP.stock;

/**
 * @author flash
 * @version 1.0
 * @description: 买股票的最佳时机四，可以至多进行k次交易
 * @date 2026/1/1 16:17
 */
public class DBestTimeToBuyAndSellStockFour {
    public static void main(String[] args) {
        int[] prices =  {3,2,6,5,0,3};
        int k = 2;
        System.out.println("最多进行" + k + "次交易的最大价值为：" + solve(prices,k));
    }
    public static int solve(int[] prices, int k){
        //构造dp数组
        int len = prices.length;
        int d = 2 * k + 1;
        int[][] dp = new int[len][d];
        for(int i = 1; i < 2 * k; i = i +2){
            dp[0][i] = 0 - prices[0];
        }
        // 遍历
        for(int i = 1; i < len; i++){
            // 遍历维度d
            dp[i][0] = dp[i][0];
            for(int j = 1; j < d ;j++){
                if(j % 2 == 1){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }

            }
        }

        return dp[len - 1][ 2* k ];
    }
}
