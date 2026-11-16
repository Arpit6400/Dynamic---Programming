public class dp35 {
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
