package DP.bag;

/**
 * @ClassName PPerfectSquares
 * @Description 完全平方数
 * @Author tiger
 * @Date 2025/12/25 13:32
 */
public class PPerfectSquares {
    public static void main(String[] args) {
        int n = 12;
        System.out.println( n + "可以由" +solve(n) + "个最少完全平方数组合而来");
    }
    public static int solve(int n){
        // 初始化dp
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        // 遍历
        for(int i = 1; i * i <= n ; i++){ // 物品
            for(int j = i * i; j <= n; j ++){ // 背包
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        return dp[n];
    }
}
