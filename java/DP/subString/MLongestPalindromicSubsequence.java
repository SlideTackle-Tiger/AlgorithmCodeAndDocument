package DP.subString;

/**
 * @ClassName MLongestPalindromicSubsequence
 * @Description 最长回文子序列
 * @Author tiger
 * @Date 2026/1/4 21:06
 */
public class MLongestPalindromicSubsequence {
    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println("经过删除元素，最长的回文子串长度为：" + solve(s));
    }

    public static int solve(String s){
        // 初始化dp
        int[][] dp = new int[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++){
            dp[i][i] = 1;
        }

        // 遍历
        for(int i = s.length() - 1; i >= 0; i--){
            for(int j = i + 1; j < s.length(); j++){
                char si = s.charAt(i);
                char sj = s.charAt(j);
                if(si == sj){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        // 返回
        return dp[0][s.length() - 1];

    }
}
