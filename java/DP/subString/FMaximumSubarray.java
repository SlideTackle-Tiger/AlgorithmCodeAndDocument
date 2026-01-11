package DP.subString;

import java.util.Arrays;

/**
 * @ClassName FMaximumSubarray
 * @Description 最大子序和
 * @Author tiger
 * @Date 2026/1/3 11:24
 */
public class FMaximumSubarray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("数组nums的最长连续子序列和为：" + solve(nums));
    }

    public static int solve(int[] nums){
        // 初始化dp
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // 遍历
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            printDP(dp);
        }

        // 返回
        int max = 0;
        for(int i : dp){
            max = Math.max(max, i);
        }
        return max;
    }

    public static void printDP(int[] dp){
        System.out.println("dp数组为：" + Arrays.toString(dp));
        System.out.println("****************");
    }
}
