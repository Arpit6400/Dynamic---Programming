public class dp53_Palindrome_Partitioning_II {
    public static void main(String[] args) {

    }

    // Palindrome Partitioning - 2
    // Recursion

    public static int palindromePartitioning2Rec(int idx, String str) {
        if (idx == str.length()) {
            return 0;
        }
        int minCost = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder("");
        for (int j = idx; j < str.length(); j++) {
            sb.append(str.charAt(j));
            if (isPalin(sb)) {
                int cost = 1 + palindromePartitioning2Rec(j + 1, str);
                minCost = Math.min(minCost, cost);
            }
        }
        return minCost;
    }

    private static boolean isPalin(StringBuilder sb) {
        int left = 0;
        int right = sb.length() - 1;

        while (left < right) {
            if (sb.charAt(left++) != sb.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // Tabulation
    public int minCut(String s) {
        int n = s.length();
        if (n == 0)
            return 0;

        int[] dp = new int[n + 1];

        // Base case: no characters remaining = 0 partitions
        dp[n] = 0;

        for (int idx = n - 1; idx >= 0; idx--) {
            int minCost = Integer.MAX_VALUE;
            StringBuilder sb = new StringBuilder();

            for (int j = idx; j < n; j++) {
                sb.append(s.charAt(j));

                if (isPalin(sb)) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }

            dp[idx] = minCost;
        }

        return dp[0] - 1;
    }

}
