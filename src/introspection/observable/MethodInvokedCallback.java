package introspection.observable;

import java.beans.PropertyChangeEvent;

@FunctionalInterface
public interface MethodInvokedCallback<T, V> {
	PropertyChangeEvent methodInvoked(T target, V result, Object... args);
}
