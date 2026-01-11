package DP.subString;

/**
 * @ClassName JEditDistance
 * @Description 编辑距离
 * @Author tiger
 * @Date 2026/1/4 19:38
 */
public class JEditDistance {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("将word1转换成word2的最小操作数为： " + solve(word1, word2));
    }

    public static int solve(String word1, String word2){
        // 初始化dp
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i < word1.length() + 1; i++){
            dp[i][0] = i;
        }
        for(int j = 0; j < word2.length() + 1; j++){
            dp[0][j] = j;
        }

        // 遍历
        for(int i = 1; i < word1.length() + 1; i++){
            for(int j = 1; j < word2.length() + 1; j++){
                char w1 = word1.charAt(i - 1);
                char w2 = word2.charAt(j - 1);
                if(w2 == w1){
                    dp[i][j] = dp[i - 1][j -1];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)); // 分别为 min(替换，min(插入、删除))
                }
            }
        }

        // 返回
        return dp[word1.length()][word2.length()];

    }
}
