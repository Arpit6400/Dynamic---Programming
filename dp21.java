public class dp21 {
    
    public static void main(String[] args) {
    }

    // Recursion - Target Sum
    public int targetSum(int idx, int target, int[] arr){
        if(idx == 0){
            if(target == 0 && arr[0] == 0) return 2;
            if(target == 0 || target == arr[0]) return 1;
            return 0;
        }
        int take = targetSum(idx - 1, target - arr[idx], arr);
        int neg_take = targetSum(idx - 1, target + arr[idx], arr);
        return take + neg_take;
    }

    // Memoization - Agent Add

    // Tabulation
    public int findTargetSumWays(int [] nums, int target) {
        // Problem is same as count of subsets with given difference
        return 0;
    }


}
