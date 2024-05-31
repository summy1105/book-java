package modernaction.chap20.pattern_match;

@FunctionalInterface
public interface TriFunction<S, T, U, R>{
    R apply(S s, T t, U u);
}
