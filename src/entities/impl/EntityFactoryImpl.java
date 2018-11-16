package entities.impl;

import entities.Device;
import entities.EntityFactory;
import entities.NetworkElement;
import entities.Rack;

import java.beans.PropertyChangeListener;

public class EntityFactoryImpl implements EntityFactory {
	@Override
	public NetworkElement createEmptyNetworkElementImpl(String className) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <T extends NetworkElement> T createEmptyNetworkElementImpl(Class<T> clazz) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <T extends Device> Rack<T> createEmptyRackImpl(String name, int size, Class<T> limitation) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <T extends NetworkElement> T getImmutableNetworkElement(T original) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <D extends Device> Rack<D> getImmutableRack(Rack<D> original) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <T extends NetworkElement> T subscribeTo(T original, PropertyChangeListener listener) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <D extends Device> Rack<D> subscribeTo(Rack<D> original, PropertyChangeListener listener) throws IllegalArgumentException {
		return null;
	}

	@Override
	public boolean unsubscribeFrom(NetworkElement publisher, PropertyChangeListener listener) throws IllegalArgumentException {
		return false;
	}

	@Override
	public boolean unsubscribeFrom(Rack publisher, PropertyChangeListener listener) throws IllegalArgumentException {
		return false;
	}
}
