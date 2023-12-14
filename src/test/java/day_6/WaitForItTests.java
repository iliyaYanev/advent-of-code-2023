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
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_6/daySixInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void waitForItPartOneTest() {
        long result = WaitForIt.possibleWins(FILE_CONTENTS);

        assertEquals(128700, result);
    }

    @Test
    public void waitForItPartTwoTest() {
        long result = WaitForIt.possibleWinsLongerRace(FILE_CONTENTS);

        assertEquals(39594072, result);
    }
}
