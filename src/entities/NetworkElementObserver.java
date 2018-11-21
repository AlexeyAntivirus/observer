package entities;

import introspection.observable.ObjectObserver;

import java.util.Queue;

public class NetworkElementObserver extends ObjectObserver<NetworkElement> implements NetworkElement {
	private NetworkElementObserver(DeviceObserverBuilder<NetworkElement> builder) {
		super(builder);
	}

	@Override
	public void fillAllFields(Queue<Field> fields) {
		this.target.fillAllFields(fields);
	}

	@Override
	public Queue<Field> getAllFields() {
		return this.target.getAllFields();
	}

	@Override
	public int compareTo(Object o) {
		return this.target.compareTo(o);
	}

	public static class DeviceObserverBuilder<T> extends ObjectObserverBuilder<T> {}
}
