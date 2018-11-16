package entities.impl;

import com.netcracker.edu.location.Location;
import entities.Device;
import entities.Rack;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RackArrayImpl<T extends Device> implements Rack<T> {
	private static final Logger LOGGER =
			Logger.getLogger(RackArrayImpl.class.getName());

	private final Class deviceSubtype;
	private int freeSize;
	private Device[] rack;
	private Location location;

	public RackArrayImpl(int size) {
		this(size, Device.class);
	}

	public RackArrayImpl(int size, Class deviceSubtype) {
		if (Objects.isNull(deviceSubtype) || !Device.class.isAssignableFrom(deviceSubtype)) {
			IllegalArgumentException e = new IllegalArgumentException(
					"entities.Device subtype must be not null and be superclass of entities.Device");
			throwError(e);
		}

		this.deviceSubtype = deviceSubtype;

		if (size <= 0) {
			IllegalArgumentException e = new IllegalArgumentException(
					"Size of rack can not be 0 or less");
			throwError(e);
		}

		this.freeSize = size;
		this.rack = new Device[size];
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int getSize() {
		return rack.length;
	}

	@Override
	public int getFreeSize() {
		return freeSize;
	}

	@Override
	public Class getTypeOfDevices() {
		return deviceSubtype;
	}

	@Override
	public T getDevAtSlot(int index) {
		checkIfIndexOutOfBound(index);

		return (T) rack[index];
	}

	@Override
	public boolean insertDevToSlot(Device device, int index) {
		checkIfIndexOutOfBound(index);
		checkIfIsValidDeviceForInsertToRack(device);
		checkIfDeviceHasInvalidType(device);

		if (rack[index] != null) {
			LOGGER.warning("Slot at index = " + index + " is full!");

			return false;
		}

		freeSize--;
		rack[index] = device;

		return true;
	}

	@Override
	public T removeDevFromSlot(int index) {
		checkIfIndexOutOfBound(index);

		Device device = rack[index];

		if (device == null) {
			LOGGER.warning("Can not remove from empty slot " + index);

			return null;
		}

		rack[index] = null;
		freeSize++;

		return (T) device;
	}

	@Override
	public T getDevByIN(int in) {
		for (Device device : rack) {
			if (device != null && device.getIn() == in) {
				return (T) device;
			}
		}

		return null;
	}

	@Override
	public T[] getAllDeviceAsArray() {
		Device[] buffer = new Device[rack.length - freeSize];

		int index = 0;
		for (Device device: rack) {
			if (device != null) {
				buffer[index++] = device;
			}
		}

		return (T[]) buffer;
	}

	private void checkIfIndexOutOfBound(int index) {
		if (index < 0 || index >= rack.length) {
			String message = "Actual index = " + index + " is invalid. "
					+ "Correct index is between 0 and " + (rack.length - 1);
			IndexOutOfBoundsException e =
					new IndexOutOfBoundsException(message);
			throwError(e);
		}
	}

	private void checkIfIsValidDeviceForInsertToRack(Device device) {

	}

	private void checkIfDeviceHasInvalidType(Device device) {
		if (!this.deviceSubtype.isAssignableFrom(device.getClass())) {
			IllegalArgumentException e = new IllegalArgumentException("Actual type: " + device.getClass().getName()
					+ ", Expected type: " + deviceSubtype.getName());
			throwError(e);
		}
	}

	private void throwError(RuntimeException e) {
		LOGGER.log(Level.SEVERE, e.getMessage(), e);
		throw e;
	}
}