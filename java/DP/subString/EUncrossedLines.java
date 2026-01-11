package DP.subString;

import java.util.Arrays;

/**
 * @ClassName EUncrossedLines
 * @Description 不相交的线
 * @Author tiger
 * @Date 2026/1/3 10:56
 */
public class EUncrossedLines {


    public static void main(String[] args) {
        int[] nums1 = {1,4,2};
        int[] nums2 = {1,2,4};
        System.out.println("最多的不相交的线的个数为：" + solve(nums1, nums2));
    }

    public static int solve(int[] nums1, int[] nums2){
        // 初始化dp
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 遍历
        for(int i = 1; i <= nums1.length; i++){
            for(int j = 1; j <= nums2.length; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j -1] + 1;
                    printDP(dp);
                }else{
                    dp[i][j] = Math.max(dp[i][j -1], dp[i - 1][j]);
                }
            }
        }

        // 返回最大值
        int maxLen = 0;
        for(int[] doRow : dp){
            for(int num : doRow){
                maxLen = Math.max(maxLen, num);
            }
        }
        return maxLen;
    }
    public static void printDP(int[][] dp){
        for(int[] dpRow:dp){
            System.out.println(Arrays.toString(dpRow));
        }
        System.out.println("******************");
    }
}
