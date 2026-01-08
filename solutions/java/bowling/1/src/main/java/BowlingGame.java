public class BowlingGame {
    private final int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        if (pins < 0) {
            throw new IllegalStateException("Negative roll is invalid");
        }
        if (pins > 10) {
            throw new IllegalStateException("Pin count exceeds pins on the lane");
        }

        if (currentRoll >= 20) { 
            handleTenthFrameRoll(pins);
        } else {
            handleNormalFrameRoll(pins);
        }

        rolls[currentRoll++] = pins;
    }

    private void handleNormalFrameRoll(int pins) {
        int frameIndex = currentRoll / 2;
        boolean firstRollInFrame = currentRoll % 2 == 0;

        if (!firstRollInFrame) {
            int firstRoll = rolls[currentRoll - 1];
            if (firstRoll != 10 && firstRoll + pins > 10) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
        }
    }

    private void handleTenthFrameRoll(int pins) {
        int rollInFrame = currentRoll - 18;

        int first = rolls[18];
        int second = rolls[19];

        if (rollInFrame == 0) {
            return;
        }

        if (rollInFrame == 1) {
            if (first != 10 && first + pins > 10) {
                throw new IllegalStateException("Pin count exceeds pins on the lane");
            }
            return;
        }

        if (rollInFrame == 2) {
            if (first == 10) {
                if (second != 10 && second + pins > 10) {
                    throw new IllegalStateException("Pin count exceeds pins on the lane");
                }
            } else if (first + second == 10) { 
            } else {
                throw new IllegalStateException("Cannot roll after game is over");
            }
        }

        if (rollInFrame > 2) {
            throw new IllegalStateException("Cannot roll after game is over");
        }
    }

    public int score() {
        int score = 0;
        int rollIndex = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (rolls[rollIndex] == 10) {
                if (rollIndex + 2 >= currentRoll) {
                    throw new IllegalStateException("Score cannot be taken until the end of the game");
                }
                score += 10 + rolls[rollIndex + 1] + rolls[rollIndex + 2];
                rollIndex++;
            } else if (rolls[rollIndex] + rolls[rollIndex + 1] == 10) {
                if (rollIndex + 2 >= currentRoll) {
                    throw new IllegalStateException("Score cannot be taken until the end of the game");
                }
                score += 10 + rolls[rollIndex + 2];
                rollIndex += 2;
            } else {
                if (rollIndex + 1 >= currentRoll) {
                    throw new IllegalStateException("Score cannot be taken until the end of the game");
                }
                score += rolls[rollIndex] + rolls[rollIndex + 1];
                rollIndex += 2;
            }
        }

        return score;
    }
}
