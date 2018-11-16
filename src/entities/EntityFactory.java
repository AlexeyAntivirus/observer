package entities;

import java.beans.PropertyChangeListener;

public interface EntityFactory {
 
    /**
     * Create empty NetworkElement instance by full class-name.
     *
     * @param className - class-name of target object
     * @return - empty NetworkElement instance
     * @throws IllegalArgumentException - if class with such full name not found or instance of this class can not be created
     */
    NetworkElement createEmptyNetworkElementImpl(String className) throws IllegalArgumentException;
 
    /**
     * Create empty NetworkElement instance by full class-object.
     *
     * @param clazz - class-object of target object
     * @return - empty NetworkElement instance
     * @throws IllegalArgumentException - if instance of this class can not be created
     */
    <T extends NetworkElement> T createEmptyNetworkElementImpl(Class<T> clazz) throws IllegalArgumentException;
 
    /**
     * Create empty Reck instance by simple name of class with constructors arguments.
     *
     * @param name - simple name of Rack implementation class
     * @param size - size of new Rack implementation
     * @param limitation - class-object of rack limitation on new Rack implementation
     * @param <T> - rack limitation of new Rack implementation
     * @return - empty Rack instance
     * @throws IllegalArgumentException - if instance of this class can not be created
     */
    <T extends Device> Rack<T> createEmptyRackImpl(String name, int size, Class<T> limitation) throws IllegalArgumentException;
 
    /**
     * Get original network element wrapped on immutable wrapper.
     *
     * @param original - network element witch will be wrapped
     * @param <T> - type of network element
     * @return - wrapped original
     * @throws IllegalArgumentException - if original network element null
     */
    <T extends NetworkElement> T getImmutableNetworkElement(T original) throws IllegalArgumentException;
 
    /**
     * Get original rack wrapped on immutable wrapper.
     *
     * @param original - rack witch will be wrapped
     * @param <D> - type of devices at rack
     * @return - wrapped original
     * @throws IllegalArgumentException - if original rack null
     */
    <D extends Device> Rack<D> getImmutableRack(Rack<D> original) throws IllegalArgumentException;
 
    /**
     * Get original network element wrapped on publisher wrapper and/or add listener to publisher subscribe
     *
     * @param original - network element witch will be wrapped
     * @param listener - link to subscriber
     * @param <T> - type of network element
     * @return - wrapped original
     * @throws IllegalArgumentException - if original network element or listener null
     */
    <T extends NetworkElement> T subscribeTo(T original, PropertyChangeListener listener) throws IllegalArgumentException;
 
    /**
     * Get original rack wrapped on publisher wrapper and/or add listener to publisher subscribe
     *
     * @param original - rack witch will be wrapped
     * @param listener - link to subscriber
     * @param <D> - type of devices at rack
     * @return - wrapped original
     * @throws IllegalArgumentException - if original rack or listener null
     */
    <D extends Device> Rack<D> subscribeTo(Rack<D> original, PropertyChangeListener listener) throws IllegalArgumentException;
 
    /**
     * Unsubscribe listener from subscribing to network element
     *
     * @param publisher - network element publisher
     * @param listener - link to subscriber
     * @return - true if listener was unsubscribed from network element successfully
     *         - false if network element was not a publisher or listener was not unsubscribed to publisher
     * @throws IllegalArgumentException - if publisher or listener null
     */
    boolean unsubscribeFrom(NetworkElement publisher, PropertyChangeListener listener) throws IllegalArgumentException;
 
    /**
     * Unsubscribe listener from subscribing to rack
     *
     * @param publisher - rack publisher
     * @param listener - link to subscriber
     * @return - true if listener was unsubscribed from rack successfully
     *         - false if rack was not a publisher or listener was not unsubscribed to publisher
     * @throws IllegalArgumentException - if publisher or listener null
     */
    boolean unsubscribeFrom(Rack publisher, PropertyChangeListener listener) throws IllegalArgumentException;
 
}