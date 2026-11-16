public class dp4_Frog_Jump_K_Distances {
    public static boolean canCross(int[] stones) {
        if (stones.length < 2 || stones[1] != 1) {
            return false;
        }

        return solve(1, 1, stones);
    }

    private static boolean solve(int idx, int k, int[] stones) {
        if (idx == stones.length - 1) {
            return true;
        }
        for (int i = idx + 1; i < stones.length; i++) {
            int jump = stones[i] - stones[idx];
            if (jump >= k - 1 && jump <= k + 1) {
                if (solve(i, jump, stones)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] stones = { 0, 1, 3, 5, 6, 8, 12, 17 };
        System.out.println(canCross(stones));
    }
}