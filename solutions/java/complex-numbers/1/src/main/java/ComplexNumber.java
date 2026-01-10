class ComplexNumber {

    private final double real;
    private final double imaginary;

    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    double abs() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(real + other.real, imaginary + other.imaginary);
    }

    ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(real - other.real, imaginary - other.imaginary);
    }

    ComplexNumber multiply(ComplexNumber other) {
        double r = real * other.real - imaginary * other.imaginary;
        double i = real * other.imaginary + imaginary * other.real;
        return new ComplexNumber(r, i);
    }

    ComplexNumber divide(ComplexNumber other) {
        double denom = other.real * other.real + other.imaginary * other.imaginary;
        double r = (real * other.real + imaginary * other.imaginary) / denom;
        double i = (imaginary * other.real - real * other.imaginary) / denom;
        return new ComplexNumber(r, i);
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(real, -imaginary);
    }

    ComplexNumber exponentialOf() {
        double ea = Math.exp(real);
        double r = ea * Math.cos(imaginary);
        double i = ea * Math.sin(imaginary);
        return new ComplexNumber(r, i);
    }
}
