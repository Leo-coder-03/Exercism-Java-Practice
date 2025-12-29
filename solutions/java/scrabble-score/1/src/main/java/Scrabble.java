class Scrabble {
    private final String word;

    Scrabble(String word) {
        this.word = word == null ? "" : word.toUpperCase();
    }

    int getScore() {
        int score = 0;

        for (char c : word.toCharArray()) {
            score += scoreForLetter(c);
        }

        return score;
    }

    private int scoreForLetter(char c) {
        return switch (c) {
            case 'A','E','I','O','U','L','N','R','S','T' -> 1;
            case 'D','G' -> 2;
            case 'B','C','M','P' -> 3;
            case 'F','H','V','W','Y' -> 4;
            case 'K' -> 5;
            case 'J','X' -> 8;
            case 'Q','Z' -> 10;
            default -> 0;
        };
    }
}
