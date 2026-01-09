package DP.bag;

/**
 * @ClassName ECoinChangeTwo
 * @Description 零钱兑换2
 * @Author tiger
 * @Date 2025/12/24 14:45
 */
public class ECoinChangeTwo {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("共有" + solve(coins,amount) + "种方法可以凑成总金额");
    }

    public static int solve(int[] coins, int amount){
        // 初始化dp
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        // 遍历
        for(int i = 0; i < coins.length; i++){ // 物品
            for(int j = coins[i]; j <= amount; j++){ // 背包容量
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        return dp[amount];
    }
}
