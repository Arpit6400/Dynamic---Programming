// LC: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// GFG: https://www.geeksforgeeks.org/problems/stock-buy-and-sell2615/1
public class dp35_Best_Time_Buy_Sell_Stock_I {
    public static void main(String[] args) {

    }

    // Dp on stocks

    // Best Time to Buy and Sell Stock - LC 121
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int minPrice = prices[0];
        int Profit = 0;

        for (int i = 1; i < n; i++) {
            int cost = prices[i] - minPrice;
            Profit = Math.max(Profit, cost);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return Profit;
    }
}
