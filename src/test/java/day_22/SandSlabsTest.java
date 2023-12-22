package day_22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class SandSlabsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_22/dayTwentyTwoInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sandSlabsPartOneTest() {
        long result = SandSlabs.bricksDisintegrated(FILE_CONTENTS);

        assertEquals(391, result);
    }

    @Test
    public void sandSlabsPartTwoTest() {
        long result = SandSlabs.otherBricksDisintegrated(FILE_CONTENTS);

        assertEquals(69601, result);
    }
}
