# 买卖股票的最佳时机问题

### 1. 买卖股票的最佳时机

力扣地址：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/  
项目代码： ABestTimeToBuyAndSellStockOne.class

简短描述：给你一个prices数组，表示每一天股票的价格，现在你可以交易一次请返回能获得的最大利润。
核心代码：

``` java.
        int len = prices.length;
        // 初始化dp数组
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];dp[0][1] = 0;
        printDP(0,dp);

        // 遍历
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i-1][0] + prices[i]);
            printDP(i,dp);
        }
        return dp[len - 1][1];
```

算法思路：

- **明确DP数组及下标含义：** 对于第i天 $dp[i][0]$表示持有这只股票的最大现金，$dp_[i][1]$表示不持有这个股票的最大现金。最终结果为```Max(dp[len - 1][0], dp[len - 1][1]```)。需要注意的是，持有股票的最大现金一定比不持有股票的最大现金少（因为你买了股票。。。）所以这里最终我们返回的是```dp[len - 1][1]```
- **确定递推公式：** 第i天的状态由第i-1天的状态推导而来：
    - 持有这只股票的最大现金： ```dp[i][0] = Math.max(dp[i-1][0], 0 - price[i])```; 第i天持有这只股票的最大现金等于第i-1天持有这只股票的最大现金与手头有零元 - 第i天股票价格的的最大值
    - 抛售这只股票的最大现金:    ```dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + price[i])```; 第i天抛售这只股票的最大现金等于第i-1天抛售股票持有的最大现金与 i-1天持有股票 + 今日股价的最大值。

- **dp数组如何初始化：** 我们考虑到递推公式 第i天的状态依靠第i-1天的状态，因此我们初始化```dp[0][0] = 0 - prices[i], dp[0][1] = 0```
- **确定遍历顺序：** 从1开始从小到大遍历prices数组即可。
- **打印dp数组：** 示例中给出了printDP函数，可以调用打印dp数组方便观察，在后续的问题中将不会再提供相同的代码，读者可以自行引用。

具体实现：

- 解题思路：回顾本题，其实我们要维护两个状态来表示当天的决定，```dp[i][0]``` 用来记录买入股票时手里的余额，```dp[i][1]```用来记录卖出股票时的余额。每一次的状态都由前一天的状态来表示，这样我们就能够在数组末尾获得一个利润最大值。

### 2. 买股票的最佳时机二

力扣地址： https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/  
项目代码：BBestTimeToBuyAndSellStockTwo.class

简短描述： 对于一个prices数组表示每天都股票价格，你现在可以多次买入卖出股票（注意卖出操作要在买入操作之后完成），返回能获得的最大现金。

核心代码：

``` java.
        int len = prices.length;
        // 初始化dp
        int[][] dp = new int[len][2];
        dp[0][0] = 0 - prices[0]; dp[0][1] = 0;

        // 遍历
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];
```
对于一只股票，核心是要让兜里的钱最多。我们需要维护两个状态，一个状态是持有状态下兜里钱最多，在该状态下我们选择价格第i天与i-1天价格较低的那支股票入手，另一个状态是抛售状态也要保证兜里钱最多，这个状态下我们选择当前第i天的股票价格与i-1天股票价格较高的时候卖出。  
那么如何维护这些状态呢，此时就需要一个二维dp数组，形状为```dp[prices.length][2]```,每一行表示当天的买入和卖出情况，需要两列一列表示买入，一列表示卖出。

- 对于买入状态 ```dp[i][0]```，先来看股票买入只能买一次的情况 ```dp[i][0] = Math.max(dp[i][0], 0 - prices[i])```， 兜里钱最多的情况是前一天持有股票的价格与0 - 当前天数i的股票价格（就是买）的最大值
  来到本题，题目要求买卖股票可以买卖多次，且卖股票的操作必须要持有股票的情况，我们的买入股票兜里最大钱的递推公式就变成了```dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i])``` 买需要花钱，兜里的钱等于前一天没持有股票的钱减去今天的股票价格。
- 对于卖出状态 ```dp[i][1]```, 先来看股票买入只能买一次的情况 ```dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i])```，兜里钱最多的情况是前一天卖出的价格与今天卖出的价格（就是前一天持有的价格加上当天的股价）的最大值。  
  来到本题，那么对于卖出状态我们还是保持这个递推公式不变。
  最终的返回结果就是```Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1])```,分析我们可以得到，只要你在最后一天还持有股票，那么兜里的钱一定没有最后一天卖出股票的钱多。所以我们直接返回```dp[prices.length - 1][1]```


算法思路：

- **明确DP数组及下标含义：** dp[i][0]表示第i天买入股票兜里最多的钱，dp[i][1]表示第i天卖出股票兜里对多的钱。
- **确定递推公式：**
    - 对于第i天持有股票所得的现金，```dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);```
    - 对于第i天没有股票所得的现金，```dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);```
- **dp数组如何初始化：** 由于第i天状态依靠前一天状态，因此我们直接初始化第一天的数据即可
    - ```dp[0][0] : 第一天买入兜里最多的钱 dp[0][0] = 0 - prices[i];```
    - ```dp[0][1] : 第一天卖出兜里最多的钱 dp[0][1] = 0; 因为第一天还没有买任何的股票所以无论股票价格为多少，此时卖出都会为0。```
- **确定遍历顺序：** 从1开始遍历prices数组即可 正序遍历
- **打印dp数组：**

### 3. 买卖股票的最大价值三

力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： CBestTimeToBuyAndSellStockThree.class

简短说明：在买卖股票的最大价值三中，买卖股票的次数来到了两次，你只可以至多买卖两次股票

核心代码：

``` java.
        // 初始化dp数组
        int len = prices.length;
        int[][] dp = new int[len][5];
        dp[0][0] = 0;dp[0][1] = 0 - prices[0];dp[0][2] = 0; dp[0][3] = 0 - prices[0];dp[0][4] = 0;

        // 遍历
        for(int i = 1; i < len;i++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        // 思考？我们应该返回什么 第一次卖出 和第二次卖出的最大值，但由于第二次卖出已经包含了第一次卖出的状态（传递）我们直接返回第二次卖出的价格即可。
        return dp[len - 1][4];
```

算法思路：

- **明确DP数组及下标含义：**  dp[i][0] 不操作 dp[i][1] 第一次持有（延续前一天买入的状态也可以第i天进行买入）
  dp[i][2] 第一次不持有（延续前一天卖出的状态也可以第i天进行卖出）dp[i][3] 第二次持有，dp[i][4] 第二次卖出
- **确定递推公式：**
    - dp[i][0] = dp[i - 1][0];
    - dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]), 如果在第i天第一次买入，那么第一次持有的最大现金等于前一天的状态和前一天不操作状态减去当前股价的最大值。
    - dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i])
    - dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i])
    - dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i])
- **dp数组如何初始化：**
    - dp[0][0] = 0
    - dp[0][1] = 0 - prices[0];
    - dp[0][2] = 0; //同一天买卖
    - dp[0][3] = 0 - prices[0]; // 0 - prices[0],注意这个0是dp[0][2]的0
    - dp[0][4] = 0;
- **确定遍历顺序：** 从小到大从1开始遍历prices数组
- **打印dp数组：**

### 4. 买卖股票的最佳时机四
力扣地址： https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/  
项目代码： DBestTimeToBuyAndSellStockFour.class    
简要说明： 在买卖股票最佳时机四中，你的交易次数来到k次，需要注意的是卖出操作必须在持有状态下进行。  
核心代码：

``` java.
        //构造dp数组
        int len = prices.length;
        int d = 2 * k + 1;
        int[][] dp = new int[len][d];
        for(int i = 1; i < 2 * k; i = i +2){
            dp[0][i] = 0 - prices[0];
        }
        // 遍历
        for(int i = 1; i < len; i++){
            // 遍历维度d
            dp[i][0] = dp[i][0];
            for(int j = 1; j < d ;j++){
                if(j % 2 == 1){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }

            }
        }

        return dp[len - 1][ 2* k ];
```

算法思路：

- **明确DP数组及下标含义：** dp[i][奇数m] = 第i天m次买入手里的最大现金，dp[i][偶数n] = 第i天n次卖出手里的最大资金。
- **确定递推公式：**
    - 对于奇数第m次买入 dp[i][m] = Math.max(dp[i - 1][m],dp[i - 1][n -1] - prices[i])
    - 对于偶数第n次卖出 dp[i][n]  = Math.max(dp[i - 1][n], dp[i - 1][m - 1] + prices[i])
- **dp数组如何初始化：**  对于奇数买入我们需要初始化为 0-prices[0],其余元素初始化为0即可。
- **确定遍历顺序：** 正序从1开始遍历prices数组即可。
- **打印dp数组：**

具体实现：
- 实现


### 5. 最佳买卖股票时机含冷冻期

力扣地址： https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/  
项目代码： EBestTimeToBuyAndSellStockCooldown.class    
简短描述： 现在你依旧可以多次买卖股票，但是在卖出股票的第二天为冷冻期，你不可以在第二天买入股票。
核心代码：

``` java.
        int len = prices.length;
        // 初始化dp
        int[][] dp = new int[len][4];
        dp[0][0] = 0 - prices[0]; // 持有股票状态
        dp[0][1] = 0;  // 卖出后保持状态 该状态下可以进行股票的买操作
        dp[0][2] = 0; // 卖出股票状态
        dp[0][3] = 0; // 冷静期

        // 遍历
        for(int i = 1; i < len ; i++){
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2]; 

        }
        // 返回
        return Math.max(dp[len - 1][3], Math.max(dp[len - 1][1], dp[len - 1][2]));
```

算法思路：
- **明确DP数组及下标含义：**
    - ```dp[i][0]``` 持有股票的状态的最大金钱
    - ```dp[i][1]``` 保持卖出股票的状态（不买入 不卖出）。
    - ```dp[i][2]``` 卖出股票状态的最大金额。
    - ```dp[i][3]``` 冷冻期
- **确定递推公式：**
    - ```dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i] , dp[i - 1][1] - prices[i]))```
    - ```dp[i][1] = Math.max(dp[i - 1][1] ,dp[i - 1][3])```
    - ```dp[i][2] = dp[i - 1][0] + prices[i]```
    - ```dp[i][3] = dp[i - 1][2]```
- **dp数组如何初始化：** 初始```dp[0][0] = 0 - prices[0]``` 其余的第0天元素都应该初始化为0
- **确定遍历顺序：** 从小到大从1开始遍历prices数组
- **打印dp数组：**

具体实现：
- 状态分析：
    - 在dp定义时我们分析题目需要的四种状态：持有股票的最大金钱、保持卖出后的最大金钱、卖出股票的最大金钱、冷冻期
    - 我们逐一来分析状态从而推导出递推公式：
    - ```持有股票的最大金钱dp[i][0]``` 可以由三种状态推导过来，前一天持有股票的最大金钱、前一天处于冷冻期 - 今天的股价、前一天处于保持卖出状态 - 今天的股价。三者取最大值
    - ```保持卖出后的最大金钱dp[i][1]``` 可以由两种状态推导过来，前一天保持卖出的最大金钱与前一天处于冷冻期的最大金钱，二者的最大值。
    - ```卖出股票的最大金钱dp[i][2]``` 由持有股票的最大金钱加上今天的股价得到。
    - ```冷冻期dp[i][3]``` 由卖出股票的最大金钱得到。

### 6. 买卖股票的最佳时机含手续费
力扣地址： https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/  
项目代码： FBestTimeToBuyAndSellStockFee.class.
简要描述： 其实就是在交易时，减去fee即可，交易次数不限制。
核心代码：

``` java.
        int len = prices.length;
        // 初始化dp
        int[][] dp = new int[len][2];
        dp[0][0] = 0 - prices[0]; // 买入状态
        dp[0][1] = 0; // 卖出状态

        // 遍历
        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }

        // 返回最大现金
        return dp[len - 1][1];
```

算法思路：
- **明确DP数组及下标含义：** dp[i][0] 表示买入状态含有的最大现金，dp[i][1] 表示卖出状态含有的最大现金。
- **确定递推公式：**
    - ```dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i])```
    - ```dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i])```
- **dp数组如何初始化：** 初始化```dp[0][0] = 0 - prices[0]```
- **确定遍历顺序：** 从小到大 从1开始
- **打印dp数组：**