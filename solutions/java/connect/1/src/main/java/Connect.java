import java.util.*;

class Connect {

    private final char[][] grid;
    private final int rows;
    private final int cols;

    Connect(String[] board) {
        rows = board.length;
        cols = board[rows - 1].trim().split(" ").length;
        grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            var parts = board[r].trim().split(" ");
            for (int c = 0; c < cols; c++) grid[r][c] = parts[c].charAt(0);
        }
    }

    public Winner computeWinner() {
        if (hasPath('X')) return Winner.PLAYER_X;
        if (hasPath('O')) return Winner.PLAYER_O;
        return Winner.NONE;
    }

    private boolean hasPath(char player) {
        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> stack = new ArrayDeque<>();

        if (player == 'X') {
            for (int r = 0; r < rows; r++) {
                if (grid[r][0] == 'X') stack.push(new int[]{r, 0});
            }
        } else {
            for (int c = 0; c < cols; c++) {
                if (grid[0][c] == 'O') stack.push(new int[]{0, c});
            }
        }

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int r = cur[0], c = cur[1];
            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (player == 'X' && c == cols - 1) return true;
            if (player == 'O' && r == rows - 1) return true;

            for (int[] n : neighbors(r, c)) {
                int nr = n[0], nc = n[1];
                if (!visited[nr][nc] && grid[nr][nc] == player) {
                    stack.push(new int[]{nr, nc});
                }
            }
        }
        return false;
    }

    private List<int[]> neighbors(int r, int c) {
        List<int[]> list = new ArrayList<>(6);
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}};
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                list.add(new int[]{nr, nc});
            }
        }
        return list;
    }
}
