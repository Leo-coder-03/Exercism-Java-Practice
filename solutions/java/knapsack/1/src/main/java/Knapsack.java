import java.util.List;

class Knapsack {

    int maximumValue(int maximumWeight, List<Item> items) {
        int[] dp = new int[maximumWeight + 1];

        for (Item item : items) {
            int weight = item.weight;
            int value = item.value;
            for (int w = maximumWeight; w >= weight; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight] + value);
            }
        }

        return dp[maximumWeight];
    }
}
