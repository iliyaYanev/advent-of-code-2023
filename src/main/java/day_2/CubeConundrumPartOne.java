package day_2;

import java.util.List;
import java.util.Map;

public class CubeConundrumPartOne {

    private static final Map<String,Integer> CUBE_CONFIGURATION = Map.of(
        "red", 12,
        "green", 13,
        "blue", 14
    );

    public static Integer possibleGames(List<String> fileContents) {
        int sum = 0;

        for (String line : fileContents) {
            String[] input = line.split(": ");

            boolean possible = true;
            int gameId = Integer.parseInt(input[0].split(" ")[1]);
            String[] game = input[1].split("; ");

            for (String configuration : game) {
                String[] cubes = configuration.split(", ");

                for (String cube : cubes) {
                    if (cube.contains("green")) {
                        int greenCubes = Integer.parseInt(cube.split(" ")[0]);

                        if (greenCubes > CUBE_CONFIGURATION.get("green")) {
                            possible = false;
                            break;
                        }
                    } else if (cube.contains("red")) {
                        int redCubes = Integer.parseInt(cube.split(" ")[0]);

                        if (redCubes > CUBE_CONFIGURATION.get("red")) {
                            possible = false;
                            break;
                        }
                    }
                    else if (cube.contains("blue")) {
                        int blueCubes = Integer.parseInt(cube.split(" ")[0]);

                        if (blueCubes > CUBE_CONFIGURATION.get("blue")) {
                            possible = false;
                            break;
                        }
                    }
                }
            }

            if (possible) {
                sum += gameId;
            }
        }

        return sum;
    }
}
