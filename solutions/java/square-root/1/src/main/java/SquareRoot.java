public class SquareRoot {

    public int squareRoot(int radicand) {
        if (radicand < 0) {
            throw new IllegalArgumentException("Radicand must be non-negative.");
        }
        if (radicand == 0 || radicand == 1) return radicand;
        int x = radicand;
        int y = (x + radicand / x) / 2;
        while (y < x) {
            x = y;
            y = (x + radicand / x) / 2;
        }
        return x;
    }
}
