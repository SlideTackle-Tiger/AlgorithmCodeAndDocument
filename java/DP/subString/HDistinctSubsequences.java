package DP.subString;

/**
 * @ClassName HDistinctSubsequences
 * @Description 不同的子序列
 * @Author tiger
 * @Date 2026/1/4 15:42
 */
public class HDistinctSubsequences {
    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println("s的子序列中，包含t的情况有" + solve(s,t) + "种");
    }

    public static int solve(String s, String t){
        // 初始化dp
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        dp[0][0] = 1;
        // 初始化dp[i][0] 全为1
        for(int i = 1; i < s.length() + 1; i++){
            dp[i][0] = 1;
        }
        // 初始化dp[0][j]，当s为空串时一定不能包含t所以都为0，java创建即为0

        // 遍历
        for(int i = 1; i < s.length() + 1; i++){
            for(int j = 1; j < t.length() + 1; j ++){
                char si = s.charAt(i - 1);
                char tj = t.charAt(j - 1);
                if(si == tj){
                    dp[i][j] = dp[i - 1][j -1] + dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i -1][j];
                }
            }
        }

        // 返回什么？返回dp[s长度][t长度]就一定是最大值。
        return dp[s.length()][t.length()];

    }
}
