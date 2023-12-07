package day_7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class CamelCardsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_7/daySevenTestInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void camelCardsPartOneTest() {
        long result = CamelCardsPartOne.winnings(FILE_CONTENTS);

        assertEquals(6440, result);
    }

    @Test
    public void camelCardsPartTwoTest() {
        long result = CamelCardsPartTwo.winnings(FILE_CONTENTS);

        assertEquals(5905, result);
    }
}
