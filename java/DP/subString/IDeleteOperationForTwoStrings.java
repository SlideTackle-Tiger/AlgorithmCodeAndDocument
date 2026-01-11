package DP.subString;

/**
 * @ClassName IDeleteOperationForTwoStrings
 * @Description 两个字符串的删除操作
 * @Author tiger
 * @Date 2026/1/4 17:05
 */
public class IDeleteOperationForTwoStrings {
    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";
        System.out.println("使两个字符串相同最少删除次数为：" + solve(word1, word2));
    }

    public static int solve(String word1, String word2){
        // 初始化dp
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i <= word1.length(); i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= word2.length();j++){
            dp[0][j] = j;
        }

        // 遍历
        for(int i = 1; i < word1.length() + 1; i++){
            for(int j = 1; j < word2.length() + 1; j++){
                char w1 = word1.charAt(i - 1);
                char w2 = word2.charAt(j - 1);
                if(w1 == w2){
                    dp[i][j] = dp[i - 1][j -1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i -1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        // 返回结果
        return dp[word1.length()][word2.length()];

    }
}
