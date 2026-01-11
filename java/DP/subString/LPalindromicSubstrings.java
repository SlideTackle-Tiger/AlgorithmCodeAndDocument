package DP.subString;

/**
 * @ClassName LPalindromicSubstrings
 * @Description 回文子串
 * @Author tiger
 * @Date 2026/1/4 20:38
 */
public class LPalindromicSubstrings {
    public static void main(String[] args) {
        String s = "aaa";
        System.out.println("字符串s包含的回文子串数量为：" + solve(s));
    }

    public static int solve(String s){
        // 初始化dp
        boolean[][] dp = new boolean[s.length()][s.length()];

        // 初始化结果
        int count = 0;

        // 遍历
        for(int i = s.length() - 1; i >= 0; i--){
            for(int j = i; j < s.length(); j++){
                char si = s.charAt(i);
                char sj = s.charAt(j);
                if(si == sj){
                    if(j - i <= 1){
                        dp[i][j] = true;
                        count++;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                        if(dp[i][j]){
                            count++;
                        }
                    }
                }
            }
        }
        return count;

    }
}
