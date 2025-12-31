import java.util.ArrayList;
import java.util.List;

class Sieve {

    private final int maxPrime;
    private final boolean[] isComposite; 

    Sieve(int maxPrime) {
        this.maxPrime = maxPrime;
        this.isComposite = new boolean[maxPrime + 1]; 
        runSieve();
    }

    private void runSieve() {
        if (maxPrime < 2) return;

        int limit = (int) Math.sqrt(maxPrime);

        for (int num = 2; num <= limit; num++) {
            if (!isComposite[num]) {  
                for (int multiple = num * num; multiple <= maxPrime; multiple += num) {
                    isComposite[multiple] = true;
                }
            }
        }
    }

    List<Integer> getPrimes() {
        List<Integer> primes = new ArrayList<>();

        for (int num = 2; num <= maxPrime; num++) {
            if (!isComposite[num]) {
                primes.add(num);
            }
        }

        return primes;
    }
}

