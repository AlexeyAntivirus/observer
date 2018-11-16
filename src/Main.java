
import entities.Device;
import entities.Rack;
import entities.impl.Battery;
import entities.impl.RackArrayImpl;
import introspection.observable.ObjectObserver;
import introspection.observable.ObservableMethod;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;


public class Main {
	public static void main(String[] args) throws IntrospectionException {
		Rack<Battery> rack = new RackArrayImpl<>(2, Battery.class);

		ObservableMethod<Rack<Battery>> insertDevToSlot = new ObservableMethod<>("insertDevToSlot", rack,
				(target, args1) -> target.insertDevToSlot((Battery) args1[0], (int) args1[1]),
				(target, result, args1) -> new PropertyChangeEvent(target, "insertDevToSlot", null, args1[0]));

		insertDevToSlot.subscribeTo(System.out::println);
		ObservableMethod<Rack<Battery>> removeDevFromSlot = new ObservableMethod<>("removeDevFromSlot", rack,
				(target, args1) -> target.removeDevFromSlot((int) args1[0]),
				(target, result, args1) -> new PropertyChangeEvent(target, "removeDevFromSlot", result, null));
		removeDevFromSlot.subscribeTo(System.out::println);

		ObjectObserver<Rack<Battery>> rackObjectObserver = ObjectObserver.<Rack<Battery>>builder()
				.addMethod(insertDevToSlot)
				.addMethod(removeDevFromSlot)
				.build();

//		rack.insertDevToSlot(new Battery(), 0);
//		rack.insertDevToSlot(new Battery(), 1);

		rackObjectObserver.invokeMethod("insertDevToSlot", new Battery(), 0);
		rackObjectObserver.invokeMethod("insertDevToSlot", new Battery(), 1);
		rackObjectObserver.invokeMethod("removeDevFromSlot", 0);
		rackObjectObserver.invokeMethod("removeDevFromSlot", 1);
	}
}
