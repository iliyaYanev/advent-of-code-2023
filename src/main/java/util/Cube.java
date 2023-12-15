package util;

import java.util.ArrayList;
import java.util.List;

public class Cube {

    private final int index;

    private final List<Lens> lenses;

    public Cube(int index) {
        this.index = index;
        this.lenses = new ArrayList<>();
    }

    public List<Lens> getLenses() {
        return lenses;
    }

    public int getIndex() {
        return index;
    }
}
