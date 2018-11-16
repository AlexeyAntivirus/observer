package introspection.observable;

import java.util.HashMap;
import java.util.Map;

public class ObjectObserver<T> {
	private final T target;
	private final Map<String, ObservableMethod<T>> observableMethods;
	private final Map<String, ObservableProperty<T, ?>> observableProperties;

	private ObjectObserver(ObjectObserverBuilder<T> builder) {
		this.target = builder.target;
		this.observableMethods = builder.observableMethods;
		this.observableProperties = builder.observableProperties;
	}

	@SuppressWarnings("unchecked")
	public <V> void changeState(String propertyName, V value) {
		if (!this.observableProperties.containsKey(propertyName)) {
			throw new IllegalArgumentException();
		}
		ObservableProperty<T, V> observableProperty = (ObservableProperty<T, V>) this.observableProperties.get(propertyName);
		observableProperty.setValue(value);
	}

	public <V> V invokeMethod(String methodName, Object ... args) {
		if (!this.observableMethods.containsKey(methodName)) {
			throw new IllegalArgumentException();
		}
		ObservableMethod<T> observableProperty = this.observableMethods.get(methodName);
		return observableProperty.invoke(args);
	}

	public static <T> ObjectObserverBuilder<T> builder() {
		return new ObjectObserverBuilder<>();
	}

	public static class ObjectObserverBuilder<E> {
		private E target;
		private Map<String, ObservableMethod<E>> observableMethods = new HashMap<>();
		private Map<String, ObservableProperty<E, ?>> observableProperties = new HashMap<>();

		private ObjectObserverBuilder() {}

		public ObjectObserverBuilder<E> addProperty(ObservableProperty<E, ?> property) {
			this.observableProperties.put(property.name, property);
			return this;
		}

		public ObjectObserverBuilder<E> addMethod(ObservableMethod<E> method) {
			this.observableMethods.put(method.name, method);
			return this;
		}

		public ObjectObserver<E> build() {
			return new ObjectObserver<>(this);
		}
	}
}
