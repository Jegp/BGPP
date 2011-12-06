package main.model;

/**
 * A vehicle in the booking-system.
 */
public class Vehicle {

	public final int id;
	public final String description;
	public final String manufacturer;
	public final String model;
	public final VehicleClass vehicleClass;
	
	Vehicle(int id, String description, String manufacturer, String model, VehicleClass vehicleClass) {
		this.id 			= id;
		this.description 	= description;
		this.manufacturer	= manufacturer;
		this.model 			= model;
		this.vehicleClass	= vehicleClass;
	}
	
}
