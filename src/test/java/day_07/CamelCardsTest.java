package day_07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class CamelCardsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_07/daySevenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void camelCardsPartOneTest() {
        long result = CamelCards.winnings(FILE_CONTENTS, false);

        assertEquals(254024898, result);
    }

    @Test
    public void camelCardsPartTwoTest() {
        long result = CamelCards.winnings(FILE_CONTENTS, true);

        assertEquals(254115617, result);
    }
}
