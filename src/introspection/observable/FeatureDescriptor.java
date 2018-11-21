package introspection.observable;

public abstract class FeatureDescriptor<T> {
	protected final String name;
	protected final T target;

	public FeatureDescriptor(String name, T target) {
		this.name = name;
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public T getTarget() {
		return target;
	}
}
