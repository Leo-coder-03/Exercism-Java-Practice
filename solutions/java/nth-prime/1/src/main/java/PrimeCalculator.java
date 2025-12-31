class PrimeCalculator {

    int nth(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        int count = 0;
        int number = 1;

        while (count < n) {
            number++;
            if (isPrime(number)) {
                count++;
            }
        }

        return number;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0) return false;

        int limit = (int) Math.sqrt(num);
        for (int i = 3; i <= limit; i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }
}

