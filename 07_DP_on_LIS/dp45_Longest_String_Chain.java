
// LC: https://leetcode.com/problems/longest-string-chain/
import java.util.*;

public class dp45_Longest_String_Chain {
    public int longestStrChain(String[] arr) {
        Arrays.sort(arr, (s1, s2) -> s1.length() - s2.length());
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLength = 1;

        for (int idx = 0; idx < n; idx++) {
            for (int prev = 0; prev < idx; prev++) {
                if (checkPossible(arr[idx], arr[prev])) {
                    dp[idx] = Math.max(dp[idx], dp[prev] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[idx]);
        }

        return maxLength;
    }

    private boolean checkPossible(String string1, String string2) {
        if (string1.length() != string2.length() + 1) {
            return false;
        }
        int first = 0;
        int second = 0;
        while (first != string1.length()) {
            if (second != string2.length() && string1.charAt(first) == string2.charAt(second)) {
                first++;
                second++;
            } else {
                first++;
            }
        }
        return first == string1.length() && second == string2.length();
    }
}
