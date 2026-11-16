public class dp24 {
    public static void main(String[] args) {
        
    }

    // Recursion - Rod cutting problem
    public int rodCutting(int idx, int totalLength,int[] cost){
        if(idx == 0) return totalLength*cost[0];

        int not_cut = rodCutting(idx - 1, totalLength, cost);
        int cut = Integer.MIN_VALUE;
        int currRodLength = idx + 1;
        if(currRodLength <= totalLength){
            cut = cost[idx] + rodCutting(idx, totalLength - currRodLength, cost);
        }
        return Math.max(cut, not_cut);
    }

    // Memoization - agent add

    // Tabulation
	public int cutRod(int[] price) {
		int n = price.length;
		int totalLength = n;
		int[][] dp = new int[n][totalLength + 1];
		
		for (int i = 0; i <= n ; i++) {
			dp[0][i] = i*price[0];
		}
		
		for (int idx = 1; idx < n; idx++) {
			for (int currlength = 0; currlength <= totalLength; currlength++) {
				int not_cut = 0 + dp[idx - 1][currlength];
				int cut = Integer.MIN_VALUE;
				if (idx + 1 <= currlength) {
					cut = price[idx] + dp[idx][currlength - (idx + 1)];
				}
				dp[idx][currlength] = Math.max(cut, not_cut);
			}
		}
		return dp[n - 1][totalLength];
		
	}
}
