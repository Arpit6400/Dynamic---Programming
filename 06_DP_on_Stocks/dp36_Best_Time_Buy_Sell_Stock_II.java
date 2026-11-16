// LC: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

public class dp36_Best_Time_Buy_Sell_Stock_II {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
    }

    // Recursion - state: (idx, canBuy)
    public int maxProfit2(int idx, boolean buy, int[] prices) {
        if (idx == prices.length)
            return 0;
        int profit;
        if (buy) {
            int buy_stock = -prices[idx] + maxProfit2(idx + 1, false, prices);
            int not_buy = 0 + maxProfit2(idx + 1, true, prices);
            profit = Math.max(buy_stock, not_buy);
        } else {
            int sell = prices[idx] + maxProfit2(idx + 1, true, prices);
            int not_sell = 0 + maxProfit2(idx + 1, false, prices);
            profit = Math.max(sell, not_sell);
        }
        return profit;
    }

    // Memoization
    private int solveMemo(int idx, int buy, int[] prices, int[][] dp) {
        if (idx == prices.length)
            return 0;
        if (dp[idx][buy] != -1)
            return dp[idx][buy];

        int profit;
        if (buy == 1) {
            profit = Math.max(-prices[idx] + solveMemo(idx + 1, 0, prices, dp),
                    0 + solveMemo(idx + 1, 1, prices, dp));
        } else {
            profit = Math.max(prices[idx] + solveMemo(idx + 1, 1, prices, dp),
                    0 + solveMemo(idx + 1, 0, prices, dp));
        }
        return dp[idx][buy] = profit;
    }

    public int maxProfitMemo(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp)
            java.util.Arrays.fill(row, -1);
        return solveMemo(0, 1, prices, dp);
    }

    // Tabulation
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit;
                if (buy == 1)
                    profit = Math.max(-prices[idx] + dp[idx + 1][0], dp[idx + 1][1]);
                else
                    profit = Math.max(prices[idx] + dp[idx + 1][1], dp[idx + 1][0]);
                dp[idx][buy] = (int) profit;
            }
        }
        return dp[0][1];
    }

    // Space Optimization
    public static int maxProfitSpaceOpt(int[] prices) {
        int n = prices.length;
        int[] ahead = new int[2];
        int[] curr = new int[2];
        ahead[0] = ahead[1] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit;
                if (buy == 1)
                    profit = Math.max(-prices[idx] + ahead[0], ahead[1]);
                else
                    profit = Math.max(prices[idx] + ahead[1], ahead[0]);
                curr[buy] = (int) profit;
            }
            ahead = curr.clone();
        }
        return ahead[1];
    }
}
