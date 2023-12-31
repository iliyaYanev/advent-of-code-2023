package day_08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class WastelandTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_08/dayEightInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void wastelandPartOneTest() {
        long result = Wasteland.wastelandSteps(FILE_CONTENTS);

        assertEquals(19631, result);
    }

    @Test
    public void wastelandPartTwoTest() {
        long result = Wasteland.wastelandStepsZ(FILE_CONTENTS);

        assertEquals(21003205388413L, result);
    }
}
