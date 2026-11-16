// LC: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
// GFG: https://www.geeksforgeeks.org/problems/maximum-profit4657/1

public class dp38_Best_Time_Buy_Sell_Stock_IV {

    public static void main(String[] args) {
        int[] prices = { 3, 2, 6, 5, 0, 3 };
        int k = 2;
        System.out.println("Tabulation: " + maxProfit(k, prices));
        System.out.println("Space Opt: " + maxProfitSpaceOpt(k, prices));
    }

    // Tabulation - O(N * 2 * K) Time, O(N * 2 * K) Space
    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0)
            return 0;
        int transaction = 2 * k;
        int[][] dp = new int[n + 1][transaction + 1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int tran = 0; tran < transaction; tran++) {
                long profit;
                if (tran % 2 == 0) {
                    profit = Math.max(-prices[idx] + dp[idx + 1][tran + 1], dp[idx + 1][tran]);
                } else {
                    profit = Math.max(prices[idx] + dp[idx + 1][tran + 1], dp[idx + 1][tran]);
                }
                dp[idx][tran] = (int) profit;
            }
        }
        return dp[0][0];
    }

    // Space Optimization - O(N * 2 * K) Time, O(2 * K) Space
    public static int maxProfitSpaceOpt(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0)
            return 0;
        int transaction = 2 * k;
        int[] after = new int[transaction + 1];
        int[] curr = new int[transaction + 1];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int tran = 0; tran < transaction; tran++) {
                long profit;
                if (tran % 2 == 0) {
                    profit = Math.max(-prices[idx] + after[tran + 1], after[tran]);
                } else {
                    profit = Math.max(prices[idx] + after[tran + 1], after[tran]);
                }
                curr[tran] = (int) profit;
            }
            after = curr.clone();
        }
        return after[0];
    }
}
