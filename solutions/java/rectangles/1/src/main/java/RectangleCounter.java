class RectangleCounter {

    int countRectangles(String[] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length();
        int count = 0;

        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                if (grid[r1].charAt(c1) != '+') continue;

                for (int c2 = c1 + 1; c2 < cols; c2++) {
                    if (grid[r1].charAt(c2) != '+') continue;

                    for (int r2 = r1 + 1; r2 < rows; r2++) {
                        if (grid[r2].charAt(c1) != '+' || grid[r2].charAt(c2) != '+') continue;

                        if (!isVerticalEdgeValid(grid, c1, r1, r2)) continue;
                        if (!isVerticalEdgeValid(grid, c2, r1, r2)) continue;

                        if (!isHorizontalEdgeValid(grid[r1], c1, c2)) continue;
                        if (!isHorizontalEdgeValid(grid[r2], c1, c2)) continue;

                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean isVerticalEdgeValid(String[] grid, int col, int rowStart, int rowEnd) {
        for (int r = rowStart + 1; r < rowEnd; r++) {
            char ch = grid[r].charAt(col);
            if (ch != '|' && ch != '+') return false;
        }
        return true;
    }

    private boolean isHorizontalEdgeValid(String row, int colStart, int colEnd) {
        for (int c = colStart + 1; c < colEnd; c++) {
            char ch = row.charAt(c);
            if (ch != '-' && ch != '+') return false;
        }
        return true;
    }
}
