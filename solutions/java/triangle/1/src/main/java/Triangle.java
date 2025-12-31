class Triangle {

    private final double a;
    private final double b;
    private final double c;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        this.a = side1;
        this.b = side2;
        this.c = side3;

        validate();
    }

    private void validate() throws TriangleException {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new TriangleException();
        }

        if (a + b <= c || b + c <= a || a + c <= b) {
            throw new TriangleException();
        }
    }

    boolean isEquilateral() {
        return a == b && b == c;
    }

    boolean isIsosceles() {
        return a == b || b == c || a == c;
    }

    boolean isScalene() {
        return a != b && b != c && a != c;
    }
}
