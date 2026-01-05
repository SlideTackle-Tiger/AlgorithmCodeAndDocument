package DP.base;

/**
 * @ClassName DUniquePathsTwo
 * @Description 不同路径二
 * @Author tiger
 * @Date 2025/12/20 16:07
 */
public class EUniquePathsTwo {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};// 1表示障碍
        System.out.println("共有路径" + solve(obstacleGrid));
    }

    public static int solve(int[][] obstacleGrid){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][ n - 1] == 1)return 0;
        for (int i = 0; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }
        for (int j = 0; j < n ; j++){
            if(obstacleGrid[0][j] == 1){
                break;
            }
            dp[0][j] = 1;
        }
        // 开始遍历
        for(int i = 1; i < m ; i++){
            for(int j = 1; j < n ; j++){
                if(obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j -1];
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
