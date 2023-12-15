package day_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import util.Cube;
import util.Lens;
import util.Regex;

public class LensLibrary {

    public static long initializationHash(String input) {
        long hash = 0;

        List<String> lines = Arrays.stream(input.trim().split(","))
            .toList();

        for (String line: lines) {
            hash += line.chars().reduce(0, (a, b) -> (a + b) * 17 % 256);
        }

        return hash;
    }

    public static long focusingPower(String input) {
        long focusingPower = 0;
        List<String> lines = Arrays.stream(input.trim().split(","))
            .toList();

        List<Cube> cubes = new ArrayList<>();

        for (int i = 0; i < 256; i++) {
            cubes.add(new Cube(i));
        }

        next:
        for (String line: lines) {
            List<String> matches = Objects.requireNonNull(Regex
                .matchGroups("([a-z]+)([=\\-])(\\d+)?", line));

            String lensName = matches.getFirst();
            char sign = matches.get(1).charAt(0);

            Integer num = matches.size() == 3 ? Integer.parseInt(matches.getLast()) : null;
            int cubeIndex = lensName.chars().reduce(0, (a, b) -> (a + b) * 17 % 256);
            Cube cube = cubes.get(cubeIndex);

            if (sign == '=') {
                assert num != null;

                for (Lens lens: cube.getLenses()) {
                    if (lens.getName().equals(lensName)) {
                        lens.setValue(num);
                        continue next;
                    }
                }

                cube.getLenses().add(new Lens(lensName, num));
                continue;
            }

            cube.getLenses().removeIf(lens -> lens.getName().equals(lensName));
        }

        for (Cube cube: cubes) {
            var index = 1;

            for (Lens lens: cube.getLenses()) {
                focusingPower += (1L + cube.getIndex()) * index++ * lens.getValue();
            }
        }

        return focusingPower;
    }
}
