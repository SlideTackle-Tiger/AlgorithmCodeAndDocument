package DP.bag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName QWordBreak
 * @Description 单词拆分
 * @Author tiger
 * @Date 2025/12/25 14:14
 */
public class QWordBreak {
    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple", "pen"));
        System.out.println("字符串" + s + "是否可以被拆分成" + wordDict  + ":" + solve(s, wordDict));
    }
    public static boolean solve(String s, List<String> wordDict){
        HashSet<String> dict = new HashSet<>(wordDict);
        // 初始化dp
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        // 遍历
        for(int j = 1; j <= s.length(); j++){ // 遍历背包
            for(int i = 0; i < wordDict.size(); i++){ // 遍历物品
                // 如果当前背包长度小于遍历到物品的长度，说明物品放不下我们直接break
                if(j >= wordDict.get(i).length()){
                    // 获取子串，要明确子串的范围是从 j- 当前遍历到的物品长度 到 j（当前背包位置）
                    String sub = s.substring(j - wordDict.get(i).length(), j);
                    // 核心递推公式判断，如果当前字典中包含子串且 之前的字符串也可以被字典中的字符串填满，那么我们将当前背包位置变为true
                    if(dict.contains(sub) && dp[j - wordDict.get(i).length()]){
                        dp[j] = true;
                    }
                }

            }
        }

        return dp[s.length()];
    }
}
