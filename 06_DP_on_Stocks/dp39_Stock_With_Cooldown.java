// LC: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

public class dp39_Stock_With_Cooldown {

    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 0, 2 };
        System.out.println("Tabulation: " + maxProfit(prices));
        System.out.println("Space Opt: " + maxProfitSpaceOpt(prices));
    }

    // Recursion
    public int maxProfitRec(int idx, boolean buy, int[] prices) {
        if (idx >= prices.length)
            return 0;
        int profit;
        if (buy) {
            int buyStock = -prices[idx] + maxProfitRec(idx + 1, false, prices);
            int notBuy = maxProfitRec(idx + 1, true, prices);
            profit = Math.max(buyStock, notBuy);
        } else {
            int sell = prices[idx] + maxProfitRec(idx + 2, true, prices);
            int notSell = maxProfitRec(idx + 1, false, prices);
            profit = Math.max(sell, notSell);
        }
        return profit;
    }

    // Tabulation - O(N) Time, O(N) Space
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n + 2][2];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit;
                if (buy == 1) {
                    profit = Math.max(-prices[idx] + dp[idx + 1][0], dp[idx + 1][1]);
                } else {
                    profit = Math.max(prices[idx] + dp[idx + 2][1], dp[idx + 1][0]);
                }
                dp[idx][buy] = (int) profit;
            }
        }
        return dp[0][1];
    }

    // Space Optimization - O(N) Time, O(1) Space
    public static int maxProfitSpaceOpt(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        int[] front2 = new int[2];
        int[] front1 = new int[2];
        int[] cur = new int[2];

        for (int idx = n - 1; idx >= 0; idx--) {
            cur[1] = Math.max(-prices[idx] + front1[0], front1[1]);
            cur[0] = Math.max(prices[idx] + front2[1], front1[0]);

            front2 = front1.clone();
            front1 = cur.clone();
        }
        return cur[1];
    }
}
