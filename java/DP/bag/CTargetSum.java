package DP.bag;

import java.util.Arrays;

/**
 * @ClassName CTargetSum
 * @Description 目标和
 * @Author tiger
 * @Date 2025/12/23 11:01
 */
public class CTargetSum {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println("数组nums" + Arrays.toString(nums) + "的目标和为" + target + "的方案数为：" + solve(nums, target));
    }

    public static int solve(int[] nums, int target){
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        int bigSize  = (sum + target) / 2;
        if((sum + target) % 2 != 0)return 0;

        // 初始化dp
        int[] dp = new int[bigSize + 1];
        dp[0] = 1;

        // 遍历
        for(int i = 0; i < nums.length; i++){
            for(int j = bigSize; j >= nums[i]; j--){
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }

        return dp[bigSize];
    }
}
