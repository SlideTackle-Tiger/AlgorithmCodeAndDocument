package DP.base;

/**
 * @ClassName DClimbingStairs
 * @Description 爬楼梯
 * @Author tiger
 * @Date 2025/12/20 14:14
 */
public class BClimbingStairs {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(n +"阶台阶一共有" + solve(n) + "种方案");
    }

    public static int solve(int n){
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n ; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
