public class dp36 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }

    // Buy & sell stocks 2 - LC 122
    // Recursion
    public int maxProfit2(int idx, boolean buy,int[] prices){
        if(idx == prices.length){
            return 0;
        }
        int profit = 0;
        if(buy){
            int buy_stock = - prices[idx] + maxProfit2(idx + 1, false, prices);
            int not_buy_stock = 0 + maxProfit2(idx + 1, true, prices);
            profit = Math.max(buy_stock, not_buy_stock);
        }
        else{
            int sell = prices[idx] + maxProfit2(idx + 1, true, prices);
            int not_sell = 0 + maxProfit2(idx + 1, false, prices);
            profit = Math.max(sell, not_sell);
        }
        return profit;
    }

    // Memoization - agent add

    // Tabulation

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        dp[n][0] = dp[n][1] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit = 0;
                if (buy == 1) {
                    profit = Math.max(-prices[idx] + dp[idx + 1][0], 0 + dp[idx + 1][1]);
                } else {
                    profit = Math.max(prices[idx] + dp[idx + 1][1], 0 + dp[idx + 1][0]);
                }
                dp[idx][buy] = (int) profit;
            }
        }
        return dp[0][1];
    }

    // Tabulation + space optimization
    public static int maxProfit3(int[] prices) {
        int n = prices.length;
        int[] ahead = new int[2];
        int[] curr = new int[2];

        ahead[0] = ahead[1] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit = 0;
                if (buy == 1) {
                    profit = Math.max(-prices[idx] + ahead[0], 0 + ahead[1]);
                } else {
                    profit = Math.max(prices[idx] + ahead[1], 0 + ahead[0]);
                }
                curr[buy] = (int)profit;
            }
            ahead = curr;
        }
        return ahead[1];
    }
}
