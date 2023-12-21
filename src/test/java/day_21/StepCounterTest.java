package day_21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class StepCounterTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_21/dayTwentyOneInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void stepCounterPartOneTest() {
        long result = StepCounter.gardenPlotsReached(FILE_CONTENTS);

        assertEquals(3853, result);
    }

    @Test
    public void stepCounterPartTwoTest() {
        long result = StepCounter.gardenPlotsReachedExactSteps(FILE_CONTENTS);

        assertEquals(639051580070841L, result);
    }
}
