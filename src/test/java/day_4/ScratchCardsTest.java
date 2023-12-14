package day_4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ScratchCardsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_4/dayFourInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void scratchCardPointsPartOneTest() {
        long result = ScratchCards.scratchCardPoints(FILE_CONTENTS);

        assertEquals(21088, result);
    }

    @Test
    public void scratchCardPointsPartTwoTest() {
        long result = ScratchCards.totalScratchCards(FILE_CONTENTS);

        assertEquals(6874754, result);
    }
}
