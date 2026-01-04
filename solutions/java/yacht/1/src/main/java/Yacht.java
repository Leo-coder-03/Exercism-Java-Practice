import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

class Yacht {

    private final int[] dice;
    private final YachtCategory category;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.dice = dice;
        this.category = yachtCategory;
    }

    int score() {
        return switch (category) {
            case YACHT -> scoreYacht();
            case ONES -> scoreSingles(1);
            case TWOS -> scoreSingles(2);
            case THREES -> scoreSingles(3);
            case FOURS -> scoreSingles(4);
            case FIVES -> scoreSingles(5);
            case SIXES -> scoreSingles(6);
            case FULL_HOUSE -> scoreFullHouse();
            case FOUR_OF_A_KIND -> scoreFourOfAKind();
            case LITTLE_STRAIGHT -> scoreLittleStraight();
            case BIG_STRAIGHT -> scoreBigStraight();
            case CHOICE -> scoreChoice();
        };
    }

    // --- Helper methods ---

    private int scoreYacht() {
        return dice[0]==dice[1] && dice[1]==dice[2] && dice[2]==dice[3] && dice[3]==dice[4] ? 50 : 0;
    }

    private int scoreSingles(int n) {
        int sum = 0;
        for (int d : dice) if (d == n) sum += d;
        return sum;
    }

    private int scoreFullHouse() {
        Map<Integer, Integer> freq = countFreq();
        if (freq.size() != 2) return 0;
        Collection<Integer> counts = freq.values();
        if (counts.contains(3) && counts.contains(2)) {
            int sum = 0;
            for (int d : dice) sum += d;
            return sum;
        }
        return 0;
    }

    private int scoreFourOfAKind() {
        Map<Integer, Integer> freq = countFreq();
        for (int key : freq.keySet()) {
            if (freq.get(key) >= 4) return key * 4;
        }
        return 0;
    }

    private int scoreLittleStraight() {
        int[] copy = dice.clone();
        Arrays.sort(copy);
        return Arrays.equals(copy, new int[]{1,2,3,4,5}) ? 30 : 0;
    }

    private int scoreBigStraight() {
        int[] copy = dice.clone();
        Arrays.sort(copy);
        return Arrays.equals(copy, new int[]{2,3,4,5,6}) ? 30 : 0;
    }

    private int scoreChoice() {
        int sum = 0;
        for (int d : dice) sum += d;
        return sum;
    }

    private Map<Integer, Integer> countFreq() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int d : dice) map.put(d, map.getOrDefault(d, 0) + 1);
        return map;
    }
}
