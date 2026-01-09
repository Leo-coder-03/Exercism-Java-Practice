class Robot {

    private GridPosition position;
    private Orientation orientation;

    Robot(GridPosition initialPosition, Orientation initialOrientation) {
        this.position = initialPosition;
        this.orientation = initialOrientation;
    }

    GridPosition getGridPosition() {
        return position;
    }

    Orientation getOrientation() {
        return orientation;
    }

    void advance() {
        int x = position.x;
        int y = position.y;

        switch (orientation) {
            case NORTH -> position = new GridPosition(x, y + 1);
            case SOUTH -> position = new GridPosition(x, y - 1);
            case EAST  -> position = new GridPosition(x + 1, y);
            case WEST  -> position = new GridPosition(x - 1, y);
        }
    }

    void turnLeft() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.WEST;
            case WEST  -> orientation = Orientation.SOUTH;
            case SOUTH -> orientation = Orientation.EAST;
            case EAST  -> orientation = Orientation.NORTH;
        }
    }

    void turnRight() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.EAST;
            case EAST  -> orientation = Orientation.SOUTH;
            case SOUTH -> orientation = Orientation.WEST;
            case WEST  -> orientation = Orientation.NORTH;
        }
    }

    void simulate(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'L' -> turnLeft();
                case 'R' -> turnRight();
                case 'A' -> advance();
                default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }
        }
    }
}
