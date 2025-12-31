import java.util.HashSet;
import java.util.Set;

class SumOfMultiples {

    private final int limit;
    private final int[] bases;

    SumOfMultiples(int number, int[] set) {
        this.limit = number;
        this.bases = set;
    }

    int getSum() {
        Set<Integer> multiples = new HashSet<>();

        for (int base : bases) {
            if (base <= 0) continue;  

            for (int n = base; n < limit; n += base) {
                multiples.add(n);
            }
        }

        int sum = 0;
        for (int m : multiples) {
            sum += m;
        }

        return sum;
    }
}

