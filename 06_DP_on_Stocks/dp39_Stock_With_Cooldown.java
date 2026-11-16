public class dp39_Stock_With_Cooldown {
    public static void main(String[] args) {
        
    }
    // Buy and sell stock with cooldown
    public int maxProfit2(int idx, boolean buy, int[] prices) {
        if (idx >= prices.length) {
            return 0;
        }
        int profit = 0;
        if (buy) {
            int buy_stock = -prices[idx] + maxProfit2(idx + 1, false, prices);
            int not_buy_stock = 0 + maxProfit2(idx + 1, true, prices);
            profit = Math.max(buy_stock, not_buy_stock);
        } else {
            int sell = prices[idx] + maxProfit2(idx + 2, true, prices);
            int not_sell = 0 + maxProfit2(idx + 1, false, prices);
            profit = Math.max(sell, not_sell);
        }
        return profit;
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit = 0;
                if (buy == 1) {
                    int buyS = -prices[idx] + dp[idx + 1][0];
                    int not_buyS = 0 + dp[idx + 1][1];
                    profit = Math.max(buyS, not_buyS);
                } else {
                    int sell = prices[idx] + dp[idx + 2][1];
                    int not_sell = 0 + dp[idx + 1][0];
                    profit = Math.max(sell, not_sell);
                }
                dp[idx][buy] = (int) profit;
            }
        }
        return dp[0][1];
    }
}
