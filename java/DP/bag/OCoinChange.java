package DP.bag;

/**
 * @ClassName OCoinChange
 * @Description 零钱兑换
 * @Author tiger
 * @Date 2025/12/25 12:30
 */
public class OCoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2 , 5};
        int amount = 11;
        System.out.println("金额" + amount + "的最少硬币个数为：" + solve(coins, amount));
    }

    public static int solve(int[] coins, int amount){
        // 初始化dp数组
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        // 遍历
        for(int i = 0; i < coins.length; i++){
            for(int j = 0; j <= amount; j ++){
                if(j >= coins[i] && dp[j - coins[i]] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
