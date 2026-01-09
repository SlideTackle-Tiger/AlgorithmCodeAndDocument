package DP.bag;

import java.util.Arrays;

/**
 * @ClassName FCombinationSumIV
 * @Description 组合总和四
 * @Author tiger
 * @Date 2025/12/24 16:41
 */
public class FCombinationSumIV {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println("数组：" + Arrays.toString(nums) + " 组合成 " + target +"共有：" + solve(nums, target) +"种组合");
    }
    public static int solve(int[] nums, int target){
        // 定义dp数组
        int[] dp = new int[target + 1];
        // 初始化
        dp[0] = 1;

        // 遍历
        for(int j = 0; j <= target; j++){
            for(int i = 0 ; i < nums.length && j- nums[i] >= 0; i++){
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
