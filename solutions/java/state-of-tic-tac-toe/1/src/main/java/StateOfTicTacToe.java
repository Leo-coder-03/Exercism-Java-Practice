class StateOfTicTacToe {

    public GameState determineState(String[] board) {
        char[][] g = new char[3][3];
        for (int i = 0; i < 3; i++) {
            g[i] = board[i].toCharArray();
        }

        int countX = 0, countO = 0;
        for (char[] row : g) {
            for (char c : row) {
                if (c == 'X') countX++;
                else if (c == 'O') countO++;
            }
        }

        if (countO > countX) {
            throw new IllegalArgumentException("Wrong turn order: O started");
        }
        if (countX - countO > 1) {
            throw new IllegalArgumentException("Wrong turn order: X went twice");
        }

        boolean xWins = wins(g, 'X');
        boolean oWins = wins(g, 'O');

        if (xWins && oWins) {
            throw new IllegalArgumentException(
                "Impossible board: game should have ended after the game was won"
            );
        }

        if (xWins && countX == countO) {
            throw new IllegalArgumentException(
                "Impossible board: game should have ended after the game was won"
            );
        }
        if (oWins && countX > countO) {
            throw new IllegalArgumentException(
                "Impossible board: game should have ended after the game was won"
            );
        }

        if (xWins || oWins) return GameState.WIN;

        if (countX + countO == 9) return GameState.DRAW;

        return GameState.ONGOING;
    }

    private boolean wins(char[][] g, char p) {
        for (int r = 0; r < 3; r++)
            if (g[r][0] == p && g[r][1] == p && g[r][2] == p) return true;

        for (int c = 0; c < 3; c++)
            if (g[0][c] == p && g[1][c] == p && g[2][c] == p) return true;

        if (g[0][0] == p && g[1][1] == p && g[2][2] == p) return true;
        if (g[0][2] == p && g[1][1] == p && g[2][0] == p) return true;

        return false;
    }
}
