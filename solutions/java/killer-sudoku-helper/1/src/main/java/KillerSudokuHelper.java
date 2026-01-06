import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KillerSudokuHelper {

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, cageSize, cageSum, new ArrayList<>(), result, exclude);
        return result;
    }

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
        return combinationsInCage(cageSum, cageSize, new ArrayList<>());
    }

    // Backtracking generator
    private void backtrack(int startDigit,
                           int digitsLeft,
                           int sumRemaining,
                           List<Integer> current,
                           List<List<Integer>> result,
                           List<Integer> exclude) {

        // If no digits left, sum must be exactly zero
        if (digitsLeft == 0) {
            if (sumRemaining == 0) {
                List<Integer> sorted = new ArrayList<>(current);
                Collections.sort(sorted);
                result.add(sorted);
            }
            return;
        }

        // Prune impossible sums early
        if (sumRemaining < 0) return;

        for (int digit = startDigit; digit <= 9; digit++) {

            // Respect exclude constraints
            if (exclude.contains(digit)) continue;

            // Add digit
            current.add(digit);

            backtrack(digit + 1,                // no repeats → next digit must be larger
                      digitsLeft - 1,
                      sumRemaining - digit,
                      current,
                      result,
                      exclude);

            // Undo choice
            current.remove(current.size() - 1);
        }
    }
}

