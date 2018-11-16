package introspection.observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ObservableFeature<T> {
	protected final String name;
	protected final T target;
	protected final PropertyChangeSupport support;

	public ObservableFeature(String name, T target) {
		this.name = name;
		this.target = target;
		this.support = new PropertyChangeSupport(target);
	}

	public String getName() {
		return name;
	}

	public T getTarget() {
		return target;
	}

	public void subscribeTo(PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(this.name, listener);
	}

	public void unsubscribeFrom(PropertyChangeListener listener) {
		this.support.removePropertyChangeListener(this.name, listener);
	}
}
