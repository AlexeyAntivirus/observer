package introspection.references;

@FunctionalInterface
public interface MethodReference<T> {
	Object invoke(T target, Object ... args);
}
