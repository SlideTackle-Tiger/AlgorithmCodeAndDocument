package DP.subString;

import java.util.Arrays;

/**
 * @ClassName ALongestIncreasingSubsequence
 * @Description 最长递增子序列
 * @Author tiger
 * @Date 2026/1/3 09:13
 */
public class ALongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println("该数组的最长递增子序列长度为：" + solve(nums));
    }

    public static int solve(int[] nums){
        // 初始化dp
        int len = nums.length;
        int[] dp = new int[len];

        Arrays.fill(dp, 1);

        // 遍历
        for(int i = 1; i < len; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
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
