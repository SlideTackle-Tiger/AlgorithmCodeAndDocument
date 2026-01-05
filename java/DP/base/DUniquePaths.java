package DP.base;

/**
 * @ClassName DUniquePaths
 * @Description 独特路径
 * @Author tiger
 * @Date 2025/12/20 15:27
 */
public class DUniquePaths {
    public static void main(String[] args) {
        int m = 3; int n = 7;
        System.out.println("从左上角到右下角一共有路径：" + solve(m,n) + "种");
    }
    public static int solve(int m , int n){
        int[][] dp = new int[m][n];
        // 初始化
        for(int i =0;i<m;i++){dp[i][0] = 1;}; // 初始化第一列
        for(int j =0; j < n; j++){dp[0][j] = 1;}// 初始化第一行
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i -1][j] + dp[i][j -1];
            }
        }
        return dp[m - 1][n -1];
    }
}
