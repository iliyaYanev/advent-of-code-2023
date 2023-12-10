package util;

public record Position(int x, int y) {

    public boolean equals(Position other) {
        return this.x == other.x() && this.y == other.y();
    }
}
