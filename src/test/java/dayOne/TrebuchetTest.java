package dayOne;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class TrebuchetTest {

    @Test
    public void trebuchetPartOneTest() throws FileNotFoundException {
        List<String> fileContents = GetInputFileContents
            .getFileLines("src/test/resources/dayOne/dayOnePartOneInputTest.txt");

        int result = TrebuchetPartOne.getCalibrationValue(fileContents);

        assertEquals(142, result);
    }

    @Test
    public void trebuchetPartTwoTest() throws FileNotFoundException {
        List<String> fileContents = GetInputFileContents
            .getFileLines("src/test/resources/dayOne/dayOnePartTwoInputTest.txt");

        int result = TrebuchetPartTwo.getCalibrationValue(fileContents);

        assertEquals(281, result);
    }
}
