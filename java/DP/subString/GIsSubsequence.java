package DP.subString;

/**
 * @ClassName GIsSubsequence
 * @Description 判断子序列
 * @Author tiger
 * @Date 2026/1/3 12:01
 */
public class GIsSubsequence {
    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println("s是否为t的子序列：" + solve(s, t));
    }

    public static boolean solve(String s, String t){
        // 定义初始化dp数组
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // 循环遍历
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= t.length(); j++){
                char si = s.charAt(i - 1);
                char tj = t.charAt(j - 1);
                if(si == tj){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = dp[i][j -1]; // 状态继承
                }
            }
        }

        return dp[s.length()][t.length()] == s.length() ? true : false;
    }

}
