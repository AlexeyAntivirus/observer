package introspection.observable;

import introspection.references.MethodReference;


public class MethodDescriptor<T> extends FeatureDescriptor<T> {

	private MethodReference<T> methodReference;

	public MethodDescriptor(String name,
	                        T target,
	                        MethodReference<T> methodReference) {
		super(name, target);
		this.methodReference = methodReference;
	}

	public MethodReference<T> getMethodReference() {
		return methodReference;
	}

	@SuppressWarnings("unchecked")
	public <V> V invoke(Object... args) {
		return (V) this.methodReference.invoke(target, args);
	}
}
