import java.util.List;
import java.util.ArrayList;

class FlowerFieldBoard {
    private final char[][] board;
    private final int rows;
    private final int cols;

    FlowerFieldBoard(List<String> boardRows) {
        rows = boardRows.size();
        cols = boardRows.isEmpty() ? 0 : boardRows.get(0).length();
        board = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            board[r] = boardRows.get(r).toCharArray();
        }
    }
    List<String> withNumbers() {
        char[][] result = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == '*') {
                    result[r][c] = '*';
                } else {
                    int count = countAdjacentFlowers(r, c);
                    result[r][c] = count > 0 ? (char) ('0' + count) : ' ';
                }
            }
        }
        List<String> numberedBoard = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            numberedBoard.add(new String(result[r]));
        }
        return numberedBoard;
    }

    private int countAdjacentFlowers(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int nr = row + dr;
                int nc = col + dc;
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == '*') {
                    count++;
                }
            }
        }
        return count;
    }
}
