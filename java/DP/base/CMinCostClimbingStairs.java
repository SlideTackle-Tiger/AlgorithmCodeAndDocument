package DP.base;

/**
 * @ClassName DMinCostClimbingStairs
 * @Description 使用最小花费爬楼梯
 * @Author tiger
 * @Date 2025/12/20 14:37
 */
public class CMinCostClimbingStairs {
    public static void main(String[] args) {
        int[] cost = {1,100,1,1,1,100,1,1,100,1};
        System.out.println("爬到顶层需要的最小花费：" + solve(cost));
    }

    public static int solve(int[] cost){
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 0;
        for(int i = 2; i <= n ; i++ ){
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
