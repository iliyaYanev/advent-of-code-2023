package day_8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class WastelandTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_8/dayEightInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void wastelandPartOneTest() {
        long result = WastelandPartOne.wastelandSteps(FILE_CONTENTS);

        assertEquals(19631, result);
    }

    @Test
    public void wastelandPartTwoTest() {
        long result = WastelandPartTwo.wastelandSteps(FILE_CONTENTS);

        assertEquals(21003205388413L, result);
    }
}
