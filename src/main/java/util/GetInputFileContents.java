package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class GetInputFileContents {

    public static List<String> getFileLines(String inputFilePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));

        return reader.lines().toList();
    }
}
