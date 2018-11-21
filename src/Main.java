
import com.netcracker.edu.location.Location;
import entities.Rack;
import entities.impl.Battery;
import entities.impl.RackArrayImpl;
import introspection.observable.ObjectObserver;
import introspection.observable.MethodDescriptor;
import introspection.observable.PropertyDescriptor;

import java.beans.PropertyChangeEvent;


public class Main {
	public static void main(String[] args) {
		Rack<Battery> rack = new RackArrayImpl<>(2, Battery.class);

		MethodDescriptor<Rack<Battery>> insertDevToSlot = new MethodDescriptor<>("insertDevToSlot", rack,
				(target, args1) -> target.insertDevToSlot((Battery) args1[0], (int) args1[1]));

		MethodDescriptor<Rack<Battery>> removeDevFromSlot = new MethodDescriptor<>("removeDevFromSlot", rack,
				(target, args1) -> target.removeDevFromSlot((int) args1[0]));

		PropertyDescriptor<Rack<Battery>, Location> location =
				new PropertyDescriptor<>("location", rack, Rack::getLocation, Rack::setLocation);

		ObjectObserver<Rack<Battery>> rackObjectObserver = ObjectObserver.<Rack<Battery>>builder()
				.target(rack)
				.addMethod(insertDevToSlot, (target, result, args1) ->
						new PropertyChangeEvent(target, "insertDevToSlot", null, args1[0]))
				.addMethod(removeDevFromSlot, (target, result, args1) ->
						new PropertyChangeEvent(target, "removeDevFromSlot", result, null))
				.addProperty(location)
				.build();

		rackObjectObserver.subscribeToMethod("insertDevToSlot", System.out::println);
		rackObjectObserver.subscribeToMethod("removeDevFromSlot", System.out::println);
		rackObjectObserver.subscribeToProperty("location", System.out::println);

		rackObjectObserver.invokeMethod("insertDevToSlot", new Battery(), 0);
		rackObjectObserver.invokeMethod("insertDevToSlot", new Battery(), 1);
		rackObjectObserver.invokeMethod("removeDevFromSlot", 0);
		rackObjectObserver.invokeMethod("removeDevFromSlot", 1);
		rackObjectObserver.changeState("location", new Location() {
			@Override
			public String getFullName() {
				return "LOL";
			}

			@Override
			public String getShortName() {
				return "LOL";
			}
		});


	}
}
