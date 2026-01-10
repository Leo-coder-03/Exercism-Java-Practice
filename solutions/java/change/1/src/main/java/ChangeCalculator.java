import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ChangeCalculator {

    private final List<Integer> coins;

    ChangeCalculator(List<Integer> currencyCoins) {
        this.coins = new ArrayList<>(currencyCoins);
        Collections.sort(this.coins);
    }

    List<Integer> computeMostEfficientChange(int total) {
        if (total < 0) {
            throw new IllegalArgumentException("Negative totals are not allowed.");
        }
        if (total == 0) {
            return new ArrayList<>();
        }

        int max = total + 1;
        List<Integer>[] dp = new List[total + 1];
        dp[0] = new ArrayList<>();

        for (int t = 1; t <= total; t++) {
            List<Integer> best = null;
            for (int coin : coins) {
                if (coin > t) break;
                if (dp[t - coin] != null) {
                    List<Integer> candidate = new ArrayList<>(dp[t - coin]);
                    candidate.add(coin);
                    if (best == null || candidate.size() < best.size()) {
                        best = candidate;
                    }
                }
            }
            dp[t] = best;
        }

        if (dp[total] == null) {
            throw new IllegalArgumentException(
                    "The total " + total + " cannot be represented in the given currency.");
        }

        Collections.sort(dp[total]);
        return dp[total];
    }
}
