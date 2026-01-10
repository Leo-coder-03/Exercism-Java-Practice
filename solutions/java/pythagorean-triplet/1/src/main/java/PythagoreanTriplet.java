import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class PythagoreanTriplet {
    private final int a;
    private final int b;
    private final int c;

    public PythagoreanTriplet(int a, int b, int c) {
        if (a >= b || b >= c) {
            throw new IllegalArgumentException("Triplet must satisfy a < b < c");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() { return a; }
    public int getB() { return b; }
    public int getC() { return c; }

    static TripletListBuilder makeTripletsList() {
        return new TripletListBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PythagoreanTriplet)) return false;
        PythagoreanTriplet that = (PythagoreanTriplet) o;
        return a == that.a && b == that.b && c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "{" + a + ", " + b + ", " + c + "}";
    }

    static class TripletListBuilder {
        private int sum = -1;           
        private int maxFactor = Integer.MAX_VALUE; 
        TripletListBuilder thatSumTo(int sum) {
            this.sum = sum;
            return this;
        }

        TripletListBuilder withFactorsLessThanOrEqualTo(int maxFactor) {
            this.maxFactor = maxFactor;
            return this;
        }

        List<PythagoreanTriplet> build() {
            List<PythagoreanTriplet> result = new ArrayList<>();

            int limitA = Math.min(sum / 3, maxFactor);
            for (int a = 1; a <= limitA; a++) {
                int limitB = Math.min((sum - a) / 2, maxFactor);
                for (int b = a + 1; b <= limitB; b++) {
                    int c = sum - a - b;
                    if (c <= b || c > maxFactor) continue;
                    if (a * a + b * b == c * c) {
                        result.add(new PythagoreanTriplet(a, b, c));
                    }
                }
            }

            return result;
        }
    }
}
