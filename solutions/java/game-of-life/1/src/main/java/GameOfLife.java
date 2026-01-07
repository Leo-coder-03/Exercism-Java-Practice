class GameOfLife {

    public int[][] tick(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[][] {};
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        if (cols == 0) {
            return new int[rows][0];
        }

        int[][] next = new int[rows][cols];
        int[] directions = {-1, 0, 1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int liveNeighbors = 0;

                for (int dr : directions) {
                    for (int dc : directions) {
                        if (dr == 0 && dc == 0) continue;

                        int nr = r + dr;
                        int nc = c + dc;

                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                            if (matrix[nr][nc] == 1) {
                                liveNeighbors++;
                            }
                        }
                    }
                }

                if (matrix[r][c] == 1) {
                    next[r][c] = (liveNeighbors == 2 || liveNeighbors == 3) ? 1 : 0;
                } else {
                    next[r][c] = (liveNeighbors == 3) ? 1 : 0;
                }
            }
        }
        return next;
    }
}
