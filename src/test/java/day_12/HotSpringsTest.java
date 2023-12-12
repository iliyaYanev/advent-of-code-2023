package day_12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class HotSpringsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_12/dayTwelveInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void hotSpringsPartOneTest() {
        long result = HotSprings.arrangementCount(FILE_CONTENTS);

        assertEquals(7173, result);
    }

    @Test
    public void hotSpringsPartTwoTest() {
        long result = HotSprings.unfoldedArrangementCount(FILE_CONTENTS);

        assertEquals(29826669191291L, result);
    }
}
