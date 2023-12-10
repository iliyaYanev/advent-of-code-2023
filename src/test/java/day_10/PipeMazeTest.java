package day_10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class PipeMazeTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_10/dayTenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void pipeMazePartOneTest() {
        long result = PipeMaze.distance(FILE_CONTENTS, true);

        assertEquals(6820, result);
    }

    @Test
    public void pipeMazePartTwoTest() {
        long result = PipeMaze.distance(FILE_CONTENTS, false);

        assertEquals(337, result);
    }
}
