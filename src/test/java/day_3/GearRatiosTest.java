package day_3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class GearRatiosTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_3/dayThreeInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void gearRatiosPartOneTest() {
        long result = GearRatios.partNumbers(FILE_CONTENTS);

        assertEquals(556367, result);
    }

    @Test
    public void gearRatiosPartTwoTest() {
        long result  = GearRatios.gearRatios(FILE_CONTENTS);

        assertEquals(89471771, result);
    }
}
