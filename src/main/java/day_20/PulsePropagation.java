package day_20;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.LongStream.range;
import static util.MathUtil.lcm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import util.Module;
import util.Pair;
import util.Signal;
import util.Type;

public class PulsePropagation {

    public static long numberLowHighPulses(List<String> fileContents) {
        Map<String, Module> modules = fileContents.stream()
            .map(Module::new)
            .collect(toMap(Module::source, m -> m));

        Map<String, Boolean> flipflopState = modules.keySet().stream()
            .filter(m -> modules.get(m).type() == Type.FLIPFLOP)
            .collect(toMap(k -> k, k -> false));

        Map<String, Map<String, Boolean>> conjunctionState = modules.keySet().stream()
            .filter(m -> modules.get(m).type() == Type.CONJUNCTION)
            .collect(toMap(k -> k, k -> modules.entrySet()
                .stream()
                .filter(e -> e.getValue().targets().contains(k)).map(Map.Entry::getKey)
                .collect(toMap(k2 -> k2, k2 -> false))));

        return range(0, 1000).mapToObj(i -> sendPulse(modules, flipflopState, conjunctionState, new HashMap<>(), i))
            .reduce(new Pair<>(0L, 0L), (a, b) -> new Pair<>(a.a() + b.a(), a.b() + b.b())).map((a, b) -> a * b);
    }

    public static long numberLowHighPulses2(List<String> fileContents) {
        Map<String, Module> modules = fileContents.stream()
            .map(Module::new)
            .collect(toMap(Module::source, m -> m));

        String rx = modules.keySet().stream()
            .filter(m -> modules.get(m).targets().contains("rx"))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);

        Map<String, Boolean> flipflopState = modules.keySet().stream()
            .filter(m -> modules.get(m).type() == Type.FLIPFLOP)
            .collect(toMap(k -> k, k -> false));

        Map<String, Map<String, Boolean>> conjunctionState = modules.keySet().stream()
            .filter(m -> modules.get(m).type() == Type.CONJUNCTION)
            .collect(toMap(k -> k, k -> modules.entrySet().stream()
                .filter(e -> e.getValue().targets().contains(k))
                .map(Map.Entry::getKey)
                .collect(toMap(k2 -> k2, k2 -> false))));

        Map<String, Long> lcms = conjunctionState.get(rx).keySet()
            .stream()
            .collect(toMap(e -> e, e -> 0L));

        return range(1, Long.MAX_VALUE).map(i -> sendPulse(modules, flipflopState, conjunctionState, lcms, i).a())
            .filter(e -> e != 0L)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    private static Pair<Long, Long> sendPulse(Map<String, Module> modules,
        Map<String, Boolean> state, Map<String, Map<String, Boolean>> conjunctionState,
        Map<String, Long> lcms, long i) {
        Pair<Long, Long> output = new Pair<>(0L, 0L);
        LinkedList<Signal> pulseQueue = new LinkedList<>();

        pulseQueue.add(new Signal("", "broadcaster", false));
        while (!pulseQueue.isEmpty()) {
            Signal pulse = pulseQueue.pop();
            boolean isHigh = pulse.high();
            output = output.map((a, b) -> new Pair<>(a + (isHigh ? 0 : 1), b + (isHigh ? 1 : 0)));

            if (isHigh && lcms.containsKey(pulse.from())) {
                lcms.put(pulse.from(), i);
                if (lcms.values().stream().allMatch(e -> e != 0L)) {
                    return new Pair<>(lcm(lcms.values().stream().mapToLong(e -> e).toArray()), 0L);
                }
            }

            if (!modules.containsKey(pulse.to())) {
                continue;
            }

            Module module = modules.get(pulse.to());

            if (module.type() == Type.BROADCAST) {
                module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, isHigh)));
            } else if (module.type() == Type.CONJUNCTION) {
                conjunctionState.get(pulse.to()).put(pulse.from(), isHigh);
                if (conjunctionState.get(pulse.to()).values().stream().allMatch(b -> b)) {
                    module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, false)));
                } else {
                    module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, true)));
                }
            } else {
                if (!isHigh) {
                    boolean newState = !state.get(module.source());
                    state.put(module.source(), newState);
                    module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, newState)));
                }
            }
        }

        if (!lcms.isEmpty()) {
            return new Pair<>(0L, 0L);
        }

        return output;
    }
}
