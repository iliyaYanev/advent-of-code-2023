package day_5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class FertilizerTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_5/dayFiveInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void fertilizerPartOneTest() {
        long result = FertilizerPartOne.lowestLocation(FILE_CONTENTS);

        assertEquals(535088217, result);
    }

    @Test
    public void fertilizerPartTwoTest() {
        long result = FertilizerPartTwo.lowestLocation(FILE_CONTENTS);

        assertEquals(51399228, result);
    }
}
