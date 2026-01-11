package DP.rob;

/**
 * @ClassName BHouseRobberTwo
 * @Description 打家劫舍2
 * @Author tiger
 * @Date 2025/12/29 16:10
 */
public class BHouseRobberTwo {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println("房屋循环，打劫最大金额为：" + solve1(nums));
    }

    public static int solve1(int[] nums){
        // 边界判断
        if(nums.length == 1){return nums[0];}
        if(nums.length == 2){return Math.max(nums[0], nums[1]);}
        // 数组拆分
        int n = nums.length;
        int[] subNumsOne = new int[n - 1];
        for(int i = 0; i < n - 1; i++){
            subNumsOne[i] = nums[i];
        }
        int[] subNumsTow = new int[n - 1];
        for(int i = 1; i < n; i++){
            subNumsTow[i - 1] = nums[i];
        }
        // 问题转化为两个打家劫舍1取最大的那个。
        return Math.max(solve(subNumsOne), solve(subNumsTow));
    }
    // 打家劫舍1
    public static int solve(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
