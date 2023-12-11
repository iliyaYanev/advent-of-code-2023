package day_11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class CosmicExpansionTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_11/dayElevenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void cosmicExpansionPartOneTest() {
        long result = CosmicExpansion.shortestPathOne(FILE_CONTENTS);

        assertEquals(10292708, result);
    }

    @Test
    public void cosmicExpansionPartTwoTest() {
        long result = CosmicExpansion.shortestPathTwo(FILE_CONTENTS);

        assertEquals(790194712336L, result);
    }
}
