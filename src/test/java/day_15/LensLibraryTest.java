package day_15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class LensLibraryTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_15/dayFifteenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void lensLibraryPartOneTest() {
        long result = LensLibrary.initializationHash(FILE_CONTENTS);

        assertEquals(516070, result);
    }

    @Test
    public void lensLibraryPartTwoTest() {
        long result = LensLibrary.focusingPower(FILE_CONTENTS);

        assertEquals(244981, result);
    }
}
