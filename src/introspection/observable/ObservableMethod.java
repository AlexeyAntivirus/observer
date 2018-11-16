package introspection.observable;

import introspection.references.MethodReference;

import java.beans.PropertyChangeEvent;

public class ObservableMethod<T> extends ObservableFeature<T> {

	private MethodReference<T> methodReference;
	private MethodInvokedCallback<T, ?> callback;

	public ObservableMethod(String name,
	                        T target,
	                        MethodReference<T> methodReference,
	                        MethodInvokedCallback<T, ?> callback) {
		super(name, target);
		this.methodReference = methodReference;
		this.callback = callback;
	}

	public MethodReference<T> getMethodReference() {
		return methodReference;
	}

	@SuppressWarnings("unchecked")
	public <V> V invoke(Object... args) {
		V invoke = (V) this.methodReference.invoke(target, args);

		PropertyChangeEvent propertyChangeEvent = ((MethodInvokedCallback<T, V>) callback).methodInvoked(target, invoke, args);
		this.support.firePropertyChange(propertyChangeEvent);

		return invoke;
	}
}
