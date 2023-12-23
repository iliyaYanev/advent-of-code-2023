package day_23;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;
import util.Point;

public class LongWalkTest {

    private static final Map<Point, Character> GRID;

    static {
        try {
            GRID = GetInputFileContents.getFileAsGrid("src/test/resources/day_23/dayTwentyThreeInput.txt",
                Set.of('#'));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void longWalkPartOneTest() {
        long result = LongWalk.longestHike(GRID);

        assertEquals(2030, result);
    }

    @Test
    public void longWalkPartTwoTest() {
        long result = LongWalk.longestHikeSteps(GRID);

        assertEquals(6390, result);
    }
}
