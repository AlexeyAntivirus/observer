package introspection.observable;

import introspection.references.GetMethodReference;
import introspection.references.SetMethodReference;


public class PropertyDescriptor<T, V> extends FeatureDescriptor<T> {
	private final GetMethodReference<T, V> getter;
	private final SetMethodReference<T, V> setter;

	public PropertyDescriptor(String name,
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
		this.setter.setValue(target, value);
	}
}
