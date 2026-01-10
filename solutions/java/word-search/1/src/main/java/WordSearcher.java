import java.util.*;

class WordSearcher {

    private static final int[][] DIRS = {
        { 1,  0},  // right
        {-1,  0},  // left
        { 0,  1},  // down
        { 0, -1},  // up
        { 1,  1},  // down-right
        {-1, -1},  // up-left
        { 1, -1},  // up-right
        {-1,  1}   // down-left
    };

    Map<String, Optional<WordLocation>> search(Set<String> words, char[][] grid) {
        Map<String, Optional<WordLocation>> result = new HashMap<>();

        for (String w : words) {
            result.put(w, findWord(w, grid));
        }

        return result;
    }

    private Optional<WordLocation> findWord(String word, char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int len  = word.length();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] != word.charAt(0)) continue;

                for (int[] d : DIRS) {
                    int dx = d[0], dy = d[1];

                    if (matches(word, grid, c, r, dx, dy)) {

                        int endX = c + dx * (len - 1);
                        int endY = r + dy * (len - 1);

                        Pair start = new Pair(c + 1, r + 1);
                        Pair end   = new Pair(endX + 1, endY + 1);

                        return Optional.of(new WordLocation(start, end));
                    }
                }
            }
        }

        return Optional.empty();
    }

    private boolean matches(String word, char[][] grid, int c, int r, int dx, int dy) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < word.length(); i++) {
            int x = c + dx * i;
            int y = r + dy * i;

            if (x < 0 || x >= cols || y < 0 || y >= rows) return false;
            if (grid[y][x] != word.charAt(i)) return false;
        }
        return true;
    }
}
