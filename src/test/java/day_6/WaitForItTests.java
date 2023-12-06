package day_6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class WaitForItTests {
    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_6/daySixTestInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void waitForItPartOneTest() {
        long result = WaitForItPartOne.possibleWins(FILE_CONTENTS);

        assertEquals(288, result);
    }

    @Test
    public void waitForItPartTwoTest() {
        long result = WaitForItPartTwo.possibleWins(FILE_CONTENTS);

        assertEquals(71503, result);
    }
}
