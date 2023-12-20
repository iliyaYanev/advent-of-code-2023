package util;

import java.util.Arrays;
import java.util.List;

public record Module(Type type, String source, List<String> targets) {

    public Module(String input) {
        this(switch (input.charAt(0)) {
                case '%' -> Type.FLIPFLOP;
                case '&' -> Type.CONJUNCTION;
                default -> Type.BROADCAST;
            },
            input.split(" -> ")[0].replaceAll("[&%]", ""),
            Arrays.asList(input.split(" -> ")[1].split(", ")));
    }
}
