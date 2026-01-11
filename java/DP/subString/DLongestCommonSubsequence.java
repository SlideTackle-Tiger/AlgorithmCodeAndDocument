package DP.subString;

/**
 * @ClassName DLongestCommonSubsequence
 * @Description 最长公共子序列，给两个数组，求最长的不连续的公共子序列。
 * @Author tiger
 * @Date 2026/1/3 10:09
 */
public class DLongestCommonSubsequence {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("两个字符串的最长不连续的公共子序列长度为：" + solve(text1, text2));
    }

    public static int solve(String text1, String text2){
        // 初始化dp
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // 遍历
        for(int i = 1; i <= text1.length(); i++){
            for(int j = 1; j <= text2.length(); j++){
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if(c1 == c2){
                    dp[i][j] = dp[i - 1][j -1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j -1]);
                }
            }
        }

        // 返回结果，注意这里我们返回的结果并不是以最后一个元素结尾的dp数组，而是dp数组中的最大值
        int maxLen = 0;
        for(int[] doRow : dp){
            for(int num : doRow){
                maxLen = Math.max(maxLen, num);
            }
        }
        return maxLen;
    }
}
