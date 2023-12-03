package dayThree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class GearRatiosTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/dayThree/dayThreeTestInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void gearRatiosPartOneTest() {
        int result = GearRatiosPartOne.partNumbers(FILE_CONTENTS);

        assertEquals(4361, result);
    }

    @Test
    public void gearRatiosPartTwoTest() {
        int result  = GearRatiosPartTwo.gearRatios(FILE_CONTENTS);

        assertEquals(467835, result);
    }
}
