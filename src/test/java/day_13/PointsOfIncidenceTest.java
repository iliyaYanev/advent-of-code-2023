package day_13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class PointsOfIncidenceTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_13/dayThirteenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void pointsOfIncidencePartOneTest() {
        long result = PointsOfIncidence.reflectionPatterns(FILE_CONTENTS);

        assertEquals(31739, result);
    }

    @Test
    public void pointsOfIncidencePartTwoTest() {
        long result = PointsOfIncidence.reflectionPatternsReflectionLine(FILE_CONTENTS);

        assertEquals(31539, result);
    }
}
