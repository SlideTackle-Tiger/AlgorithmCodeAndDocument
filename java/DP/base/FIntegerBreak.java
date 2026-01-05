package DP.base;

/**
 * @ClassName DIntegerBreak
 * @Description 整数拆分
 * @Author tiger
 * @Date 2025/12/21 14:55
 */
public class FIntegerBreak {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("整数拆分后最大值为：" + solve(n));
    }

    public static int solve(int n){
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 0; dp[2] = 1;
        for(int i = 3; i <= n; i++){
            for(int j = 1; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
