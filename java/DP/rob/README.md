# 动态规划-打家劫舍问题


你是一个小偷，小偷的核心要素有两点：

- 相邻的房间不能偷
- 确保你偷的金额最大

转换成题目，给你一个数组表示一排屋子里面含有现金的数量，返回你能偷的最大金额。

核心问题：当前的房间偷还是不偷->依赖于前一个房间和前两个房间偷没偷的状态。

### 1. 打家劫舍1

力扣地址： https://leetcode.cn/problems/house-robber/description/  
项目代码： AHouseRobberOne.class  
核心代码：

``` java.
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
```

算法思路：

- **明确DP数组及下标含义：** 考虑下标i，所偷的最大的金币为$dp_[i]$,最终结果为$dp_[nums.length -1]$.注意返回结果。
- **确定递推公式（对于第i间屋子）：**
    - 偷： ```dp[i] = dp[i - 2] + nume[i]```
    - 不偷: ```dp[i] = dp[i - 2]```

- 则最大金币数量为：```Math.max(dp[i-1], dp[i-2] + nums[i])```
- **dp数组如何初始化：** 分析递推公式，至少要前面两个状态```dp[0] = nums[0],dp[1] = Math.max(dp[0],nums[1])```,非0非1下标初始化为0即可。
- **确定遍历顺序：** i取决于i-1与 i-2，遍历顺序为正序且从2开始。
- **打印dp数组：**

具体实现：

- 按照算法思路实现即可，需要注意dp数组的长度为nums数组的长度。

```
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
```

### 2. 打家劫舍2

力扣地址： https://leetcode.cn/problems/house-robber-ii/description/
项目代码： BHouseRobberTwo.class

题目简介：条件与打家劫舍1一样，但是现在房屋首尾相连变成一个个环，还是求能偷的最大值。

题目转换：将环形问题拆分为线性问题，对于数组nums，我们拆成两个数组 nums[0] - nums[length - 2] 和nums[1]-nums[length -1],分别求最大价值，再返回两个数组的最大价值。

核心代码：

``` java.
    public int rob(int[] nums) {
        // 边界判断
        if(nums.length == 1){return nums[0];}
        if(nums.length == 2){return nums[0] > nums[1] ? nums[0] : nums[1];}
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
    public int solve(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
```
算法思路：
- **明确DP数组及下标含义：**$dp_[j]$表示考虑j个房屋能偷的最大价值。
- **确定递推公式：** ```dp[j] = Math.max(dp[j - 1], dp[j - 2] + nums[j])```
- **dp数组如何初始化：** ```dp[0] = nums[0], dp[1] = Math.max(nums[0], nums[1])```，非0与非1元素初始化为0即可。
- **确定遍历顺序：** 从前向后遍历nums数组
- **打印dp数组：**

具体实现：
- 需要注意环形问题拆分，但这样会不会带来额外的空间复杂度？  会的！

### 3. 打家劫舍3 二叉树抢劫

力扣地址： https://leetcode.cn/problems/house-robber-iii/description/  
项目代码： CHouseRobberThree.class    
![DHouseRobberThree.png](image/CHouseRobberThree.png)

简短描述：我们在main线程中创建了如上二叉树，根节点为root。还是不能偷连续的两家，求得到的最大钱币。

核心代码：

``` java.
    public static int solve(TreeNode root){
        //
        int[] res = rob(root);
        return Math.max(res[0], res[1]);
    }

    public static int[] rob(TreeNode root){
        int[] res = new int[2];
        if(root == null){
            // 递归终止条件
            return res;
        }

        // 后续遍历左右根
        int[] left = rob(root.left);
        int[] right = rob(root.right);

        // 不偷当前节点,那么左右子树偷不偷取决于左右子树偷的值最大还是不偷的值最大
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 偷当前节点，那么左右子树都不能偷了，结果是left[0] + right[0] + 当前节点的金额。
        res[1] = root.val + left[0] + right[0];
        return res;
    }
```

算法思路：

- **明确DP数组及下标含义：** 只用两个元素的dp数组 dp[0] 表示不偷当前节点的价值，dp[1]表示偷当前节点的价值。
- **确定递推公式：** 根左右的形式递归 最后 maxValue = Math.max(res[0], res[1]);
- **dp数组如何初始化：**初始化为0即可
- **确定遍历顺序：** 深度优先遍历 左右根 后序遍历
- **打印dp数组：**

具体实现：

- 对于每一个节点都维护一个含有两个元素的dp数组，dp[0]表示偷当前节点的最大价值 dp[1] 表示不偷当前节点的最大价值。在二叉树遍历过程中不断对每个字节点的dp数组进行处理，最终返回根节点的dp数组。
- 能否将树形结构通过遍历变成一维数组？进行状态转移 从树形转换为线性（不行，进行二叉树遍历的话会改变顺序 如根左右 左右根 左根右遍历顺序都是不同的，不能保证访问顺序）  
