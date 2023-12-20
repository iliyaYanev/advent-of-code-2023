package util;

import java.util.function.BiFunction;
import javax.annotation.Nonnull;

public record Pair<A, B>(A a, B b) implements Comparable<Pair<A, B>> {

    @Override
    public A a() {
        return a;
    }

    @Override
    public B b() {
        return b;
    }

    public <C> C map(BiFunction<A, B, C> func) {
        return func.apply(a(), b());
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compareTo(@Nonnull Pair<A, B> t) {
        if (a instanceof Comparable && t.a != null) {
            return ((Comparable<A>) a).compareTo(t.a);
        }

        return 0;
    }
}
