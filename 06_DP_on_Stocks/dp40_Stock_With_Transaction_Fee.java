public class dp40_Stock_With_Transaction_Fee {
    // Buy and sell stock with transaction fee - LC 714
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit = 0;
                if (buy == 1) {
                    profit = Math.max(-prices[idx] + dp[idx + 1][0], 0 + dp[idx + 1][1]);
                } else {
                    profit = Math.max(prices[idx] - fee + dp[idx + 1][1], 0 + dp[idx + 1][0]);
                }
                dp[idx][buy] = (int) profit;
            }
        }
        return dp[0][1];
    }
}
