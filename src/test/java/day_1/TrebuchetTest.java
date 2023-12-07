package day_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class TrebuchetTest {

    @Test
    public void trebuchetPartOneTest() throws FileNotFoundException {
        List<String> fileContents = GetInputFileContents
            .getFileLines("src/test/resources/day_1/dayOnePartOneInputTest.txt");

        long result = TrebuchetPartOne.getCalibrationValue(fileContents);

        assertEquals(142, result);
    }

    @Test
    public void trebuchetPartTwoTest() throws FileNotFoundException {
        List<String> fileContents = GetInputFileContents
            .getFileLines("src/test/resources/day_1/dayOnePartTwoInputTest.txt");

        long result = TrebuchetPartTwo.getCalibrationValue(fileContents);

        assertEquals(281, result);
    }
}
