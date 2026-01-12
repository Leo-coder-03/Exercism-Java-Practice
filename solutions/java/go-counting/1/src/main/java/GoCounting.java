import java.awt.Point;
import java.util.*;

class GoCounting {

    private final char[][] board;
    private final int rows, cols;

    GoCounting(String boardString) {
        String[] lines = boardString.split("\n");
        this.rows = lines.length;
        this.cols = lines[0].length();
        this.board = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            board[r] = lines[r].toCharArray();
        }
    }

    private void validate(int x, int y) {
        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
    }

    Player getTerritoryOwner(int x, int y) {
        validate(x, y);
        if (board[y][x] != ' ') return Player.NONE;

        TerritoryInfo info = exploreTerritory(x, y);
        return info.owner;
    }

    Set<Point> getTerritory(int x, int y) {
        validate(x, y);
        if (board[y][x] != ' ') return new HashSet<>();

        return exploreTerritory(x, y).territory;
    }

    Map<Player, Set<Point>> getTerritories() {
        Map<Player, Set<Point>> result = new HashMap<>();
        result.put(Player.BLACK, new HashSet<>());
        result.put(Player.WHITE, new HashSet<>());
        result.put(Player.NONE, new HashSet<>());

        boolean[][] visited = new boolean[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (board[y][x] == ' ' && !visited[y][x]) {
                    TerritoryInfo info = exploreTerritory(x, y);
                    // Mark visited
                    for (Point p : info.territory) {
                        visited[p.y][p.x] = true;
                    }
                    // Add to correct owner
                    result.get(info.owner).addAll(info.territory);
                }
            }
        }
        return result;
    }

    // ------------------ Territory Logic --------------------

    private static class TerritoryInfo {
        Set<Point> territory = new HashSet<>();
        Player owner = Player.NONE;
    }

    private final int[][] DIRECTIONS = {{1,0},{-1,0},{0,1},{0,-1}};

    private TerritoryInfo exploreTerritory(int startX, int startY) {
        TerritoryInfo info = new TerritoryInfo();

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));

        boolean[][] visited = new boolean[rows][cols];

        Set<Character> borderingColors = new HashSet<>();

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (visited[p.y][p.x]) continue;
            visited[p.y][p.x] = true;

            if (board[p.y][p.x] != ' ') continue;

            info.territory.add(p);

            for (int[] d : DIRECTIONS) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || nx >= cols || ny < 0 || ny >= rows)
                    continue;

                char c = board[ny][nx];

                if (c == ' ') {
                    queue.add(new Point(nx, ny));
                } else if (c == 'B' || c == 'W') {
                    borderingColors.add(c);
                }
            }
        }

        if (borderingColors.size() == 1) {
            char only = borderingColors.iterator().next();
            info.owner = (only == 'B') ? Player.BLACK : Player.WHITE;
        } else {
            info.owner = Player.NONE;
        }

        return info;
    }
}
