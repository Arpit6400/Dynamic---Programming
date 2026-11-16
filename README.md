# 🎯 Dynamic Programming Revision Guide

This repository contains key problems from **Striver's DP Series (dp1 to dp54)**. It is organized into a clean folder structure to serve as a reference for future revisions.

📺 **Playlist**: [Striver's DP Series – TakeUForward](https://www.youtube.com/playlist?list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY)

## 📂 Repository Structure

```
DP/
├── 01_Introduction/          → Fibonacci (Memoization + Tabulation)
├── 02_1D_DP/                 → Climbing Stairs, Frog Jump, House Robber
├── 03_2D_Grid_DP/            → Unique Paths, Min Path Sum, Cherry Pickup
├── 04_DP_on_Subsequences/    → Knapsack, Coin Change, Subset Sum
├── 05_DP_on_Strings/         → LCS, Edit Distance, Wildcard Matching
├── 06_DP_on_Stocks/          → Buy & Sell Stock variants
├── 07_DP_on_LIS/             → LIS, Bitonic, String Chain, Number of LIS
└── 08_Partition_DP_MCM/      → MCM, Burst Balloons, Boolean Parenthesization
```

---

## 📌 DP Patterns

Here is a collective summary of the key DP patterns utilized throughout these problems:

1. **Memoization (Top-Down)**: Write the plain recursion first, then add a `dp[]` array to cache results. Return `dp[state]` if already computed.
2. **Tabulation (Bottom-Up)**: Build the solution iteratively from base cases upward, filling a `dp[]` or `dp[][]` table.
3. **Space Optimization**: Reduce 2D tabulation to 1D (or two 1D arrays `prev` / `curr`) when only the previous row/column is needed.
4. **Pick / Not-Pick Pattern**: Binary branching at each index — include the element or exclude it. Foundation of all subset/subsequence DP.
5. **Index Reduction Pattern**: Shrink the problem by one element at every recursive call. Used in knapsack, LCS, edit distance.
6. **Two-Variable State DP**: Track two changing dimensions simultaneously (e.g., two people/robots on a grid, `buy`/`sell` state in stocks).
7. **Carry-Forward DP (1D Grid)**: When the previous row's state is all that is needed, carry it forward row by row (e.g., Ninja Training).
8. **LCS-Derived Problems**: Many string DP problems reduce to computing LCS and using the result algebraically (palindrome, SCS, insertions/deletions).
9. **LIS / Patience Sorting Pattern**: Compare every previous element with the current; extend the longest valid subsequence. Use binary search for $O(N \log N)$.
10. **Partition / Interval DP (MCM)**: Split an array/string at every possible cut point $k$ in $[i, k]$ and $[k+1, j]$; minimize/maximize over all splits.
11. **State Machine DP (Stocks)**: Model buying/selling/cooldown as explicit states in a finite state machine; transition between states at each day.
12. **Combinatorial Count DP**: Instead of `max/min`, accumulate the *number* of ways to reach a target (Coin Change 2, Count Subsets, Distinct Subsequences).

---

## 📌 Problem Index (dp1 – dp54)

---

### 💡 dp1: Introduction — Fibonacci Number

- **Pattern**: Memoization → Tabulation → Space Optimization
- **Concept**:
    - **Memoization**: Cache `f(n)` in a `dp[]` array; return early on a hit.
    - **Tabulation**: Fill `dp[0..n]` bottom-up using `dp[i] = dp[i-1] + dp[i-2]`.
    - **Space Opt**: Keep only two variables `prev2` and `prev1`.
- **File**: [dp1_Fibonacci.java](01_Introduction/dp1_Fibonacci.java)

---

### 💡 dp2: Climbing Stairs (LC 70)

- **Pattern**: 1D Tabulation / Space Optimization
- **Concept**: Number of ways to reach step $N$ taking 1 or 2 steps at a time. Identical to Fibonacci recurrence: $f(n) = f(n-1) + f(n-2)$.
- **File**: [dp2_Climbing_Stairs.java](02_1D_DP/dp2_Climbing_Stairs.java)

---

### 💡 dp3: Frog Jump — Min Cost

- **Pattern**: 1D DP — Index Reduction
- **Concept**: Frog at index $i$ can jump to $i+1$ or $i+2$. Cost = `|height[i] - height[j]|`. $dp[i] = \min(dp[i-1] + |h_i - h_{i-1}|,\ dp[i-2] + |h_i - h_{i-2}|)$.
- **File**: [dp3_Frog_Jump.java](02_1D_DP/dp3_Frog_Jump.java)

---

### 💡 dp4: Frog Jump with K Distances

- **Pattern**: 1D DP — Loop over previous $K$ states
- **Concept**: Frog can jump up to $K$ steps. Inner loop from $j=1$ to $\min(K, i)$: $dp[i] = \min(dp[i],\ dp[i-j] + |h_i - h_{i-j}|)$.
- **File**: [dp4_Frog_Jump_K_Distances.java](02_1D_DP/dp4_Frog_Jump_K_Distances.java)

---

### 💡 dp5: House Robber I (LC 198)

- **Pattern**: 1D DP — Pick / Not-Pick (no two adjacent)
- **Concept**: Cannot rob two adjacent houses. $dp[i] = \max(dp[i-1],\ nums[i] + dp[i-2])$. Space-optimize to two variables.
- **File**: [dp5_House_Robber_I.java](02_1D_DP/dp5_House_Robber_I.java)

---

### 💡 dp6: House Robber II (LC 213)

- **Pattern**: 1D DP — Circular Array Trick
- **Concept**: Houses arranged in a circle → first and last cannot both be robbed. Run House Robber I twice: once on `[0..n-2]`, once on `[1..n-1]`; take the max.
- **File**: [dp6_House_Robber_II.java](02_1D_DP/dp6_House_Robber_II.java)

---

### 💡 dp7: Ninja Training — 2D DP

- **Pattern**: 2D DP — Carry-Forward Row DP
- **Concept**: Each day pick one of 3 activities; cannot repeat the same activity on consecutive days. State: `dp[day][last]`. Space-optimize to a single 1D `prev[3]` array.
- **File**: [dp7_Ninja_Training.java](03_2D_Grid_DP/dp7_Ninja_Training.java)

---

### 💡 dp8: Unique Paths (LC 62)

- **Pattern**: 2D Grid DP
- **Concept**: Count paths in an $M \times N$ grid moving only right or down. $dp[i][j] = dp[i-1][j] + dp[i][j-1]$. Space-optimize to 1D prev row.
- **File**: [dp8_Unique_Paths.java](03_2D_Grid_DP/dp8_Unique_Paths.java)

---

### 💡 dp9: Unique Paths with Obstacles (LC 63)

- **Pattern**: 2D Grid DP — with obstacle check
- **Concept**: Same as dp8 but set $dp[i][j] = 0$ wherever `grid[i][j] == -1` (obstacle). Initialize carefully when obstacles appear on the first row/column.
- **File**: [dp9_Unique_Paths_With_Obstacles.java](03_2D_Grid_DP/dp9_Unique_Paths_With_Obstacles.java)

---

### 💡 dp10: Minimum Path Sum in Grid (LC 64)

- **Pattern**: 2D Grid DP — Min-cost traversal
- **Concept**: Reach bottom-right corner with minimum sum moving right or down. $dp[i][j] = grid[i][j] + \min(dp[i-1][j],\ dp[i][j-1])$.
- **File**: [dp10_Minimum_Path_Sum_Grid.java](03_2D_Grid_DP/dp10_Minimum_Path_Sum_Grid.java)

---

### 💡 dp11: Triangle — Minimum Path Sum (LC 120)

- **Pattern**: 2D DP — Bottom-Up Triangle
- **Concept**: Start from bottom row; $dp[i][j] = triangle[i][j] + \min(dp[i+1][j],\ dp[i+1][j+1])$. Space-optimize to a single row modified in-place.
- **File**: [dp11_Triangle_Min_Path_Sum.java](03_2D_Grid_DP/dp11_Triangle_Min_Path_Sum.java)

---

### 💡 dp12: Maximum Path Sum in Matrix

- **Pattern**: 2D Grid DP — Variable start column, fixed end row
- **Concept**: Start from any cell in the first row; move to $(i+1, j-1)$, $(i+1, j)$, or $(i+1, j+1)$. Track maximum accumulated sum down to the last row.
- **File**: [dp12_Max_Path_Sum_Matrix.java](03_2D_Grid_DP/dp12_Max_Path_Sum_Matrix.java)

---

### 💡 dp13: Cherry Pickup II (LC 1444) — 3D DP

- **Pattern**: Two-Variable State DP (3D → 2D Space-Optimized)
- **Concept**: Two robots start at opposite corners of the top row and move down simultaneously. State: `dp[row][col1][col2]`. Since both move one row at a time, space-optimize to 2D.
- **File**: [dp13_Cherry_Pickup_II.java](03_2D_Grid_DP/dp13_Cherry_Pickup_II.java)

---

### 💡 dp14: Subset Sum Equal to K

- **Pattern**: Pick/Not-Pick — Boolean DP
- **Concept**: Can a subset of `arr[]` sum to exactly $K$? $dp[i][s]$ = true if first $i$ elements can form sum $s$. Base: $dp[i][0] = \text{true}$ for all $i$.
- **File**: [dp14_Subset_Sum_Equal_To_K.java](04_DP_on_Subsequences/dp14_Subset_Sum_Equal_To_K.java)

---

### 💡 dp15: Partition Equal Subset Sum (LC 416)

- **Pattern**: Subset Sum variation — target = `totalSum / 2`
- **Concept**: Check if the array can be split into two subsets with equal sum. Reduces to: does a subset with sum = `total/2` exist? Return false immediately if `total` is odd.
- **File**: [dp15_Partition_Equal_Subset_Sum.java](04_DP_on_Subsequences/dp15_Partition_Equal_Subset_Sum.java)

---

### 💡 dp16: Partition Into 2 Subsets — Min Absolute Difference

- **Pattern**: Subset Sum — Full last row analysis
- **Concept**: After filling the subset-sum DP table, the last row gives all achievable sums $s$. Answer = $\min |total - 2s|$ for all reachable $s \leq total/2$.
- **File**: [dp16_Partition_Min_Sum_Difference.java](04_DP_on_Subsequences/dp16_Partition_Min_Sum_Difference.java)

---

### 💡 dp17: Count Subsets with Sum K

- **Pattern**: Count DP — Pick/Not-Pick accumulation
- **Concept**: Count the number of subsets with sum exactly $K$. $dp[i][s] = dp[i-1][s] + dp[i-1][s - arr[i]]$ if $arr[i] \leq s$. Handle zeros carefully.
- **File**: [dp17_Count_Subsets_With_Sum_K.java](04_DP_on_Subsequences/dp17_Count_Subsets_With_Sum_K.java)

---

### 💡 dp18: Count Partitions with Given Difference (GFG)

- **Pattern**: Count Subsets — Algebraic reduction
- **Concept**: Given difference $D$, we need $S1 - S2 = D$ and $S1 + S2 = \text{total}$. So $S1 = (total + D) / 2$. Count subsets with sum = $S1$. Watch for edge cases when `(total + D)` is odd or `< 0`.
- **File**: [dp18_Count_Partitions_With_Difference.java](04_DP_on_Subsequences/dp18_Count_Partitions_With_Difference.java)

---

### 💡 dp19: 0/1 Knapsack

- **Pattern**: Classic 0/1 Knapsack — Pick/Not-Pick with weight constraint
- **Concept**: For each item, either include it (if `wt[i] <= W`) or exclude it. $dp[i][w] = \max(val[i] + dp[i-1][w-wt[i]],\ dp[i-1][w])$. Space-optimize to 1D traversed **right to left**.
- **File**: [dp19_0_1_Knapsack.java](04_DP_on_Subsequences/dp19_0_1_Knapsack.java)

---

### 💡 dp20: Minimum Coins (LC 322)

- **Pattern**: Unbounded Knapsack — Min-cost count
- **Concept**: Each coin can be used any number of times. $dp[i][t] = \min(1 + dp[i][t - coins[i]],\ dp[i-1][t])$. Space-optimize to 1D traversed **left to right** (unbounded).
- **File**: [dp20_Minimum_Coins.java](04_DP_on_Subsequences/dp20_Minimum_Coins.java)

---

### 💡 dp21: Target Sum (LC 494)

- **Pattern**: Count Partitions with Given Difference
- **Concept**: Assign `+` or `-` to each number. This creates two groups $S1$ and $S2$ where $S1 - S2 = \text{target}$. Reduces to counting subsets with sum = $(total + target) / 2$.
- **File**: [dp21_Target_Sum.java](04_DP_on_Subsequences/dp21_Target_Sum.java)

---

### 💡 dp22: Coin Change II (LC 518)

- **Pattern**: Unbounded Knapsack — Count ways
- **Concept**: Count distinct combinations of coins summing to amount. $dp[i][t] = dp[i-1][t] + dp[i][t - coins[i]]$ (reuse allowed → same row, **left to right**).
- **File**: [dp22_Coin_Change_II.java](04_DP_on_Subsequences/dp22_Coin_Change_II.java)

---

### 💡 dp23: Unbounded Knapsack

- **Pattern**: Unbounded Knapsack — Max value, unlimited reuse
- **Concept**: Items can be picked any number of times. $dp[i][w] = \max(val[i] + dp[i][w - wt[i]],\ dp[i-1][w])$. Same row is referenced for reuse (left to right).
- **File**: [dp23_Unbounded_Knapsack.java](04_DP_on_Subsequences/dp23_Unbounded_Knapsack.java)

---

### 💡 dp24: Rod Cutting Problem (GFG)

- **Pattern**: Unbounded Knapsack — length as weight, price as value
- **Concept**: Cut a rod of length $N$ into pieces to maximize price. Each cut of length $i$ costs `price[i-1]`. Equivalent to unbounded knapsack where `wt[i] = i`, reuse allowed.
- **File**: [dp24_Rod_Cutting.java](04_DP_on_Subsequences/dp24_Rod_Cutting.java)

---

### 💡 dp25: Longest Common Subsequence — LCS (LC 1143)

- **Pattern**: 2D String DP — Index Reduction
- **Concept**: If `s1[i] == s2[j]`: $1 + dp[i-1][j-1]$. Else: $\max(dp[i-1][j],\ dp[i][j-1])$. Space-optimize to two 1D arrays `prev` and `curr`.
- **File**: [dp25_Longest_Common_Subsequence.java](05_DP_on_Strings/dp25_Longest_Common_Subsequence.java)

---

### 💡 dp26: Print the LCS

- **Pattern**: LCS Backtracking — Traceback through DP table
- **Concept**: After filling the LCS table, backtrack from `dp[n][m]`. If `s1[i-1]==s2[j-1]` → character is in LCS, move diagonal. Otherwise move towards the larger neighbor (`up` or `left`). Build string from end.
- **File**: [dp26_Print_LCS.java](05_DP_on_Strings/dp26_Print_LCS.java)

---

### 💡 dp27: Longest Common Substring (GFG)

- **Pattern**: 2D String DP — Reset on mismatch
- **Concept**: Unlike LCS, a substring must be contiguous. If `s1[i]==s2[j]`: $dp[i][j] = 1 + dp[i-1][j-1]$. If not equal: $dp[i][j] = 0$. Answer = global max of `dp[i][j]`.
- **File**: [dp27_Longest_Common_Substring.java](05_DP_on_Strings/dp27_Longest_Common_Substring.java)

---

### 💡 dp28: Longest Palindromic Subsequence (LC 516)

- **Pattern**: LCS Derived
- **Concept**: LPS of string $S$ = LCS of $S$ and its reverse $S'$. Run standard LCS on $(S, \text{reverse}(S))$.
- **File**: [dp28_Longest_Palindromic_Subsequence.java](05_DP_on_Strings/dp28_Longest_Palindromic_Subsequence.java)

---

### 💡 dp29: Minimum Insertions to Make a String Palindrome (LC 1312)

- **Pattern**: LCS Derived
- **Concept**: Min insertions = $N - \text{LPS}(S)$ = $N - \text{LCS}(S, \text{reverse}(S))$. Each character not in the palindromic subsequence needs one insertion.
- **File**: [dp29_Min_Insertions_To_Make_Palindrome.java](05_DP_on_Strings/dp29_Min_Insertions_To_Make_Palindrome.java)

---

### 💡 dp30: Minimum Insertions/Deletions to Convert String A → B (LC 583 variant)

- **Pattern**: LCS Derived
- **Concept**: To convert $A$ to $B$: delete characters in $A$ not in LCS, then insert characters in $B$ not in LCS. Answer = $(|A| - \text{LCS}) + (|B| - \text{LCS})$.
- **File**: [dp30_Min_Insertions_Deletions_To_Convert.java](05_DP_on_Strings/dp30_Min_Insertions_Deletions_To_Convert.java)

---

### 💡 dp31: Shortest Common Supersequence (LC 1092)

- **Pattern**: LCS Derived — Build from traceback
- **Concept**: SCS length = $|A| + |B| - \text{LCS}$. To reconstruct: traceback through LCS table — when characters match, include once; otherwise include the character from the string with the larger DP value.
- **File**: [dp31_Shortest_Common_Supersequence.java](05_DP_on_Strings/dp31_Shortest_Common_Supersequence.java)

---

### 💡 dp32: Distinct Subsequences (LC 115)

- **Pattern**: Count DP — String matching
- **Concept**: Count ways to form string $T$ as a subsequence of $S$. If `s[i]==t[j]`: $dp[i][j] = dp[i-1][j-1] + dp[i-1][j]$ (use it or skip it). If not: $dp[i][j] = dp[i-1][j]$.
- **File**: [dp32_Distinct_Subsequences.java](05_DP_on_Strings/dp32_Distinct_Subsequences.java)

---

### 💡 dp33: Edit Distance (LC 72)

- **Pattern**: 2D String DP — Insert / Delete / Replace
- **Concept**: $dp[i][j]$ = min ops to convert $s1[0..i]$ → $s2[0..j]$. If chars match: $dp[i-1][j-1]$. Else: $1 + \min(\text{insert},\ \text{delete},\ \text{replace})$.
- **File**: [dp33_Edit_Distance.java](05_DP_on_Strings/dp33_Edit_Distance.java)

---

### 💡 dp34: Wildcard Matching (LC 44)

- **Pattern**: 2D String DP — Pattern matching with `?` and `*`
- **Concept**: `?` matches any single character. `*` matches any sequence (including empty). If `p[j] == '*'`: $dp[i][j] = dp[i-1][j]$ (use `*`) OR $dp[i][j-1]$ (ignore `*`). Handle `*` at start of pattern as base case.
- **File**: [dp34_Wildcard_Matching.java](05_DP_on_Strings/dp34_Wildcard_Matching.java)

---

### 💡 dp35: Best Time to Buy and Sell Stock I (LC 121)

- **Pattern**: Greedy / Simple DP — Single transaction
- **Concept**: Track `minPrice` seen so far; at each day compute `price - minPrice`. Max profit = max of all such values. No DP table needed; $O(N)$ one-pass.
- **File**: [dp35_Best_Time_Buy_Sell_Stock_I.java](06_DP_on_Stocks/dp35_Best_Time_Buy_Sell_Stock_I.java)

---

### 💡 dp36: Best Time to Buy and Sell Stock II (LC 122)

- **Pattern**: State Machine DP — Unlimited transactions
- **Concept**: State = `(day, canBuy)`. If `canBuy`: take max of `buy` or `skip`. If holding: take max of `sell` or `skip`. Space-optimize: track `aheadNotBuy` and `aheadBuy`.
- **File**: [dp36_Best_Time_Buy_Sell_Stock_II.java](06_DP_on_Stocks/dp36_Best_Time_Buy_Sell_Stock_II.java)

---

### 💡 dp37: Best Time to Buy and Sell Stock III (LC 123)

- **Pattern**: State Machine DP — At most 2 transactions
- **Concept**: State = `(day, canBuy, transactionsLeft)`. Extend stock II with a `cap` dimension tracking remaining allowed transactions (capped at 2).
- **File**: [dp37_Best_Time_Buy_Sell_Stock_III.java](06_DP_on_Stocks/dp37_Best_Time_Buy_Sell_Stock_III.java)

---

### 💡 dp38: Best Time to Buy and Sell Stock IV (LC 188)

- **Pattern**: State Machine DP — At most K transactions
- **Concept**: Generalize dp37: state = `(day, canBuy, k)`. 3D DP table of size `n × 2 × (k+1)`. Space-optimize to 2D.
- **File**: [dp38_Best_Time_Buy_Sell_Stock_IV.java](06_DP_on_Stocks/dp38_Best_Time_Buy_Sell_Stock_IV.java)

---

### 💡 dp39: Stock with Cooldown (LC 309)

- **Pattern**: State Machine DP — Cooldown state
- **Concept**: After selling, must skip one day (cooldown). State = `(day, canBuy)`. When selling: next state jumps to `day+2` instead of `day+1`.
- **File**: [dp39_Stock_With_Cooldown.java](06_DP_on_Stocks/dp39_Stock_With_Cooldown.java)

---

### 💡 dp40: Stock with Transaction Fee (LC 714)

- **Pattern**: State Machine DP — Fee on sell
- **Concept**: Unlimited transactions but each sell incurs a fee. Same as dp36 but on sell: `profit = prices[i] - fee`. State = `(day, canBuy)`.
- **File**: [dp40_Stock_With_Transaction_Fee.java](06_DP_on_Stocks/dp40_Stock_With_Transaction_Fee.java)

---

### 💡 dp41: Longest Increasing Subsequence — Recursion/Memoization (LC 300)

- **Pattern**: LIS — 2D Memoization `dp[idx][prevIdx]`
- **Concept**: State = `(currentIdx, prevIdx)`. At each index, either skip or pick (if `arr[idx] > arr[prevIdx]`). Memoize with `idx` shifted by 1 to handle `prevIdx = -1` case.
- **File**: [dp41_Longest_Increasing_Subsequence.java](07_DP_on_LIS/dp41_Longest_Increasing_Subsequence.java)

---

### 💡 dp42: LIS — 1D DP + Print LIS (LC 300)

- **Pattern**: LIS — 1D Tabulation with hash array
- **Concept**: $dp[i] = 1 + \max(dp[j])$ for all $j < i$ where $arr[j] < arr[i]$. Use a `hash[]` array storing the previous index to reconstruct and print the actual LIS.
- **File**: [dp42_Print_LIS.java](07_DP_on_LIS/dp42_Print_LIS.java)

---

### 💡 dp43: LIS — Binary Search / Patience Sorting (LC 300)

- **Pattern**: LIS — $O(N \log N)$ with `tails[]` array
- **Concept**: Maintain a `tails[]` array where `tails[i]` = smallest tail element of all LIS of length `i+1`. For each element, binary search for its position (`lowerBound`). The length of `tails` is the LIS length.
- **File**: [dp43_LIS_Binary_Search.java](07_DP_on_LIS/dp43_LIS_Binary_Search.java)

---

### 💡 dp44: Largest Divisible Subset (LC 368)

- **Pattern**: LIS Variant — Divisibility condition
- **Concept**: Sort the array. Replace the LIS comparison `arr[j] < arr[i]` with `arr[i] % arr[j] == 0`. Use a `hash[]` array to reconstruct the actual subset.
- **File**: [dp44_Largest_Divisible_Subset.java](07_DP_on_LIS/dp44_Largest_Divisible_Subset.java)

---

### 💡 dp45: Longest String Chain (LC 1048)

- **Pattern**: LIS Variant — String predecessor check
- **Concept**: Sort words by length. Replace the LIS comparison with a `isPredecessor(shorter, longer)` check: longer must be obtainable by inserting exactly one character anywhere into shorter.
- **File**: [dp45_Longest_String_Chain.java](07_DP_on_LIS/dp45_Longest_String_Chain.java)

---

### 💡 dp46: Longest Bitonic Subsequence (GFG)

- **Pattern**: LIS + LDS (Longest Decreasing Subsequence)
- **Concept**: Compute LIS from left → right (`dp1[]`) and LDS from right → left (`dp2[]`; equivalent to LIS from right). Answer = $\max(dp1[i] + dp2[i] - 1)$ for each index $i$ (excluding all-increasing or all-decreasing sequences).
- **File**: [dp46_Longest_Bitonic_Subsequence.java](07_DP_on_LIS/dp46_Longest_Bitonic_Subsequence.java)

---

### 💡 dp47: Number of Longest Increasing Subsequences (LC 673)

- **Pattern**: LIS + Count Array
- **Concept**: Maintain two arrays: `dp[i]` = LIS length ending at $i$, `ct[i]` = number of such LIS. When `dp[j]+1 == dp[i]`: add `ct[j]` to `ct[i]`. When `dp[j]+1 > dp[i]`: reset `ct[i] = ct[j]`. Sum `ct[i]` for all $i$ where `dp[i] == maxLen`.
- **File**: [dp47_Number_Of_LIS.java](07_DP_on_LIS/dp47_Number_Of_LIS.java)

---

### 💡 dp48: Matrix Chain Multiplication — Recursion (GFG)

- **Pattern**: Partition / Interval DP — MCM
- **Concept**: For a chain of matrices $A_i$ to $A_j$, try every split point $k \in [i, j-1]$: cost = $\text{cost}(i,k) + \text{cost}(k+1,j) + \text{dims}[i-1] \times \text{dims}[k] \times \text{dims}[j]$. Answer = `solve(1, n)`.
- **File**: [dp48_Matrix_Chain_Multiplication.java](08_Partition_DP_MCM/dp48_Matrix_Chain_Multiplication.java)

---

### 💡 dp49: MCM — Tabulation (Bottom-Up)

- **Pattern**: Interval DP — Diagonal-based filling
- **Concept**: Fill the DP table by increasing interval lengths. Outer loop: `len` from 2 to $n$. Inner loop: starting index $i$; ending index $j = i + len - 1$. Try all $k$ splits.
- **File**: [dp49_MCM_Tabulation.java](08_Partition_DP_MCM/dp49_MCM_Tabulation.java)

---

### 💡 dp50: Minimum Cost to Cut a Stick (LC 1547)

- **Pattern**: Interval DP — MCM variant
- **Concept**: Insert `0` and `n` into the cuts array. For subproblem on interval `[cuts[i], cuts[j]]`, try each cut $k$ in between: cost = `cuts[j] - cuts[i]` + cost of left + cost of right.
- **File**: [dp50_Min_Cost_Cut_Stick.java](08_Partition_DP_MCM/dp50_Min_Cost_Cut_Stick.java)

---

### 💡 dp51: Burst Balloons (LC 312)

- **Pattern**: Interval DP — Last balloon to burst trick
- **Concept**: Insert sentinel values `1` at both ends. Instead of thinking which balloon bursts **first**, think which one bursts **last** in `[i,j]`. Last burst = $k$: gain = `nums[i-1] * nums[k] * nums[j+1]` + cost of left and right subproblems.
- **File**: [dp51_Burst_Balloons.java](08_Partition_DP_MCM/dp51_Burst_Balloons.java)

---

### 💡 dp52: Boolean Parenthesization (GFG)

- **Pattern**: Interval DP — Count true/false outcomes
- **Concept**: For expression $[i..j]$ with operators, count ways it evaluates to `True` and `False`. Split at each operator $k$: combine left `(T,F)` and right `(T,F)` based on operator type (`AND`, `OR`, `XOR`).
- **File**: [dp52_Boolean_Parenthesization.java](08_Partition_DP_MCM/dp52_Boolean_Parenthesization.java)

---

### 💡 dp53: Palindrome Partitioning II (LC 132)

- **Pattern**: Partition DP — Minimum cuts
- **Concept**: Precompute `isPalin[i][j]` for all substrings. Then $dp[i]$ = min cuts to partition `s[0..i]`. For each $i$, try all $j \leq i$ where `isPalin[j][i]` is true: $dp[i] = \min(dp[i],\ dp[j-1] + 1)$.
- **File**: [dp53_Palindrome_Partitioning_II.java](08_Partition_DP_MCM/dp53_Palindrome_Partitioning_II.java)

---

### 💡 dp54: Partition Array for Maximum Sum (LC 1043)

- **Pattern**: Partition DP — 1D
- **Concept**: Partition array into subarrays of length at most $K$. For each index $i$, look back up to $K$ steps: $dp[i] = \max(dp[i-len] + \text{maxInWindow} \times len)$ for `len` from 1 to $\min(K, i)$.
- **File**: [dp54_Partition_Array_For_Max_Sum.java](08_Partition_DP_MCM/dp54_Partition_Array_For_Max_Sum.java)

---

## 🗂️ Quick Section Reference

| Section | Files | Key Pattern |
|---|---|---|
| [01_Introduction](01_Introduction/) | dp1 | Memoization → Tabulation → Space Opt |
| [02_1D_DP](02_1D_DP/) | dp2–dp6 | Index Reduction, Pick/Not-Pick |
| [03_2D_Grid_DP](03_2D_Grid_DP/) | dp7–dp13 | 2D/3D Grid, Carry-Forward |
| [04_DP_on_Subsequences](04_DP_on_Subsequences/) | dp14–dp24 | 0/1 Knapsack, Unbounded, Count |
| [05_DP_on_Strings](05_DP_on_Strings/) | dp25–dp34 | LCS & Derived, Edit Distance |
| [06_DP_on_Stocks](06_DP_on_Stocks/) | dp35–dp40 | State Machine DP |
| [07_DP_on_LIS](07_DP_on_LIS/) | dp41–dp47 | LIS Variants, Patience Sorting |
| [08_Partition_DP_MCM](08_Partition_DP_MCM/) | dp48–dp54 | Interval DP, Last-to-burst trick |
