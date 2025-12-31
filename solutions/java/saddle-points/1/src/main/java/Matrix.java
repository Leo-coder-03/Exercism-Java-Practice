import java.util.*;

class Matrix {

    private final List<List<Integer>> values;
    private final int rows;
    private final int cols;

    Matrix(List<List<Integer>> values) {
        this.values = values;
        this.rows = values.size();
        this.cols = rows == 0 ? 0 : values.get(0).size();
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> result = new HashSet<>();

        if (rows == 0 || cols == 0) return result;

        int[] rowMax = new int[rows];
        for (int r = 0; r < rows; r++) {
            rowMax[r] = Collections.max(values.get(r));
        }

        int[] colMin = new int[cols];
        Arrays.fill(colMin, Integer.MAX_VALUE);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                colMin[c] = Math.min(colMin[c], values.get(r).get(c));
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int val = values.get(r).get(c);
                if (val == rowMax[r] && val == colMin[c]) {
                    result.add(new MatrixCoordinate(r + 1, c + 1));
                }
            }
        }

        return result;
    }
}
