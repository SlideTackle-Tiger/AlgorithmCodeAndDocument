package DP.rob;

/**
 * @ClassName AHouseRobberOne
 * @Description 打家劫舍1
 * @Author tiger
 * @Date 2025/12/29 15:24
 */
public class AHouseRobberOne {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println("能偷的最大金额为：" + solve(nums));
    }

    public static int solve(int[] nums){
        // 初始化dp
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        // 遍历
        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i -2] + nums[i]);
        }

        return dp[nums.length - 1];
    }
}
