package day_10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class PipeMazeTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_10/dayTenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void pipeMazePartOneTest() {
        long result = PipeMazePartOne.farthestPoint(FILE_CONTENTS);

        assertEquals(6820, result);
    }

//    @Test
//    public void pipeMazePartTwoTest() {
//        long result = PipeMazePartTwo.tilesEnclosed(FILE_CONTENTS);
//
//        assertEquals(337, result);
//    }
}
