import java.util.ArrayList;
import java.util.List;

class PrimeFactorsCalculator {
    List<Long> calculatePrimeFactorsOf(long number) {
        List<Long> factors = new ArrayList<>();
        long n = number;

        for (long divisor = 2; divisor <= n / divisor; divisor++) {
            while (n % divisor == 0) {
                factors.add(divisor);
                n /= divisor;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }
}
