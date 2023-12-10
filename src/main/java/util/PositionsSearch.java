package util;

public record PositionsSearch(Position currentPosition, Direction from) {

    public static PositionsSearch findNextPipeNode(PositionsSearch posSearchHelper, char[][] nodes) {
        final Position currentPos = posSearchHelper.currentPosition();
        final char currentPipe = nodes[currentPos.y()][currentPos.x()];

        return switch (currentPipe) {
            case '|' -> posSearchHelper.from() == Direction.NORTH ?
                new PositionsSearch(new Position(currentPos.x(), currentPos.y() + 1), Direction.NORTH) :
                new PositionsSearch(new Position(currentPos.x(), currentPos.y() - 1), Direction.SOUTH);
            case '-' -> posSearchHelper.from() == Direction.WEST ?
                new PositionsSearch(new Position(currentPos.x() + 1, currentPos.y()), Direction.WEST) :
                new PositionsSearch(new Position(currentPos.x() - 1, currentPos.y()), Direction.EAST);
            case 'L' -> posSearchHelper.from() == Direction.NORTH ?
                new PositionsSearch(new Position(currentPos.x() + 1, currentPos.y()), Direction.WEST) :
                new PositionsSearch(new Position(currentPos.x(), currentPos.y() - 1), Direction.SOUTH);
            case 'J' -> posSearchHelper.from() == Direction.NORTH ?
                new PositionsSearch(new Position(currentPos.x() - 1, currentPos.y()), Direction.EAST) :
                new PositionsSearch(new Position(currentPos.x(), currentPos.y() - 1), Direction.SOUTH);
            case '7' -> posSearchHelper.from() == Direction.WEST ?
                new PositionsSearch(new Position(currentPos.x(), currentPos.y() + 1), Direction.NORTH) :
                new PositionsSearch(new Position(currentPos.x() - 1, currentPos.y()), Direction.EAST);
            case 'F' -> posSearchHelper.from() == Direction.EAST ?
                new PositionsSearch(new Position(currentPos.x(), currentPos.y() + 1), Direction.NORTH) :
                new PositionsSearch(new Position(currentPos.x() + 1, currentPos.y()), Direction.WEST);
            default -> throw new RuntimeException("Invalid direction");
        };
    }
}
