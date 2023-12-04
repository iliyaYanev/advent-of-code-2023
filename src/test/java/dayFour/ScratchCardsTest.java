package dayFour;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ScratchCardsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/dayFour/dayFourTestInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void scratchCardPointsPartOneTest() {
        long result = ScratchCardsPartOne.scratchCardPoints(FILE_CONTENTS);

        assertEquals(13, result);
    }
}
