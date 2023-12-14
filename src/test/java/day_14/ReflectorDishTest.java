package day_14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ReflectorDishTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_14/dayFourteenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void reflectorDishPartOneTest() {
        long result = ReflectorDish.northSupportBeamsLoad(FILE_CONTENTS);

        assertEquals(109385, result);
    }

    @Test
    public void reflectorDishPartTwoTest() {
        long result = ReflectorDish.northSupportBeamsLoadSpinCycles(FILE_CONTENTS);

        assertEquals(93102, result);
    }
}
