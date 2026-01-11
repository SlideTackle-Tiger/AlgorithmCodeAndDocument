## 动态规划子序列问题

### 1. 最长递增子序列

力扣地址： https://leetcode.cn/problems/longest-increasing-subsequence/description/  
项目代码： ALongestIncreasingSubsequence.class  
简要说明： 什么是递增子序列： 在数组中选取某些元素，要保证两点 第一顺序保持为数组原顺序，第二子序列中元素是递增的。  
核心代码：

``` java.
        // 初始化dp
        int len = nums.length;
        int[] dp = new int[len];

        Arrays.fill(dp, 1); // 调用arrays快速填充方法

        // 遍历
        for(int i = 1; i < len; i++){ // 分割子串
            for(int j = 0; j < i; j++){ // 遍历每个子串
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 返回结果
        int maxLen = 0;
        for(int i : dp){
            maxLen = Math.max(maxLen, i);
        }
        return maxLen;
```

算法思路：
- **明确DP数组及下标含义：** $dp_[i]$表示长度以```nums[i]```为结尾的数组最长递增子序列的长度。
- **确定递推公式：** 从哪些状态可以推出$dp_[i]$？```dp[i] = Max(dp[i], dp[j] + 1)  j < i```
- **dp数组如何初始化：** 初始化```dp[i] = 1,子序列中至少包含nums[i]```
- **确定遍历顺序：** i依靠前面的状态，因此从小到大进行遍历。内层需要一个循环j来遍历 0- i的所有元素。在内层循环中```nums[i] > nums[j] 才根据递推公式更新```。
- **打印dp数组：**


需要注意的是我们最终返回的结果不是dp[nums.length - 1] 而是返回nums数组中以元素最大结尾的子序列长度，如例子中末尾元素为18，但是以101为结尾的递增子序列才是最大长度。



### 2. 最长连续递增序列

力扣地址： https://leetcode.cn/problems/longest-continuous-increasing-subsequence/description/  
项目代码： BLongestContinuousIncreasingSubsequence.class   
简短说明： 相较于上一题的最长递增子序列，这里加入了连续的条件，要保持子序列是连续的。重点考虑连续体现在了代码的哪里？  
核心代码：

``` java.
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        // 遍历
        for(int i = 1; i < len; i++){
            if(nums[i] > nums[i - 1]){
                dp[i] = dp[i - 1] + 1;
            }
        }

        // 返回结果
        int maxLen = 0;
        for(int i : dp){
            maxLen = Math.max(maxLen, i);
        }
        return maxLen;
```

算法思路：

- **明确DP数组及下标含义：**  $dp_[i]$表示以i为结尾的最长递增子序列的长度为$dp_[i]$
- **确定递推公式：** ```对于每一个元素，只要跟前面的一个元素进行比较，如果大于则+1即可 dp[i] = dp[i - 1] + 1```
- **dp数组如何初始化：** 初始化dp数组全为1，至少每一个元素的最长连续递增子序列为1
- **确定遍历顺序：** i从1到末尾 从大到小遍历即可。
- **打印dp数组：**

注意我们最终返回的结果是dp数组中的最大值，结尾位置可以是nums的任意位置，而不是返回```dp[len - 1]```.


### 3. 最长重复子数组

力扣地址： https://leetcode.cn/problems/maximum-length-of-repeated-subarray/description/  
项目代码： DMaximumLengthOfRepeatedSubarray.class  
核心代码：

``` java.
        // 初始化dp
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 遍历数组
        for(int i = 1; i <= nums1.length; i++){
            for(int j = 1; j <= nums2.length; j++){
                if(nums1[i -1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        // 返回
        int maxLen = 0;
        for(int[] dpRow : dp){
            for(int num : dpRow){
                maxLen = Math.max(maxLen, num);
            }
        }
        return maxLen;
```

算法思路：

- **明确DP数组及下标含义：** $dp_[i][j]$表示以i-1为结尾的nums1和以j-1为结尾的nums2两个数组的最长重复子数组的长度。
- **确定递推公式：** ```if(nums[i - 1] == nums[j - 1]){ dp[i][j] = dp[i - 1][j - 1] + 1}```
- **dp数组如何初始化：** ```dp[i][0], dp[0][j] 在定义中无意义，初始化为0，其余元素初始化为多少都可以，在递推公式中会被覆盖```
- **确定遍历顺序：** 遍历nums1和nums2，遍历任意一个数组内层嵌套另一个数组的遍历。```for(int i = 1; i <= nums1.length; i++){for(int j = 1; j <= nums2.length;j++){code}}```
- **打印dp数组：**

注意我们最终的返回数据不是$dp_[len][len]$ 而是dp数组中元素的最大值，需要遍历dp数组，可以放到双层嵌套循环中求最大值。  
为什么dp数组 ```dp[i][j]```表示nums1[i - 1] 为结尾与nums2[j - 1]为结尾的数据？ 核心在初始化上，如果表示i、j那么第一行第一列需要进行初始化，而题目中由于无意义直接初始为0即可。

### 4. 最长公共子序列

力扣地址： https://leetcode.cn/problems/longest-common-subsequence/description/  
项目代码： DLongestCommonSubsequence.class  
核心代码：

``` java.
        // 初始化dp
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // 遍历
        for(int i = 1; i <= text1.length(); i++){
            for(int j = 1; j <= text2.length(); j++){
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if(c1 == c2){
                    dp[i][j] = dp[i - 1][j -1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j -1]);
                }
            }
        }

        // 返回结果，注意这里我们返回的结果并不是以最后一个元素结尾的dp数组，而是dp数组中的最大值
        int maxLen = 0;
        for(int[] doRow : dp){
            for(int num : doRow){
                maxLen = Math.max(maxLen, num);
            }
        }
        return maxLen;
```

算法思路：

- **明确DP数组及下标含义：** $dp_[i][j]$ 表示0到i-1的nums1的长度和以0到j-1的nums2的长度的最长公共子序列的长度。 为什么i-1、j-1？为了方便初始化。
- **确定递推公式：** ```if(nums[i -1] == nums[j - 1]){两个元素相同 dp[i][j] = dp[i - 1][j -1] + 1} 如果元素不相同呢？ else情况,dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) 缩小比对窗口```
- **dp数组如何初始化：** ```dp[i][0]、dp[0][j] 表示无状态需要初始化为0，其余元素由于可以由第一行第一列推导而来，因此不必关注初始化```
- **确定遍历顺序：** 外层遍历nums1 内层遍历nums2，从小到大从1开始遍历。
- **打印dp数组：**


### 5. 不相交的线

力扣地址： https://leetcode.cn/problems/uncrossed-lines/description/  
项目代码： EUncrossedLines.class    
简要说明： 给定两个数组nums1和nums2，对于数组中元素相等的连接一条线，求最大的不相交线的个数。
- 本题就是求最长的连续子序列，考虑这样一个问题对于两个数组的最长不连续子序列，由于子序列天然有序则不会相交。

核心代码：

``` java.
        // 初始化dp
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        // 遍历
        for(int i = 1; i <= nums1.length; i++){
            for(int j = 1; j <= nums2.length; j++){
                // 核心递推公式，相等要加一，不等要收缩。
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j -1] + 1;
                    printDP(dp);
                }else{
                    dp[i][j] = Math.max(dp[i][j -1], dp[i - 1][j]);
                }
            }
        }

        // 返回最大值
        int maxLen = 0;
        for(int[] doRow : dp){
            for(int num : doRow){
                maxLen = Math.max(maxLen, num);
            }
        }
        return maxLen;
    }
    public static void printDP(int[][] dp){
        for(int[] dpRow:dp){
            System.out.println(Arrays.toString(dpRow));
        }
        System.out.println("******************");
    }
```
算法思路：
- 同上题。

### 6. 最大子序和

力扣地址： https://leetcode.cn/problems/maximum-subarray/description/  
项目代码： FMaximumSubarray.class   
简短说明： 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和 注意子数组不要求顺序。  
核心代码：

``` java.
     public static int solve(int[] nums){
        // 初始化dp
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // 遍历
        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            printDP(dp);
        }

        // 返回
        int max = 0;
        for(int i : dp){
            max = Math.max(max, i);
        }
        return max;
    }

    public static void printDP(int[] dp){
        System.out.println("dp数组为：" + Arrays.toString(dp));
        System.out.println("****************");
    }
```

算法思路：

- **明确DP数组及下标含义：** $dp_[i]$表示以nums[i]为结尾的最大连续子序列的和。
- **确定递推公式：**
    - 累加前面的和 ```dp[i] = dp[i - 1] + nums[i]```
    - 从i开始重新计算 ```dp[i] = nums[i]```
    - 二者取最大值即可。
- **dp数组如何初始化：** ```dp[i]依赖于dp[i - 1]所以我们要初始化dp[0] = nums[0]```
- **确定遍历顺序：** 从前往后从1开始遍历nums数组。
- **打印dp数组：**



### 7. 判断子序列

力扣地址： https://leetcode.cn/problems/is-subsequence/description/  
项目代码： GIsSubsequence.class  
简要描述： 给定字符串s和t，判断s是否为t的子序列，字符串的子序列定义为原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串
注意需要保持顺序，但不用保证连续。从本题开始正式进入到**编辑距离**（只计算删除的情况，不用考虑增加和替换的情况）类的题目。  
核心代码：

``` java.
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
```

算法思路：

- **明确DP数组及下标含义：** $dp_[i][j]$ 表示以i-1为结尾的字符串s和以j-1为结尾的字符串t，相同子序列的长度为```dp[i][j]```
- **确定递推公式：** 对于递推公式，我们分析当前字符```s[i-1]与t[j -1]``` 是否相等
    - ```s[i - 1] == t[j - 1]``` 那么我们当前的```dp[i][j] = dp[i - 1][j - 1] + 1```
    - 二者不相等，我们的t就要删除元素，这里的删除体现在j++上，同时我们要继承前面计算的状态```s[i - 1]与t[j -2]```的比对情况.也就是使```dp[i][j] = dp[i][j - 1]``` 这一步非常关键仔细理解动规状态传递。
- **dp数组如何初始化：** 分析递推公式我们可以得出所有的状态都需要依赖于```dp[0][0] 与 dp[i][0]```，分析dp数组的含义我们可以得出这两个状态下t的长度为0那么不可能包含s字符，所以都初始化为0即可。
- **确定遍历顺序：** 外层遍历i，内层遍历j，从大到小从1开始即可。
- **打印dp数组：**
### 8. 不同的子序列

力扣地址： https://leetcode.cn/problems/distinct-subsequences/description/
项目代码： HDistinctSubsequences.class   
简要描述： 给定一个字符串s与一个字符串t，统计s的子串种可以包含t的情况。  
核心代码：

``` java.
        // 初始化dp
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        dp[0][0] = 1;
        // 初始化dp[i][0] 全为1
        for(int i = 1; i < s.length() + 1; i++){
            dp[i][0] = 1;
        }
        // 初始化dp[0][j]，当s为空串时一定不能包含t所以都为0，java创建即为0

        // 遍历
        for(int i = 1; i < s.length() + 1; i++){
            for(int j = 1; j < t.length() + 1; j ++){
                char si = s.charAt(i - 1);
                char tj = t.charAt(j - 1);
                if(si == tj){
                    dp[i][j] = dp[i - 1][j -1] + dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i -1][j];
                }
            }
        }

        // 返回什么？返回dp[s长度][t长度]就一定是最大值。
        return dp[s.length()][t.length()];
```

算法思路：
- **明确DP数组及下标含义：** ```dp[i][j]``` 表示以i-1为结尾的s子序列中出现以j-1为结尾的t的个数
- **确定递推公式：** 字符串是否包含核心是比对s[i-1] 与t[j - 1]是否相同
    - ```s[i - 1] == t[j - 1]``` 那么对于```dp[i][j]```有两种情况
        - 第一种s取当前位置（i-1）长度就可以包含t ```dp[i][j] = dp[i -1][j -1]``` 注意这里是状态传递
        - 第二种s取当前位置前一个即(i - 2)长度就可以包含t ```dp[i][j] = dp[i -1][j]``` 注意这里表示的是s[i -2] 与t[i -1]比对的结果。
        - 综上，由于是考虑可能性那么 ```dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]```
    - ```s[i - 1] != t[j -1]``` 那么对与```dp[i][j]只有一种情况dp[i][j] = dp[i - 1][j]```,这里表示s不用当前元素(i -1)用前一个(i -2)的比对结果即s前移j不动。
- **dp数组如何初始化：** 考虑到是包含问题那么我们初始化```dp[0][0] = 1, dp[0][j] = 0, dp[i][0] = 1```
- **确定遍历顺序：** 外层遍历s 内层遍历t 从小到大 从1开始
- **打印dp数组：**

具体实现：

- 实现


### 9. 两个字符串的删除操作

力扣地址： https://leetcode.cn/problems/delete-operation-for-two-strings/description/  
项目代码： IDeleteOperationForTwoStrings.class  
简要描述： 给定两个字符串，求使二者相同的最小删除操作数，你可以删除任意一个字符串的任意一个字符记为1次操作。  
核心代码：

``` java.
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
```

算法思路：
- **明确DP数组及下标含义：** ```dp[i][j]```表示使word1[i -1] 和 word2[j - 1]相同的最小删除步骤
- **确定递推公式：** 我们还是关注与```word1[i -1] 与 word2[j - 1] 是否相等``
    - 二者相等，那么此时我们不必进行删除操作 即```dp[i][j] = dp[i - 1][j - 1]```
    - 二者不相等，我们就要进行删除操作了，怎么删？有三种删除方法
        - 第一种：删除word1即i左移动，那么```dp[i][j] = dp[i - 1][j] + 1```
        - 第二种：删除word2即j左移，那么```dp[i][j] = dp[i][j -1] + 1```
        - 第三种：删除word1 与 word2，二者都左移，那么 ```dp[i][j] = dp[i - 1][j -1] + 2```
        - 综上，只要取三者的最小值即可。
- **dp数组如何初始化：** 分析递推公式，主要是由第一行和第一列推导而来，那么我们初始化
    - 对于```dp[i][0] ```第一列,此时word1为空，所以```dp[i][0] = i ```的值就是word1的长度
    - 对于```dp[0][j] ```第一行，此时word2为空，所以```dp[0][j] = j``` 的值就是word2的长度
- **确定遍历顺序：** 遍历两个word 先i后j 从1开始从小到大
- **打印dp数组：**

### 10. 编辑距离

力扣地址： https://leetcode.cn/problems/edit-distance/description/
项目代码： JEditDistance.class  
简短说明： 给定两个字符串word1 word2求将word1转换成word2所需要的最少操作数，你可以进行增加、删除、替换三种操作。  
核心代码：

``` java.
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
```

算法思路：需要明确一点，对word1操作和对word2进行反操作最终得到的结果是一样的。
- **明确DP数组及下标含义：** ```dp[i][j]```表示以i-1为结尾的word1和以j-1为结尾的word2最少的操作次数为```dp[i][j]```
- **确定递推公式：** 核心还是比较word1[i - 1] 与word2[j - 1]
    - 相等： 不用操作 ```dp[i][j] = dp[i - 1][j - 1]``` 延续前面元素的状态
    - 不相等：有三种操作 增 删 替。
        - **增、删**： 删除word[i - 1]这个元素 那么 ```dp[i][j] = dp[i - 1][j] + 1```删除word2[j - 1]这个元素，那么```dp[i][j] = dp[i][j - 1]```
        - **替**： 什么情况需要替换？不用管，只需要将这种情况的状态写出来就可以 ```dp[i][j] = dp[i - 1][j - 1] + 1```
        - 三者取最小值即可。
- **dp数组如何初始化：** 分析递推公式可知。递推公式依赖左方、上方元素、左上方推导而来，所以我们要初始化```dp[0][j] 以-1为结尾为空串，数值等于j、dp[i][0]同理，数值等于i ```
- **确定遍历顺序：** 外层遍历word1或word2都可以，遍历顺序从小到大 从1开始。由递推公式分析。
- **打印dp数组：**
### 编辑距离总结

### 11. 回文子串

力扣地址： https://leetcode.cn/problems/palindromic-substrings/description/  
项目代码： LPalindromicSubstrings.class  
简短说明： 给定一个字符串s判断s的子串有多少个回文字符串，回文字符串的定义是对于一个字符串正着读和倒着读一样，从中间开始左右两边是一样的，对于单独字符则一定回文。  
核心代码：

``` java.
        // 初始化dp
        boolean[][] dp = new boolean[s.length()][s.length()];

        // 记录回文子串个数
        int count = 0;

        // 遍历
        for(int i = s.length() - 1; i >= 0; i--){
            for(int j = i; j < s.length(); j++){
                char si = s.charAt(i);
                char sj = s.charAt(j);
                if(si == sj){
                    if(j - i <= 1){
                        dp[i][j] = true;
                        count++;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                        if(dp[i][j]){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
```

算法思路：

- **明确DP数组及下标含义：**  考虑如果定义```dp[i]```表示回文字符子串的个数，那么我们无法从dp[i - 1]或dp[i + 1]推导而来。  
  我们定于```dp[i][j]```表示从i到j（左闭右闭,j>=i）范围内的子串是否为回文子串，是：true 不是 ：false。
- **确定递推公式：** 对于回文字符串前置判断条件一定是 ```if(s[i] == s[j])```这个基础上我们又分为三种情况：
    - 情况一 i==j 二者指向同一个元素，那么自然是回文字符串 如 a 我们将```dp[i][j] = true```
    - 情况二 j-i == 1 二者相邻，只有两个元素，那么自然也是回文字符串如 aa， 我们将 ```dp[i][j] = true```
    - 情况三 j- i > 1 二者中间有长度大于1的子串，那么是否为回文就依赖于中间的子串是否回文， 我们将 ```dp[i][j] = dp[i + 1][j -1]```
- **dp数组如何初始化：** 观察递推公式 我们依赖于二维dp矩阵中间对角线元素，即原数组每个单独的数组因此我们初始化 ```dp[k][k] = true k = 0 到 len -1```，也可以不必初始化因为我们有针对于i=j情况的判断。
- **确定遍历顺序：** 分析递推公式，对于遍历的当前元素需要依靠左下角的值来计算。 因此对于列 我们应该从下向上遍历 i，对于行我们从左向右遍历 j。
- **打印dp数组：**
  那么我们如何统计子串回文的个数呢？需要维护一个int，只需要在dp数组赋值为true的时候将cont++即可。

### 12. 最长回文子序列

力扣地址： https://leetcode.cn/problems/longest-palindromic-subsequence/  
项目代码： MLongestPalindromicSubsequence.class  
简要说明： 给定一个字符串，你可以删除任意一个元素，求删除元素后构成最长的回文子串的长度。  
核心代码：

``` java.
        // 初始化dp
        int[][] dp = new int[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++){
            dp[i][i] = 1;
        }

        // 遍历
        for(int i = s.length() - 1; i >= 0; i--){
            for(int j = i + 1; j < s.length(); j++){
                char si = s.charAt(i);
                char sj = s.charAt(j);
                if(si == sj){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        // 返回
        return dp[0][s.length() - 1];
```

算法思路：

- **明确DP数组及下标含义：** ```dp[i][j]```表示从i开始到j结束的最长回文子串的长度
- **确定递推公式：** 前提还是 ```s[i] == s[j] ？ ```
    - 相同 ```dp[i][j] = dp[i + 1][j - 1] + 2``` 取决于里面子串的最长回文子串长度+ 2
    - 不相同：分别考虑左右
        - 左： 子串变为 i 到 j -1， 那么有 ```dp[i][j] = dp[i][j - 1]``` 考虑i
        - 右： 子串变为 i + 1 到 j， 那么有 ```dp[i][j] = dp[i + 1][j]``` 考虑j
        - 二者取最大值即可。
- **dp数组如何初始化：** 观察递推公式，发现根基在中间元素即i = j的情况 我们需要初始化所有中间元素为1 ```dp[k][k] = 1 k = 0 到 len - 1```
- **确定遍历顺序：** 观察递推公式 影响当前遍历元素的状态为 左下角[i + 1][j - 1]与下边[i + 1][j] 和左边元素[i][j - 1] 推导而来，因此我们的遍历顺序为列从小到上遍历 i，行从左到右遍历 j。 需要画图感受
- **打印dp数组：**
