package day_8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class WastelandTest {

    @Test
    public void wastelandPartOneTest() throws IOException {
        String fileContents = GetInputFileContents
            .getFileAsString("src/test/resources/day_8/dayEightTestInputPartOne.txt");

        long result = Wasteland.wastelandStepsPartOne(fileContents);

        assertEquals(2, result);
    }

    @Test
    public void wastelandPartTwoTest() throws IOException {
        String fileContents = GetInputFileContents
            .getFileAsString("src/test/resources/day_8/dayEightTestInputPartTwo.txt");

        long result = Wasteland.wastelandStepsPartTwo(fileContents);

        assertEquals(6, result);
    }
}
