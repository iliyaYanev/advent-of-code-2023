package day_24;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class OddsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_24/dayTwentyFourInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void oddsPartOneTest() {
        long result = Odds.areaIntersections(FILE_CONTENTS);

        assertEquals(28174, result);
    }

    @Test
    public void oddsPartTwoTest() {
        long result = Odds.areaIntersectionsWithZ(FILE_CONTENTS);

        assertEquals(568386357876600L, result);
    }
}
