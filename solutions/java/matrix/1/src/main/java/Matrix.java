class Matrix {

    private final int[][] matrix;

    Matrix(String matrixAsString) {
        String[] rows = matrixAsString.split("\n");
        matrix = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] nums = rows[i].trim().split(" +");
            matrix[i] = new int[nums.length];

            for (int j = 0; j < nums.length; j++) {
                matrix[i][j] = Integer.parseInt(nums[j]);
            }
        }
    }

    int[] getRow(int rowNumber) {
        return matrix[rowNumber - 1].clone();
    }

    int[] getColumn(int columnNumber) {
        int[] column = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][columnNumber - 1];
        }
        return column;
    }
}

