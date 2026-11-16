// LC: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

public class dp40_Stock_With_Transaction_Fee {

    public static void main(String[] args) {
        int[] prices = { 1, 3, 2, 8, 4, 9 };
        int fee = 2;
        System.out.println("Tabulation: " + maxProfit(prices, fee));
        System.out.println("Space Opt: " + maxProfitSpaceOpt(prices, fee));
    }

    // Tabulation - O(N) Time, O(N) Space
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n + 1][2];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit;
                if (buy == 1) {
                    profit = Math.max(-prices[idx] + dp[idx + 1][0], dp[idx + 1][1]);
                } else {
                    profit = Math.max(prices[idx] - fee + dp[idx + 1][1], dp[idx + 1][0]);
                }
                dp[idx][buy] = (int) profit;
            }
        }
        return dp[0][1];
    }

    // Space Optimization - O(N) Time, O(1) Space
    public static int maxProfitSpaceOpt(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0)
            return 0;

        int[] ahead = new int[2];
        int[] curr = new int[2];

        for (int idx = n - 1; idx >= 0; idx--) {
            curr[1] = Math.max(-prices[idx] + ahead[0], ahead[1]);
            curr[0] = Math.max(prices[idx] - fee + ahead[1], ahead[0]);

            ahead = curr.clone();
        }
        return ahead[1];
    }
}
