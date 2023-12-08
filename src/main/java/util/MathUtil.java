package util;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.util.ArithmeticUtils;

public class MathUtil {
    public static long lcm(List<Long> numbers) {
        long result = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            result = ArithmeticUtils.lcm(result, numbers.get(i));
        }

        return result;
    }

    public static long lcm(Collection<Long> numbers) {
        return lcm(List.copyOf(numbers));
    }
}