package day_19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class AplentyTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_19/dayNineteenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void aplentyPartOneTest() {
        long result = Aplenty.acceptedPartsSum(FILE_CONTENTS);

        assertEquals(495298, result);
    }

    @Test
    public void aplentyPartTwoTest() {
        long result = Aplenty.distinctRatingCombinations(FILE_CONTENTS);

        assertEquals(132186256794011L, result);
    }
}
