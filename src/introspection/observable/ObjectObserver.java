package introspection.observable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

public class ObjectObserver<T> {
	protected final T target;
	private final Map<String, MethodDescriptor<T>> observableMethods;
	private final Map<String, MethodInvokedCallback<T, ?>> methodInvokedCallbacks;
	private final Map<String, PropertyDescriptor<T, ?>> observableProperties;
	private final PropertyChangeSupport support;

	protected ObjectObserver(ObjectObserverBuilder<T> builder) {
		this.target = builder.target;
		this.observableMethods = builder.observableMethods;
		this.methodInvokedCallbacks = builder.methodInvokedCallbacks;
		this.observableProperties = builder.observableProperties;
		this.support = new PropertyChangeSupport(this.target);
	}

	@SuppressWarnings("unchecked")
	public <V> void changeState(String propertyName, V value) {
		if (!this.observableProperties.containsKey(propertyName)) {
			throw new IllegalArgumentException();
		}
		PropertyDescriptor<T, V> observableProperty = (PropertyDescriptor<T, V>) this.observableProperties.get(propertyName);
		V oldValue = observableProperty.getValue();
		observableProperty.setValue(value);
		V newValue = observableProperty.getValue();

		this.support.firePropertyChange(new PropertyChangeEvent(target, propertyName, oldValue, newValue));
	}

	@SuppressWarnings("unchecked")
	public <V> V invokeMethod(String methodName, Object ... args) {
		if (!this.observableMethods.containsKey(methodName)) {
			throw new IllegalArgumentException();
		}
		MethodDescriptor<T> observableProperty = this.observableMethods.get(methodName);
		V invokeResult = observableProperty.invoke(args);

		MethodInvokedCallback<T, V> callback = (MethodInvokedCallback<T, V>) this.methodInvokedCallbacks.get(methodName);
		PropertyChangeEvent propertyChangeEvent = callback.methodInvoked(target, invokeResult, args);

		this.support.firePropertyChange(propertyChangeEvent);

		return invokeResult;
	}

	public void subscribeToMethod(String methodName, PropertyChangeListener listener) {
		if (!this.observableMethods.containsKey(methodName)) {
			throw new IllegalArgumentException();
		}
		support.addPropertyChangeListener(methodName, listener);
	}

	public void subscribeToProperty(String propertyName, PropertyChangeListener listener) {
		if (!this.observableProperties.containsKey(propertyName)) {
			throw new IllegalArgumentException();
		}
		support.addPropertyChangeListener(propertyName, listener);
	}

	public void unsubscribeFromMethod(String methodName, PropertyChangeListener listener) {
		if (!this.observableMethods.containsKey(methodName)) {
			throw new IllegalArgumentException();
		}
		support.removePropertyChangeListener(methodName, listener);
	}

	public void unsubscribeFromProperty(String propertyName, PropertyChangeListener listener) {
		if (!this.observableProperties.containsKey(propertyName)) {
			throw new IllegalArgumentException();
		}
		support.removePropertyChangeListener(propertyName, listener);
	}

	public static <T> ObjectObserverBuilder<T> builder() {
		return new ObjectObserverBuilder<>();
	}

	public static class ObjectObserverBuilder<E> {
		private E target;
		private Map<String, MethodDescriptor<E>> observableMethods = new HashMap<>();
		private Map<String, MethodInvokedCallback<E, ?>> methodInvokedCallbacks = new HashMap<>();
		private Map<String, PropertyDescriptor<E, ?>> observableProperties = new HashMap<>();

		protected ObjectObserverBuilder() {}

		public ObjectObserverBuilder<E> target(E target) {
			this.target = target;
			return this;
		}

		public ObjectObserverBuilder<E> addProperty(PropertyDescriptor<E, ?> property) {
			this.observableProperties.put(property.name, property);
			return this;
		}

		public ObjectObserverBuilder<E> addMethod(MethodDescriptor<E> method, MethodInvokedCallback<E, ?> callback) {
			this.observableMethods.put(method.name, method);
			this.methodInvokedCallbacks.put(method.name, callback);
			return this;
		}

		public ObjectObserver<E> build() {
			return new ObjectObserver<>(this);
		}
	}
}
