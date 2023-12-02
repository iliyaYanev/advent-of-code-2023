package dayOne;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class TrebuchetTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/dayOne/dayOneInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void trebuchetPartOneTest() {
        int result = TrebuchetPartOne.getCalibrationValue(FILE_CONTENTS);

        assertEquals(54953, result);
    }

    @Test
    public void trebuchetPartTwoTest() {
        int result = TrebuchetPartTwo.getCalibrationValue(FILE_CONTENTS);

        assertEquals(53885, result);
    }
}
