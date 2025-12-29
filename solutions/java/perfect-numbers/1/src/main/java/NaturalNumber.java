public class NaturalNumber {

    private final int number;

    public NaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.number = number;
    }

    public Classification getClassification() {
        int aliquotSum = calculateAliquotSum(number);

        if (aliquotSum == number) {
            return Classification.PERFECT;
        } else if (aliquotSum > number) {
            return Classification.ABUNDANT;
        } else {
            return Classification.DEFICIENT;
        }
    }

    private int calculateAliquotSum(int n) {
        int sum = 0;
        if(n==0 || n==1)
            return 0;

        // Only check factors up to sqrt(n)
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i; // add the divisor

                int pair = n / i;
                // Add the paired factor if it's not the number itself and not the same as i
                if (pair != n && pair != i) {
                    sum += pair;
                }
            }
        }
        return sum;
    }
}

