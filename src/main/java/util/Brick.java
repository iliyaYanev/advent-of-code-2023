package util;

import java.util.HashSet;
import java.util.Set;

public class Brick {

    private final Point3D firstPoint;

    private final Point3D secondPoint;

    private final Set<Brick> supports;

    private final Set<Brick> supportedBy;

    private boolean wouldFall;

    private boolean removed;

    public Brick(Point3D firstPoint, Point3D secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.supports = new HashSet<>();
        this.supportedBy = new HashSet<>();
    }

    public Point3D getFirstPoint() {
        return firstPoint;
    }

    public Point3D getSecondPoint() {
        return secondPoint;
    }

    public boolean wouldFall() {
        return wouldFall;
    }

    public Set<Brick> getSupports() {
        return supports;
    }

    public Set<Brick> getSupportedBy() {
        return supportedBy;
    }

    public void setWouldFall(boolean wouldFall) {
        this.wouldFall = wouldFall;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public void markBricksThatWouldFall() {
        for (Brick brick: supports) {
            if (brick.supportedBy.stream().noneMatch(b -> !b.removed && !b.wouldFall)) {
                brick.wouldFall = true;
            }
        }

        supports.forEach(Brick::markBricksThatWouldFall);
    }
}
