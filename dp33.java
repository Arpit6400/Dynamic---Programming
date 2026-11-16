public class dp33 {
    public static void main(String[] args) {
        
    }

    // Edit distance - recursion
    public int editdistanceRecursion(int i, int j, String S1, String S2){
        if(i < 0) return j + 1;
        if(j < 0) return i + 1;

        if (S1.charAt(i) == S2.charAt(j)) {
            return 0 + editdistanceRecursion(i - 1, j - 1, S1, S2);
        }
        int insertion = 1 + editdistanceRecursion(i, j - 1, S1, S2);
        int delete = 1 + editdistanceRecursion(i - 1, j, S1, S2);
        int replace = 1 + editdistanceRecursion(i - 1, j - 1, S1, S2);
        return Math.min(insertion, Math.min(delete, replace));
    }

    // Edit distance - Rec Shiftinng of index
    public int editdistanceRecursion2(int i, int j, String S1, String S2){
        if(i == 0) return j;
        if(j == 0) return i;

        if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
            return 0 + editdistanceRecursion2(i - 1, j - 1, S1, S2);
        }

        return 1 + Math.min(
                editdistanceRecursion2(i, j - 1, S1, S2),
                Math.min(editdistanceRecursion2(i - 1, j, S1, S2),
                        editdistanceRecursion2(i - 1, j - 1, S1, S2)));
    }

    // Memoization - agent add

    // Tabulation

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Base case

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        // Recursion
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = 0 + dp[i - 1][j - 1];
                }
                else{
                    dp[i][j] = 1 + Math.min(dp[i][j - 1] , 
                        Math.min( dp[i - 1][j] , dp[i - 1][j -1]));
                }
            }
        }

        return dp[n][m];
    }

}
