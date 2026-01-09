package DP.bag;

/**
 * @ClassName APartitionEqualSubsetSum
 * @Description 分割等和子集
 * @Author tiger
 * @Date 2025/12/22 12:37
 */
public class APartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println("能否分割成两个相同子集：" + solve(nums));
    }

    public static boolean solve(int[] nums){
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(sum % 2 != 0){return false;}
        int target = sum / 2;

        // 初始化dp
        int[] dp = new int[target + 1];

        // 遍历
        for(int i = 0; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j --){
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        // 判断是否满足两个子集和相同
        if(dp[target] == target){
            return true;
        }else {
            return false;
        }
    }
}
