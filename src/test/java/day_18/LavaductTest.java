package day_18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class LavaductTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_18/dayEighteenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void lavaductPartOneTest() {
        long result = Lavaduct.cubicMetersLava(FILE_CONTENTS);

        assertEquals(26857, result);
    }

    @Test
    public void lavaductPartTwoTest() {
        long result = Lavaduct.cubicMetersLavaNewPlan(FILE_CONTENTS);

        assertEquals(129373230496292L, result);
    }
}
