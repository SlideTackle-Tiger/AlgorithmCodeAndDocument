package DP.base;

/**
 * @ClassName DUniqueBinarySearchTrees
 * @Description 不同的二叉搜索树
 * @Author tiger
 * @Date 2025/12/21 15:27
 */
public class GUniqueBinarySearchTrees {
    public static void main(String[] args) {
        int n = 3;
        System.out.println("可以构造出"+ sovle(n) + "种不同的二叉搜索树");
    }

    public static int sovle(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++ ){
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];

    }
}
