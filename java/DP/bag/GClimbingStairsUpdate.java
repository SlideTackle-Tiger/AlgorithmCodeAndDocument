package DP.bag;

/**
 * @ClassName GClimbingStairsUpdate
 * @Description 爬楼梯进阶版
 * @Author tiger
 * @Date 2025/12/24 17:45
 */
public class GClimbingStairsUpdate {
    public static void main(String[] args) {
        int n = 3;
        int m = 2;
        System.out.println(n +"层的台阶，每次上1-" + m+ "步" + "共有" + sovle(n, m) + "种方案");
    }

    public static int sovle(int n, int m){
        // n 台阶，m 每一次可以上的步数

        // 构造物品数组
        int[] weight = new int[m];
        for(int i = 0; i < m ;i ++){
            weight[i] = i + 1;
        }

        // 初始化dp
        int[] dp = new int[n + 1];
        dp[0] = 1;

        // 遍历, 排列问题先背包再物品
        for(int j = 0; j <= n; j++){
            for(int i = 0; i < m; i++){
                // 防止步数超过台阶数量
                if(j - weight[i] >=0){
                    dp[j] = dp[j] + dp[j - weight[i]];
                }
            }
        }
        return dp[n];
    }
}
