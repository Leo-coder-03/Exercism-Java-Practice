import java.util.*;

public class MazeGenerator {

    private static final char PATH = ' ';
    private static final char TEMP_WALL = '#';

    public char[][] generatePerfectMaze(int rows, int columns) {
        return generatePerfectMaze(rows, columns, new Random().nextInt());
    }

    public char[][] generatePerfectMaze(int rows, int columns, int seed) {

        validate(rows, columns);

        int H = rows * 2 + 1;
        int W = columns * 2 + 1;

        char[][] maze = new char[H][W];
        for (char[] r : maze) Arrays.fill(r, TEMP_WALL);

        boolean[][] visited = new boolean[rows][columns];
        Random rnd = new Random(seed);

        carve(0, 0, visited, maze, rnd, rows, columns);

        convertToBoxDrawing(maze);

        addEntranceExit(maze);

        return maze;
    }

    private void validate(int r, int c) {
        if (r < 5 || r > 100 || c < 5 || c > 100)
            throw new IllegalArgumentException("Maze dimensions must be 5–100");
    }

    private void carve(int r, int c, boolean[][] visited, char[][] maze,
                       Random rnd, int rows, int cols) {

        visited[r][c] = true;
        maze[r * 2 + 1][c * 2 + 1] = PATH;

        List<int[]> dirs = new ArrayList<>(List.of(
                new int[]{1, 0},  
                new int[]{-1, 0}, 
                new int[]{0, 1},  
                new int[]{0, -1}  
        ));

        Collections.shuffle(dirs, rnd);

        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
            if (visited[nr][nc]) continue;

            maze[r * 2 + 1 + d[0]][c * 2 + 1 + d[1]] = PATH;
            carve(nr, nc, visited, maze, rnd, rows, cols);
        }
    }

    private void convertToBoxDrawing(char[][] m) {
        int H = m.length, W = m[0].length;

        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (m[r][c] == PATH) continue;

                boolean U = r > 0     && m[r - 1][c] != PATH;
                boolean D = r < H - 1 && m[r + 1][c] != PATH;
                boolean L = c > 0     && m[r][c - 1] != PATH;
                boolean R = c < W - 1 && m[r][c + 1] != PATH;

                m[r][c] = pickBoxChar(U, D, L, R);
            }
        }
    }

    private char pickBoxChar(boolean U, boolean D, boolean L, boolean R) {
        int n = (U?1:0)+(D?1:0)+(L?1:0)+(R?1:0);

        return switch (n) {
            case 4 -> '┼';
            case 3 -> {
                if (!U) yield '┬';
                if (!D) yield '┴';
                if (!L) yield '├';
                yield '┤';
            }
            case 2 -> {
                if (U && D) yield '│';
                if (L && R) yield '─';
                if (D && R) yield '┌';
                if (D && L) yield '┐';
                if (U && R) yield '└';
                yield '┘'; // U+L
            }
            case 1 -> {
                if (U || D) yield '│';
                yield '─';
            }
            default -> ' ';
        };
    }

    private void addEntranceExit(char[][] m) {
        for (int r = 1; r < m.length - 1; r++) {
            if (m[r][1] == PATH) {
                m[r][0] = '⇨';
                break;
            }
        }
        int last = m[0].length - 1;
        for (int r = 1; r < m.length - 1; r++) {
            if (m[r][last - 1] == PATH) {
                m[r][last] = '⇨';
                break;
            }
        }
    }
}
