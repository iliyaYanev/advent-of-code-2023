package day_9;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class MirageMaintenanceTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_9/dayNineTestInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void mirageMaintenancePartOneTest() {
        long result = MirageMaintenance.extrapolatedSumPartOne(FILE_CONTENTS);

        assertEquals(114, result);
    }

    @Test
    public void mirageMaintenancePartTwoTest() {
        long result = MirageMaintenance.extrapolatedSumPartTwo(FILE_CONTENTS);

        assertEquals(2, result);
    }
}
