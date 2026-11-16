// GFG: https://www.geeksforgeeks.org/problems/boolean-parenthesization5610/1
public class dp52_Boolean_Parenthesization {

    // Boolean Parenthesization - Recursion
    public static int booleanParenthesization(int i, int j, int isTrue, String exp, int mod) {
        if (i > j)
            return 0;
        if (i == j) {
            if (isTrue == 1)
                return exp.charAt(i) == 'T' ? 1 : 0;
            else
                return exp.charAt(i) == 'F' ? 1 : 0;
        }
        int ways = 0;
        for (int idx = i + 1; idx <= j - 1; idx += 2) {
            int LT = booleanParenthesization(i, idx - 1, 1, exp, mod);
            int LF = booleanParenthesization(i, idx - 1, 0, exp, mod);
            int RT = booleanParenthesization(idx + 1, j, 1, exp, mod);
            int RF = booleanParenthesization(idx + 1, j, 0, exp, mod);

            if (exp.charAt(idx) == '&') {
                if (isTrue == 1) {
                    ways = (ways + (LT * RT) % mod) % mod;
                } else {
                    ways = (ways + (LF * RT) % mod + (LF * RF) % mod + (LT * RF) % mod) % mod;
                }
            } else if (exp.charAt(idx) == '|') {
                if (isTrue == 1) {
                    ways = (ways + (LT * RT) % mod + (LF * RT) % mod + (LF * RT) % mod) % mod;
                } else {
                    ways = (ways + (LF * RF) % mod) % mod;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (LF * RT) % mod + (LT * RF) % mod) % mod;
                } else {
                    ways = (ways + (LT * RT) % mod + (LF * RF) % mod) % mod;
                }
            }
        }
        return ways;
    }

    // Memoization
    public static int booleanParenthesizationMemo(int i, int j, int isTrue, String exp, int mod, int[][][] dp) {
        if (i > j)
            return 0;
        if (i == j) {
            if (isTrue == 1)
                return exp.charAt(i) == 'T' ? 1 : 0;
            else
                return exp.charAt(i) == 'F' ? 1 : 0;
        }
        if (dp[i][j][isTrue] != -1)
            return dp[i][j][isTrue];
        int ways = 0;
        for (int idx = i + 1; idx <= j - 1; idx += 2) {
            int LT = booleanParenthesizationMemo(i, idx - 1, 1, exp, mod, dp);
            int LF = booleanParenthesizationMemo(i, idx - 1, 0, exp, mod, dp);
            int RT = booleanParenthesizationMemo(idx + 1, j, 1, exp, mod, dp);
            int RF = booleanParenthesizationMemo(idx + 1, j, 0, exp, mod, dp);

            if (exp.charAt(idx) == '&') {
                if (isTrue == 1) {
                    ways = (ways + (LT * RT) % mod) % mod;
                } else {
                    ways = (ways + (LF * RT) % mod + (LF * RF) % mod + (LT * RF) % mod) % mod;
                }
            } else if (exp.charAt(idx) == '|') {
                if (isTrue == 1) {
                    ways = (ways + (LT * RT) % mod + (LF * RT) % mod + (LF * RT) % mod) % mod;
                } else {
                    ways = (ways + (LF * RF) % mod) % mod;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (LF * RT) % mod + (LT * RF) % mod) % mod;
                } else {
                    ways = (ways + (LT * RT) % mod + (LF * RF) % mod) % mod;
                }
            }
        }

        return dp[i][j][isTrue] = ways;
    }

    // Tabulation Conversion

    public static int booleanParenthesizationTabulation(String exp) {
        int mod = 1_000_000_007;
        int n = exp.length();

        int[][][] dp = new int[n][n][2];

        // Process starting indices from right to left
        for (int i = n - 1; i >= 0; i -= 2) {

            // Process ending indices from left to right
            for (int j = i; j < n; j += 2) {

                // Base case: single boolean operand
                if (i == j) {
                    dp[i][j][1] = exp.charAt(i) == 'T' ? 1 : 0;
                    dp[i][j][0] = exp.charAt(i) == 'F' ? 1 : 0;
                    continue;
                }

                // Calculate both TRUE and FALSE ways
                for (int isTrue = 0; isTrue <= 1; isTrue++) {

                    long ways = 0;

                    // Try every operator as the last operation
                    for (int idx = i + 1; idx <= j - 1; idx += 2) {

                        int LT = dp[i][idx - 1][1];
                        int LF = dp[i][idx - 1][0];

                        int RT = dp[idx + 1][j][1];
                        int RF = dp[idx + 1][j][0];

                        char operator = exp.charAt(idx);

                        if (operator == '&') {
                            if (isTrue == 1) {
                                ways = (ways + (long) LT * RT) % mod;
                            } else {
                                ways = (ways + (long) LF * RT) % mod;
                                ways = (ways + (long) LF * RF) % mod;
                                ways = (ways + (long) LT * RF) % mod;
                            }
                        }

                        else if (operator == '|') {
                            if (isTrue == 1) {
                                ways = (ways + (long) LT * RT) % mod;
                                ways = (ways + (long) LT * RF) % mod;
                                ways = (ways + (long) LF * RT) % mod;
                            } else {
                                ways = (ways + (long) LF * RF) % mod;
                            }
                        }

                        else { // XOR
                            if (isTrue == 1) {
                                ways = (ways + (long) LF * RT) % mod;
                                ways = (ways + (long) LT * RF) % mod;
                            } else {
                                ways = (ways + (long) LT * RT) % mod;
                                ways = (ways + (long) LF * RF) % mod;
                            }
                        }
                    }

                    dp[i][j][isTrue] = (int) ways;
                }
            }
        }

        return dp[0][n - 1][1];
    }

    // non modular version
    public static int booleanParenthesizationTabulation2(String exp) {
        int n = exp.length();

        long[][][] dp = new long[n][n][2];

        for (int i = n - 1; i >= 0; i -= 2) {
            for (int j = i; j < n; j += 2) {

                if (i == j) {
                    dp[i][j][1] = exp.charAt(i) == 'T' ? 1 : 0;
                    dp[i][j][0] = exp.charAt(i) == 'F' ? 1 : 0;
                    continue;
                }

                for (int isTrue = 0; isTrue <= 1; isTrue++) {
                    long ways = 0;

                    for (int idx = i + 1; idx <= j - 1; idx += 2) {

                        long LT = dp[i][idx - 1][1];
                        long LF = dp[i][idx - 1][0];
                        long RT = dp[idx + 1][j][1];
                        long RF = dp[idx + 1][j][0];

                        char operator = exp.charAt(idx);

                        if (operator == '&') {
                            if (isTrue == 1) {
                                ways += LT * RT;
                            } else {
                                ways += LF * RT + LF * RF + LT * RF;
                            }
                        } else if (operator == '|') {
                            if (isTrue == 1) {
                                ways += LT * RT + LT * RF + LF * RT;
                            } else {
                                ways += LF * RF;
                            }
                        } else {
                            if (isTrue == 1) {
                                ways += LF * RT + LT * RF;
                            } else {
                                ways += LT * RT + LF * RF;
                            }
                        }
                    }

                    dp[i][j][isTrue] = ways;
                }
            }
        }

        return (int) dp[0][n - 1][1];
    }

}
