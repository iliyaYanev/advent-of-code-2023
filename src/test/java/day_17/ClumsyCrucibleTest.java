package day_17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ClumsyCrucibleTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_17/daySeventeenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void clumsyCruciblePartOneTest() {
        long result = ClumsyCrucible.minimumHeatLoss(FILE_CONTENTS);

        assertEquals(942, result);
    }

    @Test
    public void clumsyCruciblePartTwoTest() {
        long result = ClumsyCrucible.minimumHearLossUltra(FILE_CONTENTS);

        assertEquals(1082, result);
    }
}
