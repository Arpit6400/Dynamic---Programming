# 🎯 Dynamic Programming Revision Guide

This repository contains all key problems from **Striver's Dynamic Programming Series (DP 1 to DP 56)**. It is organized into a clean folder structure to serve as a fast and comprehensive reference for future revisions.

📺 **YouTube Playlist**: [Striver's DP Series – TakeUForward](https://www.youtube.com/playlist?list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY)

---

## 📂 Repository Structure

```
DP/
├── README.md                                    → This comprehensive revision guide
├── 01_Introduction/                             → DP 1: Fibonacci (Memoization, Tabulation, Space Opt)
├── 02_1D_DP/                                    → DP 2 to DP 6: Climbing Stairs, Frog Jump, House Robber
├── 03_2D_Grid_DP/                               → DP 7 to DP 13: Ninja Training, Grid Paths, Triangle, Falling Path, Cherry Pickup II
├── 04_DP_on_Subsequences/                       → DP 14 to DP 24: Subset Sum, Knapsack, Minimum Coins, Rod Cutting
├── 05_DP_on_Strings/                            → DP 25 to DP 34: LCS, LPS, SCS, Edit Distance, Wildcard Matching
├── 06_DP_on_Stocks/                             → DP 35 to DP 40: Best Time to Buy and Sell Stock (I to IV, Cooldown, Fee)
├── 07_DP_on_LIS/                                → DP 41 to DP 47: LIS Variants, Print LIS, Binary Search, Bitonic, Number of LIS
├── 08_Partition_DP_MCM/                         → DP 48 to DP 54: MCM, Cut Stick, Burst Balloons, Boolean Parenthesization
└── 09_DP_on_Squares/                            → DP 55 to DP 56: Maximal Rectangle, Count Square Submatrices
```

---

## 📌 Dynamic Programming Patterns

Here is a collective summary of the fundamental DP patterns and paradigms utilized across this series:

1. **Memoization (Top-Down)**: Start with the plain recursive branching logic, cache subproblem outcomes in a `dp[]` table with initial sentinel values (e.g., `-1`), and return cached values in $O(1)$.
2. **Tabulation (Bottom-Up)**: Convert the recursive recurrence into iterative loops moving upwards from the base cases to avoid call-stack overhead and recursion limit issues.
3. **Space Optimization**: When computing $dp[i]$ or $dp[i][j]$ requires only the immediately preceding row/element, compress the table to 1D arrays (`prev` and `curr`) or standalone variables ($O(1)$ space).
4. **Pick / Not-Pick Pattern**: At each index, branch into two decisions: include the current element (reduce target/weight) or exclude it (skip to next). Forms the foundation of all subset/subsequence problems.
5. **Infinite Supply (Unbounded Reuse)**: When elements can be reused indefinitely (Unbounded Knapsack, Coin Change), stay at the same index ($idx$) during the "pick" transition rather than advancing to $idx - 1$.
6. **2D & 3D Grid Traversal**: Moving through matrices with directional constraints. State corresponds to current coordinates $(i, j)$ or multiple agents $(i, j_1, j_2)$.
7. **LCS & String DP Derived Patterns**: Aligning two sequences with matching/mismatching choices. Forms the basis for Longest Palindromic Subsequence, Shortest Common Supersequence, and Minimum Deletions/Insertions.
8. **State-Machine DP (Stocks)**: Track discrete operational states (`canBuy`, `cooldown`, transaction capacity `cap`) transitioning day by day in a finite state machine.
9. **LIS / Patience Sorting Pattern**: Compare each element with earlier elements ($j < i$) to extend an increasing sequence ($O(N^2)$), or maintain greedy minimum tails with binary search ($O(N \log N)$).
10. **Partition / Interval DP (MCM)**: When a contiguous range $[i, j]$ can be broken down at any intermediate cut $k$, iterate over all possible partition points $k \in [i, j-1]$ and combine optimal sub-results.
11. **Reverse-Order Partitioning (Last to Burst)**: In problems like Burst Balloons where elements interact with neighbors, framing the choice around which element is destroyed/chosen *last* makes the subproblems completely independent.
12. **DP on Rectangles & Histograms**: Transform multi-row binary matrices into row-wise height histograms to run monotonic stack algorithms in $O(N \times M)$ total time.
13. **Square Submatrix Expansion**: Maintain the length of the largest square ending at $(i, j)$ by taking $1 + \min(\text{top}, \text{left}, \text{diagonal})$ when the cell is `1`.

---

## 📌 Problem Index (DP 1 – DP 56)

### 01. Introduction
* **DP 1: Fibonacci Number**
  * *Pattern*: Memoization → Tabulation → Space Optimization ($O(1)$)
  * *Recurrence*: $f(n) = f(n-1) + f(n-2)$
  * *Links*: [LeetCode 509](https://leetcode.com/problems/fibonacci-number/) | [GFG](https://www.geeksforgeeks.org/problems/nth-fibonacci-number1359/1)
  * *File*: [dp1_Fibonacci.java](01_Introduction/dp1_Fibonacci.java)

---

### 02. 1D Dynamic Programming
* **DP 2: Climbing Stairs**
  * *Pattern*: 1D Tabulation / Space Optimization
  * *Recurrence*: Number of ways to reach step $n$ via 1 or 2 steps: $f(n) = f(n-1) + f(n-2)$
  * *Links*: [LeetCode 70](https://leetcode.com/problems/climbing-stairs/) | [GFG](https://www.geeksforgeeks.org/problems/count-ways-to-reach-the-nth-stair-1587115620/1)
  * *File*: [dp2_Climbing_Stairs.java](02_1D_DP/dp2_Climbing_Stairs.java)

* **DP 3: Frog Jump**
  * *Pattern*: 1D DP — Min Cost Path
  * *Recurrence*: $dp[i] = \min(dp[i-1] + |h_i - h_{i-1}|,\ dp[i-2] + |h_i - h_{i-2}|)$
  * *Links*: [GFG Geek Jump](https://www.geeksforgeeks.org/problems/geek-jump/1)
  * *File*: [dp3_Frog_Jump.java](02_1D_DP/dp3_Frog_Jump.java)

* **DP 4: Frog Jump with K Distances**
  * *Pattern*: 1D DP — Multi-branching minimum
  * *Recurrence*: For each $j \in [1, K]$: $dp[i] = \min_{j}(dp[i-j] + |h_i - h_{i-j}|)$
  * *Links*: [GFG Minimal Cost](https://www.geeksforgeeks.org/problems/minimal-cost/1)
  * *File*: [dp4_Frog_Jump_K_Distances.java](02_1D_DP/dp4_Frog_Jump_K_Distances.java)

* **DP 5: Maximum Sum of Non-Adjacent Elements (House Robber I)**
  * *Pattern*: Pick / Not-Pick
  * *Recurrence*: $dp[i] = \max(dp[i-1],\ nums[i] + dp[i-2])$
  * *Links*: [LeetCode 198](https://leetcode.com/problems/house-robber/) | [GFG](https://www.geeksforgeeks.org/problems/max-sum-without-adjacents2430/1)
  * *File*: [dp5_House_Robber_I.java](02_1D_DP/dp5_House_Robber_I.java)

* **DP 6: House Robber II**
  * *Pattern*: Circular Array Decomposition
  * *Concept*: First and last house cannot both be selected. Run House Robber I twice: on `nums[0..n-2]` and `nums[1..n-1]`; take the maximum.
  * *Links*: [LeetCode 213](https://leetcode.com/problems/house-robber-ii/)
  * *File*: [dp6_House_Robber_II.java](02_1D_DP/dp6_House_Robber_II.java)

---

### 03. 2D / 3D DP on Grids
* **DP 7: Ninja's Training**
  * *Pattern*: Carry-Forward Row DP with Activity Constraints
  * *Concept*: $dp[day][last] = \max_{task \ne last}(points[day][task] + dp[day-1][task])$
  * *Links*: [GFG Geeks Training](https://www.geeksforgeeks.org/problems/geeks-training/1) | [CodingNinjas](https://www.naukri.com/code360/problems/ninja-s-training_3621003)
  * *File*: [dp7_Ninja_Training.java](03_2D_Grid_DP/dp7_Ninja_Training.java)

* **DP 8: Grid Unique Paths**
  * *Pattern*: 2D Grid DP — Combinatorial Path Counting
  * *Recurrence*: $dp[i][j] = dp[i-1][j] + dp[i][j-1]$
  * *Links*: [LeetCode 62](https://leetcode.com/problems/unique-paths/) | [GFG](https://www.geeksforgeeks.org/problems/number-of-unique-paths5339/1)
  * *File*: [dp8_Unique_Paths.java](03_2D_Grid_DP/dp8_Unique_Paths.java)

* **DP 9: Unique Paths II (Maze with Obstacles)**
  * *Pattern*: 2D Grid DP with Obstacle Invalidation
  * *Concept*: Same as DP 8, setting $dp[i][j] = 0$ wherever an obstacle is encountered.
  * *Links*: [LeetCode 63](https://leetcode.com/problems/unique-paths-ii/)
  * *File*: [dp9_Unique_Paths_With_Obstacles.java](03_2D_Grid_DP/dp9_Unique_Paths_With_Obstacles.java)

* **DP 10: Minimum Path Sum in Grid**
  * *Pattern*: 2D Grid DP — Minimum Cost Accumulation
  * *Recurrence*: $dp[i][j] = grid[i][j] + \min(dp[i-1][j],\ dp[i][j-1])$
  * *Links*: [LeetCode 64](https://leetcode.com/problems/minimum-path-sum/)
  * *File*: [dp10_Minimum_Path_Sum_Grid.java](03_2D_Grid_DP/dp10_Minimum_Path_Sum_Grid.java)

* **DP 11: Triangle — Minimum Path Sum**
  * *Pattern*: Bottom-Up Triangular DP
  * *Concept*: Start from bottom row; $dp[i][j] = triangle[i][j] + \min(dp[i+1][j],\ dp[i+1][j+1])$
  * *Links*: [LeetCode 120](https://leetcode.com/problems/triangle/) | [GFG](https://www.geeksforgeeks.org/problems/triangle-path-sum/1)
  * *File*: [dp11_Triangle_Min_Path_Sum.java](03_2D_Grid_DP/dp11_Triangle_Min_Path_Sum.java)

* **DP 12: Minimum / Maximum Falling Path Sum**
  * *Pattern*: Variable Start & End Column Traversal
  * *Recurrence*: $dp[i][j] = matrix[i][j] + \min(dp[i-1][j-1], dp[i-1][j], dp[i-1][j+1])$
  * *Links*: [LeetCode 931](https://leetcode.com/problems/minimum-falling-path-sum/) | [GFG](https://www.geeksforgeeks.org/problems/maximum-path-sum-in-matrix1005/1)
  * *File*: [dp12_Max_Path_Sum_Matrix.java](03_2D_Grid_DP/dp12_Max_Path_Sum_Matrix.java)

* **DP 13: Cherry Pickup II (3D DP Made Easy)**
  * *Pattern*: Multi-Agent Grid Traversal
  * *Concept*: State $dp[i][j_1][j_2]$ tracks two robots descending simultaneously from row $i$ with 9 possible movement combinations.
  * *Links*: [LeetCode 1463](https://leetcode.com/problems/cherry-pickup-ii/) | [GFG](https://www.geeksforgeeks.org/problems/chocolates-pickup/1)
  * *File*: [dp13_Cherry_Pickup_II.java](03_2D_Grid_DP/dp13_Cherry_Pickup_II.java)

---

### 04. DP on Subsequences
* **DP 14: Subset Sum Equals to Target**
  * *Pattern*: Pick / Not-Pick Boolean Knapsack
  * *Concept*: $dp[i][t] = dp[i-1][t] \lor dp[i-1][t - arr[i]]$
  * *Links*: [GFG Subset Sum](https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1)
  * *File*: [dp14_Subset_Sum_Equal_To_K.java](04_DP_on_Subsequences/dp14_Subset_Sum_Equal_To_K.java)

* **DP 15: Partition Equal Subset Sum**
  * *Pattern*: Subset Sum with Target = $\frac{\sum arr}{2}$
  * *Concept*: Check if total array sum is even; if so, find if a subset with target $= \text{total}/2$ exists.
  * *Links*: [LeetCode 416](https://leetcode.com/problems/partition-equal-subset-sum/) | [GFG](https://www.geeksforgeeks.org/problems/subset-sum-problem2014/1)
  * *File*: [dp15_Partition_Equal_Subset_Sum.java](04_DP_on_Subsequences/dp15_Partition_Equal_Subset_Sum.java)

* **DP 16: Partition A Set Into Two Subsets With Minimum Absolute Sum Difference**
  * *Pattern*: Last Row Reachability Analysis
  * *Concept*: Compute reachable subset sums $s \le \text{total}/2$ in the final row of the subset DP table; find $\min(\text{total} - 2s)$.
  * *Links*: [LeetCode 2035](https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/) | [GFG](https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1)
  * *File*: [dp16_Partition_Min_Sum_Difference.java](04_DP_on_Subsequences/dp16_Partition_Min_Sum_Difference.java)

* **DP 17: Count Subsets with Sum K**
  * *Pattern*: Combinatorial Accumulation
  * *Recurrence*: $dp[i][s] = dp[i-1][s] + dp[i-1][s - arr[i]]$ (with zero-element frequency handling).
  * *Links*: [GFG Perfect Sum](https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1)
  * *File*: [dp17_Count_Subsets_With_Sum_K.java](04_DP_on_Subsequences/dp17_Count_Subsets_With_Sum_K.java)

* **DP 18: Count Partitions With Given Difference**
  * *Pattern*: Algebraic Reduction to Subset Sum
  * *Concept*: $S_1 - S_2 = D$ and $S_1 + S_2 = \text{total} \implies S_1 = (\text{total} + D)/2$. Count subsets with sum $S_1$.
  * *Links*: [GFG Partitions with Difference](https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1)
  * *File*: [dp18_Count_Partitions_With_Difference.java](04_DP_on_Subsequences/dp18_Count_Partitions_With_Difference.java)

* **DP 19: 0/1 Knapsack Problem**
  * *Pattern*: Classic Knapsack — Weight-Constrained Optimization
  * *Recurrence*: $dp[i][w] = \max(dp[i-1][w],\ val[i] + dp[i-1][w - wt[i]])$
  * *Links*: [GFG 0/1 Knapsack](https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1)
  * *File*: [dp19_0_1_Knapsack.java](04_DP_on_Subsequences/dp19_0_1_Knapsack.java)

* **DP 20: Minimum Coins**
  * *Pattern*: Unbounded Knapsack — Minimum Choice Optimization
  * *Recurrence*: $dp[i][t] = \min(dp[i-1][t],\ 1 + dp[i][t - coins[i]])$
  * *Links*: [LeetCode 322](https://leetcode.com/problems/coin-change/)
  * *File*: [dp20_Minimum_Coins.java](04_DP_on_Subsequences/dp20_Minimum_Coins.java)

* **DP 21: Target Sum**
  * *Pattern*: Sign Assignment Algebraic Mapping
  * *Concept*: Assigning $+$ and $-$ signs maps identically to Count Partitions with Difference: $S_1 - S_2 = \text{target}$.
  * *Links*: [LeetCode 494](https://leetcode.com/problems/target-sum/)
  * *File*: [dp21_Target_Sum.java](04_DP_on_Subsequences/dp21_Target_Sum.java)

* **DP 22: Coin Change 2**
  * *Pattern*: Unbounded Knapsack — Total Combination Count
  * *Recurrence*: $dp[i][t] = dp[i-1][t] + dp[i][t - coins[i]]$
  * *Links*: [LeetCode 518](https://leetcode.com/problems/coin-change-ii/)
  * *File*: [dp22_Coin_Change_II.java](04_DP_on_Subsequences/dp22_Coin_Change_II.java)

* **DP 23: Unbounded Knapsack**
  * *Pattern*: Infinite Item Supply Maximization
  * *Recurrence*: $dp[i][w] = \max(dp[i-1][w],\ val[i] + dp[i][w - wt[i]])$
  * *Links*: [GFG Knapsack with Duplicate Items](https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1)
  * *File*: [dp23_Unbounded_Knapsack.java](04_DP_on_Subsequences/dp23_Unbounded_Knapsack.java)

* **DP 24: Rod Cutting Problem**
  * *Pattern*: Unbounded Knapsack with Length Partitioning
  * *Concept*: Cut length $l \in [1, N]$ with price $cost[l-1]$; equivalent to unbounded knapsack where capacity $= N$.
  * *Links*: [GFG Rod Cutting](https://www.geeksforgeeks.org/problems/rod-cutting0840/1)
  * *File*: [dp24_Rod_Cutting.java](04_DP_on_Subsequences/dp24_Rod_Cutting.java)

---

### 05. DP on Strings
* **DP 25: Longest Common Subsequence (LCS)**
  * *Pattern*: 2-Sequence Alignment
  * *Recurrence*: If $s_1[i] == s_2[j]$, $1 + dp[i-1][j-1]$; else $\max(dp[i-1][j], dp[i][j-1])$.
  * *Links*: [LeetCode 1143](https://leetcode.com/problems/longest-common-subsequence/) | [GFG](https://www.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1)
  * *File*: [dp25_Longest_Common_Subsequence.java](05_DP_on_Strings/dp25_Longest_Common_Subsequence.java)

* **DP 26: Print Longest Common Subsequence**
  * *Pattern*: Backtracking on DP Table
  * *Concept*: Backtrack from $(n, m)$: if characters match, append and move diagonally; else move in the direction of the larger neighbor.
  * *Links*: [GFG Print LCS](https://www.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1)
  * *File*: [dp26_Print_LCS.java](05_DP_on_Strings/dp26_Print_LCS.java)

* **DP 27: Longest Common Substring**
  * *Pattern*: Contiguous Substring Matching with Mismatch Reset
  * *Concept*: If $s_1[i] == s_2[j]$, $dp[i][j] = 1 + dp[i-1][j-1]$; else $dp[i][j] = 0$. Answer is $\max_{i,j}(dp[i][j])$.
  * *Links*: [GFG Longest Common Substring](https://www.geeksforgeeks.org/problems/longest-common-substring1449/1)
  * *File*: [dp27_Longest_Common_Substring.java](05_DP_on_Strings/dp27_Longest_Common_Substring.java)

* **DP 28: Longest Palindromic Subsequence**
  * *Pattern*: LCS Reduction
  * *Concept*: $\text{LPS}(S) = \text{LCS}(S, \text{reverse}(S))$.
  * *Links*: [LeetCode 516](https://leetcode.com/problems/longest-palindromic-subsequence/) | [GFG](https://www.geeksforgeeks.org/problems/longest-palindromic-subsequence-1612327878/1)
  * *File*: [dp28_Longest_Palindromic_Subsequence.java](05_DP_on_Strings/dp28_Longest_Palindromic_Subsequence.java)

* **DP 29: Minimum Insertions to Make String Palindrome**
  * *Pattern*: LPS Complement
  * *Concept*: Min insertions $= |S| - \text{LPS}(S)$.
  * *Links*: [LeetCode 1312](https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/) | [GFG](https://www.geeksforgeeks.org/problems/form-a-palindrome1455/1)
  * *File*: [dp29_Min_Insertions_To_Make_Palindrome.java](05_DP_on_Strings/dp29_Min_Insertions_To_Make_Palindrome.java)

* **DP 30: Minimum Insertions/Deletions to Convert String A to String B**
  * *Pattern*: Double-LCS Complement
  * *Concept*: Answer $= (|A| - \text{LCS}) + (|B| - \text{LCS})$.
  * *Links*: [LeetCode 583](https://leetcode.com/problems/delete-operation-for-two-strings/) | [GFG](https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1)
  * *File*: [dp30_Min_Insertions_Deletions_To_Convert.java](05_DP_on_Strings/dp30_Min_Insertions_Deletions_To_Convert.java)

* **DP 31: Shortest Common Supersequence**
  * *Pattern*: LCS Backtracking with Merging
  * *Concept*: Length $= |A| + |B| - \text{LCS}$. Reconstruct by including matched characters once, and unmatched characters from their respective branches.
  * *Links*: [LeetCode 1092](https://leetcode.com/problems/shortest-common-supersequence/) | [GFG](https://www.geeksforgeeks.org/problems/shortest-common-supersequence0322/1)
  * *File*: [dp31_Shortest_Common_Supersequence.java](05_DP_on_Strings/dp31_Shortest_Common_Supersequence.java)

* **DP 32: Distinct Subsequences**
  * *Pattern*: 2D String Subsequence Matching Count
  * *Recurrence*: If $s[i-1] == t[j-1]$, $dp[i-1][j-1] + dp[i-1][j]$; else $dp[i-1][j]$.
  * *Links*: [LeetCode 115](https://leetcode.com/problems/distinct-subsequences/)
  * *File*: [dp32_Distinct_Subsequences.java](05_DP_on_Strings/dp32_Distinct_Subsequences.java)

* **DP 33: Edit Distance**
  * *Pattern*: Insert / Delete / Replace Transition Optimization
  * *Recurrence*: If chars match, $dp[i-1][j-1]$; else $1 + \min(\text{insert}, \text{delete}, \text{replace})$.
  * *Links*: [LeetCode 72](https://leetcode.com/problems/edit-distance/)
  * *File*: [dp33_Edit_Distance.java](05_DP_on_Strings/dp33_Edit_Distance.java)

* **DP 34: Wildcard Matching**
  * *Pattern*: Multi-Branch Pattern Matching with `?` and `*`
  * *Concept*: `?` matches single char; `*` matches empty sequence ($dp[i][j-1]$) or 1+ characters ($dp[i-1][j]$).
  * *Links*: [LeetCode 44](https://leetcode.com/problems/wildcard-matching/)
  * *File*: [dp34_Wildcard_Matching.java](05_DP_on_Strings/dp34_Wildcard_Matching.java)

---

### 06. DP on Stocks
* **DP 35: Best Time to Buy and Sell Stock I**
  * *Pattern*: Greedy Running Minimum
  * *Concept*: Maintain `minPrice` seen so far; track $\max(price - minPrice)$. $O(N)$ time, $O(1)$ space.
  * *Links*: [LeetCode 121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | [GFG](https://www.geeksforgeeks.org/problems/stock-buy-and-sell2615/1)
  * *File*: [dp35_Best_Time_Buy_Sell_Stock_I.java](06_DP_on_Stocks/dp35_Best_Time_Buy_Sell_Stock_I.java)

* **DP 36: Buy and Sell Stock II**
  * *Pattern*: Unlimited Transactions State Machine
  * *Concept*: State $(idx, canBuy)$: pick max between taking action (buy/sell) or skipping.
  * *Links*: [LeetCode 122](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)
  * *File*: [dp36_Best_Time_Buy_Sell_Stock_II.java](06_DP_on_Stocks/dp36_Best_Time_Buy_Sell_Stock_II.java)

* **DP 37: Buy and Sell Stocks III**
  * *Pattern*: Capped Capacity State Machine ($K = 2$)
  * *Concept*: State $(idx, canBuy, cap)$ where $cap \le 2$. Decrement $cap$ upon each completed sell.
  * *Links*: [LeetCode 123](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/)
  * *File*: [dp37_Best_Time_Buy_Sell_Stock_III.java](06_DP_on_Stocks/dp37_Best_Time_Buy_Sell_Stock_III.java)

* **DP 38: Buy and Stock Sell IV**
  * *Pattern*: At Most K Transactions State Machine
  * *Concept*: Extends DP 37 to arbitrary transaction budget $K$: state $(idx, canBuy, cap)$ with $cap \in [0, K]$.
  * *Links*: [LeetCode 188](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) | [GFG](https://www.geeksforgeeks.org/problems/maximum-profit4657/1)
  * *File*: [dp38_Best_Time_Buy_Sell_Stock_IV.java](06_DP_on_Stocks/dp38_Best_Time_Buy_Sell_Stock_IV.java)

* **DP 39: Buy and Sell Stocks With Cooldown**
  * *Pattern*: State Machine with Mandatory Rest Delay
  * *Concept*: After selling on day $i$, subsequent buy can only occur on day $i + 2$.
  * *Links*: [LeetCode 309](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)
  * *File*: [dp39_Stock_With_Cooldown.java](06_DP_on_Stocks/dp39_Stock_With_Cooldown.java)

* **DP 40: Buy and Sell Stocks With Transaction Fee**
  * *Pattern*: Transaction Cost Deduction
  * *Concept*: Deduct `fee` during either buy or sell transition to model per-trade commission.
  * *Links*: [LeetCode 714](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)
  * *File*: [dp40_Stock_With_Transaction_Fee.java](06_DP_on_Stocks/dp40_Stock_With_Transaction_Fee.java)

---

### 07. Longest Increasing Subsequence (LIS)
* **DP 41: Longest Increasing Subsequence**
  * *Pattern*: 2D State $(idx, prevIdx)$ with Index Shifting
  * *Concept*: Compare $nums[idx] > nums[prevIdx]$; either extend the subsequence or skip.
  * *Links*: [LeetCode 300](https://leetcode.com/problems/longest-increasing-subsequence/)
  * *File*: [dp41_Longest_Increasing_Subsequence.java](07_DP_on_LIS/dp41_Longest_Increasing_Subsequence.java)

* **DP 42: Printing Longest Increasing Subsequence**
  * *Pattern*: 1D DP with Predecessor Hash Array
  * *Concept*: Track $hash[i] = prev$ whenever $dp[j] + 1 > dp[i]$. Reconstruct sequence backwards from the maximum endpoint.
  * *Links*: [GFG Printing LIS](https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequence/1)
  * *File*: [dp42_Print_LIS.java](07_DP_on_LIS/dp42_Print_LIS.java)

* **DP 43: LIS Using Binary Search (Patience Sorting)**
  * *Pattern*: Greedy Replacement with Binary Search
  * *Concept*: Maintain array of smallest tails for each length. Use `lower_bound` to update/extend in $O(N \log N)$.
  * *Links*: [LeetCode 300](https://leetcode.com/problems/longest-increasing-subsequence/)
  * *File*: [dp43_LIS_Binary_Search.java](07_DP_on_LIS/dp43_LIS_Binary_Search.java)

* **DP 44: Largest Divisible Subset**
  * *Pattern*: LIS Variant with Divisibility Property
  * *Concept*: Sort array first ($a \% b == 0$ is transitive on sorted numbers). Adapt LIS condition to $nums[i] \% nums[j] == 0$.
  * *Links*: [LeetCode 368](https://leetcode.com/problems/largest-divisible-subset/)
  * *File*: [dp44_Largest_Divisible_Subset.java](07_DP_on_LIS/dp44_Largest_Divisible_Subset.java)

* **DP 45: Longest String Chain**
  * *Pattern*: LIS on String Predecessors
  * *Concept*: Sort words by length; transition if `isPredecessor(s1, s2)` holds (differ by exactly 1 character insertion).
  * *Links*: [LeetCode 1048](https://leetcode.com/problems/longest-string-chain/)
  * *File*: [dp45_Longest_String_Chain.java](07_DP_on_LIS/dp45_Longest_String_Chain.java)

* **DP 46: Longest Bitonic Subsequence**
  * *Pattern*: Bidirectional LIS + LDS
  * *Concept*: Compute LIS from left ($dp_1$) and LDS from right ($dp_2$). Answer is $\max(dp_1[i] + dp_2[i] - 1)$.
  * *Links*: [GFG Longest Bitonic Subsequence](https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0806/1)
  * *File*: [dp46_Longest_Bitonic_Subsequence.java](07_DP_on_LIS/dp46_Longest_Bitonic_Subsequence.java)

* **DP 47: Number of Longest Increasing Subsequences**
  * *Pattern*: Dual State Tracking (Length + Count)
  * *Concept*: Maintain $dp[i]$ (length) and $ct[i]$ (count). If $dp[j] + 1 == dp[i]$, accumulate $ct[i] += ct[j]$.
  * *Links*: [LeetCode 673](https://leetcode.com/problems/number-of-longest-increasing-subsequence/)
  * *File*: [dp47_Number_Of_LIS.java](07_DP_on_LIS/dp47_Number_Of_LIS.java)

---

### 08. Partition DP / Matrix Chain Multiplication (MCM)
* **DP 48: Matrix Chain Multiplication (Recursion / Memoization)**
  * *Pattern*: Interval DP with Intermediate Cut Loop
  * *Recurrence*: For cut $k \in [i, j-1]$: $cost = f(i, k) + f(k+1, j) + arr[i-1] \times arr[k] \times arr[j]$
  * *Links*: [GFG MCM](https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1)
  * *File*: [dp48_Matrix_Chain_Multiplication.java](08_Partition_DP_MCM/dp48_Matrix_Chain_Multiplication.java)

* **DP 49: MCM Tabulation (Bottom-Up)**
  * *Pattern*: Interval Length Based Iteration
  * *Concept*: Outer loop on interval length $len \in [2, N]$; inner loop on start index $i$; end index $j = i + len - 1$.
  * *Links*: [GFG MCM](https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1)
  * *File*: [dp49_MCM_Tabulation.java](08_Partition_DP_MCM/dp49_MCM_Tabulation.java)

* **DP 50: Minimum Cost to Cut the Stick**
  * *Pattern*: Sorted Interval Boundary Partitioning
  * *Concept*: Add endpoints $0$ and $L$ to cuts; sort. For range $[i, j]$, cut at $k \in [i+1, j-1]$ with cost $(cuts[j] - cuts[i])$.
  * *Links*: [LeetCode 1547](https://leetcode.com/problems/minimum-cost-to-cut-a-stick/)
  * *File*: [dp50_Min_Cost_Cut_Stick.java](08_Partition_DP_MCM/dp50_Min_Cost_Cut_Stick.java)

* **DP 51: Burst Balloons**
  * *Pattern*: Last-Element Partitioning
  * *Concept*: Choosing the *last* balloon $k$ to burst decouples range $[i, j]$ into independent subproblems $[i, k-1]$ and $[k+1, j]$.
  * *Links*: [LeetCode 312](https://leetcode.com/problems/burst-balloons/)
  * *File*: [dp51_Burst_Balloons.java](08_Partition_DP_MCM/dp51_Burst_Balloons.java)

* **DP 52: Evaluate Boolean Expression to True (Boolean Parenthesization)**
  * *Pattern*: Multi-State Interval DP (True / False Counts)
  * *Concept*: Partition at each operator (`&`, `|`, `^`); combine left and right boolean outcome counts.
  * *Links*: [GFG Boolean Parenthesization](https://www.geeksforgeeks.org/problems/boolean-parenthesization5610/1)
  * *File*: [dp52_Boolean_Parenthesization.java](08_Partition_DP_MCM/dp52_Boolean_Parenthesization.java)

* **DP 53: Palindrome Partitioning II**
  * *Pattern*: Front Partitioning for Minimum Cuts
  * *Concept*: $dp[i] = 1 + \min_{j}(dp[j+1])$ for all $j \ge i$ where $s[i..j]$ is a palindrome.
  * *Links*: [LeetCode 132](https://leetcode.com/problems/palindrome-partitioning-ii/) | [GFG](https://www.geeksforgeeks.org/problems/palindromic-patitioning4845/1)
  * *File*: [dp53_Palindrome_Partitioning_II.java](08_Partition_DP_MCM/dp53_Palindrome_Partitioning_II.java)

* **DP 54: Partition Array for Maximum Sum**
  * *Pattern*: Front Partitioning with Sliding Window ($K$)
  * *Concept*: $dp[i] = \max_{len=1..K}(len \times \max(nums[i..i+len-1]) + dp[i+len])$.
  * *Links*: [LeetCode 1043](https://leetcode.com/problems/partition-array-for-maximum-sum/)
  * *File*: [dp54_Partition_Array_For_Max_Sum.java](08_Partition_DP_MCM/dp54_Partition_Array_For_Max_Sum.java)

---

### 09. DP on Rectangles & Squares
* **DP 55: Maximum Rectangle Area with all 1's**
  * *Pattern*: Histogram Stack Transformation
  * *Concept*: Convert each row into a cumulative height histogram of consecutive 1's. Compute largest rectangle in histogram using a monotonic stack in $O(M)$ per row ($O(N \times M)$ overall).
  * *Links*: [LeetCode 85](https://leetcode.com/problems/maximal-rectangle/) | [GFG](https://www.geeksforgeeks.org/problems/max-rectangle/1)
  * *File*: [dp55_Maximum_Rectangle_Area_With_All_1s.java](09_DP_on_Squares/dp55_Maximum_Rectangle_Area_With_All_1s.java)

* **DP 56: Count Square Submatrices with All Ones**
  * *Pattern*: 2D DP on Squares
  * *Concept*: $dp[i][j]$ is the size of the largest square submatrix with bottom-right corner at $(i, j)$. If $matrix[i][j] == 1$, $dp[i][j] = 1 + \min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])$. Sum of all $dp[i][j]$ gives the total count.
  * *Links*: [LeetCode 1277](https://leetcode.com/problems/count-square-submatrices-with-all-ones/) | [GFG](https://www.geeksforgeeks.org/problems/count-square-submatrices-with-all-ones/1)
  * *File*: [dp56_Count_Square_Submatrices_With_All_Ones.java](09_DP_on_Squares/dp56_Count_Square_Submatrices_With_All_Ones.java)

---

## 🗂️ Quick Section Reference

| Section | Files | Problems Covered | Key Paradigms |
|---|---|---|---|
| [01_Introduction](01_Introduction/) | `dp1` | DP 1 | Memoization, Tabulation, Space Opt |
| [02_1D_DP](02_1D_DP/) | `dp2` – `dp6` | DP 2 – DP 6 | 1D Recursion, Min/Max Cost, Circular Trick |
| [03_2D_Grid_DP](03_2D_Grid_DP/) | `dp7` – `dp13` | DP 7 – DP 13 | 2D/3D Grid Paths, Multi-Agent Traversal |
| [04_DP_on_Subsequences](04_DP_on_Subsequences/) | `dp14` – `dp24` | DP 14 – DP 24 | Knapsack (0/1 & Unbounded), Coin Change, Subsets |
| [05_DP_on_Strings](05_DP_on_Strings/) | `dp25` – `dp34` | DP 25 – DP 34 | LCS, LPS, Edit Distance, Wildcards |
| [06_DP_on_Stocks](06_DP_on_Stocks/) | `dp35` – `dp40` | DP 35 – DP 40 | Finite State Machine DP (Cooldown, Fee, Capacity) |
| [07_DP_on_LIS](07_DP_on_LIS/) | `dp41` – `dp47` | DP 41 – DP 47 | LIS Variations, Patience Sorting ($O(N \log N)$), Bitonic |
| [08_Partition_DP_MCM](08_Partition_DP_MCM/) | `dp48` – `dp54` | DP 48 – DP 54 | Interval Cuts, Last-Element Trick, Front Partitioning |
| [09_DP_on_Squares](09_DP_on_Squares/) | `dp55` – `dp56` | DP 55 – DP 56 | Histogram Monotonic Stack, Square Submatrix DP |
