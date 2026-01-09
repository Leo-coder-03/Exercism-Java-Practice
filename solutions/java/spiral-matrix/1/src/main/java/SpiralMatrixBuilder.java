class SpiralMatrixBuilder {

    int[][] buildMatrixOfSize(int size) {
        int[][] matrix = new int[size][size];
        int value = 1;

        int top = 0, bottom = size - 1;
        int left = 0, right = size - 1;

        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) {
                matrix[top][col] = value++;
            }
            top++;
            for (int row = top; row <= bottom; row++) {
                matrix[row][right] = value++;
            }
            right--;
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    matrix[bottom][col] = value++;
                }
                bottom--;
            }
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    matrix[row][left] = value++;
                }
                left++;
            }
        }

        return matrix;
    }
}
