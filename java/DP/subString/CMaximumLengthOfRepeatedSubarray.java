package DP.subString;

/**
 * @ClassName DMaximumLengthOfRepeatedSubarray
 * @Description 最长重复子数组，给你两个数组A、B返回他们最长的公共子数组的长度。
 * @Author tiger
 * @Date 2026/1/3 09:47
 */
public class CMaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        System.out.println("最长重复子数组长度为："  + solve(nums1, nums2));
    }

    public static int solve(int[] nums1, int[] nums2){
        // 初始化dp
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 遍历数组
        for(int i = 1; i <= nums1.length; i++){
            for(int j = 1; j <= nums2.length; j++){
                if(nums1[i -1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        // 返回
        int maxLen = 0;
        for(int[] dpRow : dp){
            for(int num : dpRow){
                maxLen = Math.max(maxLen, num);
            }
        }
        return maxLen;

    }
}
