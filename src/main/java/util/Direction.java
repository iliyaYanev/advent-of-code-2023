package util;

public enum Direction {
    WEST,
    NORTH,
    EAST,
    SOUTH;

    public Direction turnLeft() {
        return switch (this) {
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case NORTH -> WEST;
            case SOUTH -> EAST;
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case NORTH -> EAST;
            case SOUTH -> WEST;
        };
    }
}
