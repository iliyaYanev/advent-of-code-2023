package day_2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class CubeConundrumTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_2/dayTwoTestInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void cubeConundrumPartOneTest() {
        int result = CubeConundrumPartOne.possibleGames(FILE_CONTENTS);

        assertEquals(8, result);
    }

    @Test
    public void cubeConundrumPartTwoTest() {
        int result = CubeConundrumPartTwo.powerSum(FILE_CONTENTS);

        assertEquals(2286, result);
    }
}
