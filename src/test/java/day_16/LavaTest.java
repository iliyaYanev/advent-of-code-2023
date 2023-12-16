package day_16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class LavaTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_16/daySixteenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void lavaPartOneTest() {
        long result = Lava.tilesEnergized(FILE_CONTENTS);

        assertEquals(6514, result);
    }

    @Test
    public void lavaPartTwoTest() {
        long result = Lava.tilesEnergizedSecondConfig(FILE_CONTENTS);

        assertEquals(8089, result);
    }
}
