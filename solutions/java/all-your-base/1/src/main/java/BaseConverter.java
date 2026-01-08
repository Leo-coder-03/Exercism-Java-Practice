import java.util.ArrayList;
import java.util.List;

class BaseConverter {

    private final int originalBase;
    private final int[] originalDigits;
    private final int decimalValue;

    BaseConverter(int originalBase, int[] originalDigits) {

        if (originalBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }
        if (originalDigits.length == 0) {
            this.originalBase = originalBase;
            this.originalDigits = new int[]{0};
            this.decimalValue = 0;
            return;
        }
        for (int d : originalDigits) {
            if (d < 0) {
                throw new IllegalArgumentException("Digits may not be negative.");
            }
            if (d >= originalBase) {
                throw new IllegalArgumentException("All digits must be strictly less than the base.");
            }
        }

        this.originalBase = originalBase;
        this.originalDigits = originalDigits;
        this.decimalValue = toDecimal(originalBase, originalDigits);
    }

    int[] convertToBase(int newBase) {
        if (newBase < 2) {
            throw new IllegalArgumentException("Bases must be at least 2.");
        }

        if (decimalValue == 0) {
            return new int[]{0};
        }

        int number = decimalValue;
        List<Integer> digits = new ArrayList<>();

        while (number > 0) {
            digits.add(0, number % newBase);
            number /= newBase;
        }

        return digits.stream().mapToInt(i -> i).toArray();
    }

    private int toDecimal(int base, int[] digits) {
        int value = 0;
        for (int d : digits) {
            value = value * base + d;
        }
        return value;
    }
}
