package DP.base;

/**
 * @ClassName DFibonacci
 * @Description 斐波那契数列
 * @Author tiger
 * @Date 2025/12/17 17:07
 */
public class AFibonacci {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("第n个斐波那契数（动态规划）：" + solveByDP(n));
        System.out.println("动态规划进行状态压缩：" + solveByDPTow(n));
        System.out.println("斐波那契数列递归方法：" + solveByRecursion(n));
    }

    public static int solveByDP(int n){
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int solveByDPTow(int n){
        int dp0 = 0;int dp1 = 1; int sum = dp0 + dp1;
        for(int i = 2; i <= n ; i++){
            sum = dp0 + dp1;
            dp0 = dp1;
            dp1 = sum;
        }
        return sum;
    }

    public static int solveByRecursion(int n){
        if(n < 2){return n;}
        return solveByRecursion(n - 1) + solveByRecursion(n - 2);
    }
}
