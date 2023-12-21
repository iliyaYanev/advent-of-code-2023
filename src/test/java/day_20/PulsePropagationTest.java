package day_20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class PulsePropagationTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_20/dayTwentyInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void pulsePropagationPartOneTest() {
        long result = PulsePropagation.numberLowHighPulses(FILE_CONTENTS);

        assertEquals(832957356, result);
    }

    @Test
    public void pulsePropagationPartTwoTest() {
        long result = PulsePropagation.fewestPresses(FILE_CONTENTS);

        assertEquals(240162699605221L, result);
    }
}
