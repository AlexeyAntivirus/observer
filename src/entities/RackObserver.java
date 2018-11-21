package entities;

import com.netcracker.edu.location.Location;
import introspection.observable.ObjectObserver;

public class RackObserver<E extends Device> extends ObjectObserver<Rack<E>> implements Rack<E> {

	protected RackObserver(ObjectObserverBuilder<Rack<E>> builder) {
		super(builder);
	}

	@Override
	public Location getLocation() {
		return null;
	}

	@Override
	public void setLocation(Location location) {

	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public int getFreeSize() {
		return 0;
	}

	@Override
	public Class getTypeOfDevices() {
		return null;
	}

	@Override
	public E getDevAtSlot(int index) {
		return null;
	}

	@Override
	public boolean insertDevToSlot(E device, int index) {
		return false;
	}

	@Override
	public E removeDevFromSlot(int index) {
		return null;
	}

	@Override
	public E getDevByIN(int in) {
		return null;
	}

	@Override
	public E[] getAllDeviceAsArray() {
		return null;
	}

	public static class RackObserverBuilder<T> extends ObjectObserverBuilder<T> {}
}
