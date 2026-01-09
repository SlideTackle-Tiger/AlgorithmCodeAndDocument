package DP.bag;

/**
 * @ClassName BLastStoneWeightTow
 * @Description 最后一块石头的重量
 * @Author tiger
 * @Date 2025/12/22 14:44
 */
public class BLastStoneWeightTow {
    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        int[] stones2 = {31,26,33,21,40};
        System.out.println("相撞后最后剩余石头重量：" + solve(stones2));
    }

    public static int solve(int[] stones){
        int sum = 0;
        for(int s: stones){
            sum += s;
        }

        int target = sum / 2; // capacity

        int[] dp = new int[target + 1];


        for(int i =0;i<stones.length;i++){
            for(int j = target; j >= stones[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        return Math.abs(sum - dp[target] - dp[target]);
    }
}
