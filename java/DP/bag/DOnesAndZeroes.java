package DP.bag;

/**
 * @ClassName DOnesAndZeroes
 * @Description 一和零
 * @Author tiger
 * @Date 2025/12/24 12:57
 */
public class DOnesAndZeroes {
    public static void main(String[] args) {
        String[] str = {"10", "0", "1"};
        int m = 1; int n = 1;
        System.out.println("最大子集长度为：" + solve(str,m,n));
    }

    public static int solve(String[] str, int m, int n){
        // 初始化dp
        int[][] dp = new int[m + 1][n + 1];

        // 遍历
        for(int s = 0; s < str.length; s++){
            // 计算当前字符串的1和0的个数
            String curr = str[s];
            int x = 0; int y = 0;
            for(int c = 0; c < curr.length(); c++){
                if(curr.charAt(c) == '0'){
                    x++;
                }else {
                    y++;
                }
            }

            // 遍历背包
            for(int i = m; i >= x; i--){
                for(int j = n; j >= y; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - x][j - y] + 1);
                }
            }

        }

        // 返回
        return dp[m][n];
    }
}
