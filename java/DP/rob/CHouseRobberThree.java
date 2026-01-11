package DP.rob;

/**
 * @ClassName CHouseRobberThree
 * @Description
 * @Author tiger
 * @Date 2025/12/29 16:50
 */
public class CHouseRobberThree {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static void main(String[] args) {
        // 构建二叉树
        TreeNode root = new TreeNode(3,new TreeNode(2),new TreeNode(3));
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        System.out.println("打劫二叉树最大钱币：" + solve(root));
    }

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
}
