package util;

import java.util.HashSet;
import java.util.Set;

public class Tile {
    private boolean isEnergized;
    private Character type;
    private Point position;
    private final Set<Direction> beams;

    public Tile() {
        this.beams = new HashSet<>();
    }

    public boolean isEnergized() {
        return isEnergized;
    }

    public Character getType() {
        return type;
    }

    public Point getPosition() {
        return position;
    }

    public Set<Direction> getBeams() {
        return beams;
    }

    public void setEnergized(boolean energized) {
        isEnergized = energized;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void reset() {
        this.isEnergized = false;
        this.beams.clear();
    }
}
