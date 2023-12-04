package day_2;

import java.util.List;

public class CubeConundrumPartTwo {

    public static Integer powerSum(List<String> fileContents) {
        int powerSum = 0;

        for (String line : fileContents) {
            String[] input = line.split(": ");

            String[] game = input[1].split("; ");
            int currentGamePower = 0;
            int maxRed = 1;
            int maxGreen = 1;
            int maxBlue = 1;

            for (String configuration : game) {
                String[] cubes = configuration.split(", ");

                for (String cube : cubes) {
                    if (cube.contains("green")) {
                        int greenCubes = Integer.parseInt(cube.split(" ")[0]);

                        if (greenCubes > maxGreen) {
                            maxGreen = greenCubes;
                        }

                    } else if (cube.contains("red")) {
                        int redCubes = Integer.parseInt(cube.split(" ")[0]);

                        if (redCubes > maxRed) {
                            maxRed = redCubes;
                        }

                    }
                    else if (cube.contains("blue")) {
                        int blueCubes = Integer.parseInt(cube.split(" ")[0]);

                        if (blueCubes > maxBlue) {
                            maxBlue = blueCubes;
                        }
                    }
                }

                currentGamePower += maxRed * maxBlue * maxGreen;
                System.out.println();
            }

            currentGamePower = maxRed * maxBlue * maxGreen;

            powerSum += currentGamePower;
        }

        return powerSum;
    }
}
