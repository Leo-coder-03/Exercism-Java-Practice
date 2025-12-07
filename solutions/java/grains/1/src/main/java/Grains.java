import java.math.BigInteger;

class Grains {

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64)
            throw new IllegalArgumentException("square must be between 1 and 64");

        return BigInteger.TWO.pow(square - 1);
    }

    BigInteger grainsOnBoard() {
        BigInteger total = BigInteger.ZERO;

        for (int i = 1; i <= 64; i++) {
            total = total.add(grainsOnSquare(i));
        }

        return total;
    }
}
