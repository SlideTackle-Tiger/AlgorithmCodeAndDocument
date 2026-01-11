package DP.subString;

import java.util.Arrays;

/**
 * @ClassName BLongestContinuousIncreasingSubsequence
 * @Description 最长连续递增子序列
 * @Author tiger
 * @Date 2026/1/3 09:32
 */
public class BLongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        System.out.println("最长连续递增子序列长度为：" + solve(nums));
    }

    public static int solve(int[] nums){
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        // 遍历
        for(int i = 1; i < len; i++){
            if(nums[i] > nums[i - 1]){
                dp[i] = dp[i - 1] + 1;
            }
        }

        // 返回结果
        int maxLen = 0;
        for(int i : dp){
            maxLen = Math.max(maxLen, i);
        }
        return maxLen;

    }
}
