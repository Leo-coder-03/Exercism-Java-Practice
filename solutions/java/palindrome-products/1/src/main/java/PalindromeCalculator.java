import java.util.*;

class PalindromeCalculator {

    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {

        if (minFactor > maxFactor) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }

        SortedMap<Long, List<List<Integer>>> result = new TreeMap<>();

        for (int a = minFactor; a <= maxFactor; a++) {
            for (int b = a; b <= maxFactor; b++) {  
                long product = (long) a * b;

                if (isPalindrome(product)) {
                    result.computeIfAbsent(product, k -> new ArrayList<>())
                          .add(List.of(a, b));
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(long number) {
        String s = String.valueOf(number);
        return new StringBuilder(s).reverse().toString().equals(s);
    }
}
