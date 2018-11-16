package introspection.observable;


import introspection.references.GetMethodReference;
import introspection.references.SetMethodReference;

import java.beans.PropertyChangeEvent;

public class ObservableProperty<T, V> extends ObservableFeature<T> {
	private final GetMethodReference<T, V> getter;
	private final SetMethodReference<T, V> setter;

	public ObservableProperty(String name,
	                          T target,
	                          GetMethodReference<T, V> getter,
	                          SetMethodReference<T, V> setter) {
		super(name, target);
		this.getter = getter;
		this.setter = setter;
	}

	public GetMethodReference<T, V> getGetter() {
		return getter;
	}

	public SetMethodReference<T, V> getSetter() {
		return setter;
	}

	public V getValue() {
		return this.getter.getValue(target);
	}

	public void setValue(V value) {
		V oldValue = this.getValue();
		this.setter.setValue(target, value);
		V newValue = this.getValue();

		this.support.firePropertyChange(new PropertyChangeEvent(target, name, oldValue, newValue));
	}
}
