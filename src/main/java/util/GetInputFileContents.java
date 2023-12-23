package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetInputFileContents {

    public static List<String> getFileLines(String inputFilePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));

        return reader.lines().toList();
    }

    public static String getFileAsString(String inputFilePath) throws IOException {
        return Files.readString(Path.of(inputFilePath));
    }

    public static Map<Point, Character> getFileAsGrid(String inputFilePath, Set<Character> withoutCharacters) throws IOException {
        String[] lines = getFileAsString(inputFilePath).split(System.lineSeparator());
        Map<Point, Character> grid = new HashMap<>();

        for (int col = 0; col < lines.length; col++) {
            for (int row = 0; row < lines[col].length(); row++) {
                if (!withoutCharacters.contains(lines[col].charAt(row))) {
                    grid.put(new Point(row, col), lines[col].charAt(row));
                }
            }
        }

        return grid;
    }
}
