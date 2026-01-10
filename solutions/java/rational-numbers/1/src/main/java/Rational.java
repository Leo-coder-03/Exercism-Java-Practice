import java.util.Objects;

class Rational {

    private final int numerator;
    private final int denominator;

    Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        int g = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / g;
        this.denominator = denominator / g;
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }

    Rational add(Rational other) {
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Rational(num, den);
    }

    Rational subtract(Rational other) {
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Rational(num, den);
    }

    Rational multiply(Rational other) {
        int num = this.numerator * other.numerator;
        int den = this.denominator * other.denominator;
        return new Rational(num, den);
    }

    Rational divide(Rational other) {
        if (other.numerator == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new Rational(num, den);
    }

    Rational abs() {
        return new Rational(Math.abs(numerator), Math.abs(denominator));
    }

    Rational pow(int power) {
        if (power == 0) {
            return new Rational(1, 1);
        }

        if (power > 0) {
            return new Rational((int) Math.pow(numerator, power), (int) Math.pow(denominator, power));
        } else { 
            int p = -power;
            return new Rational((int) Math.pow(denominator, p), (int) Math.pow(numerator, p));
        }
    }

    double exp(double x) {
        return Math.pow(x, (double) numerator / denominator);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rational other) {
            return this.numerator == other.numerator &&
                   this.denominator == other.denominator;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
