package main.model;

import java.util.HashMap;

/**
 * A vehicle in the booking-system.
 */
public class Vehicle extends ModelEntity<Vehicle> {

	/**
	 * the specific vehicles id in the database
	 */
	public final int id;
	
	/**
	 * the specific vehicles description. 
	 */
	public final String description;
	
	/**
	 * the specific vehicles manufacturer.
	 */
	public final String manufacturer;
	
	/**
	 * the specific vehicles model.
	 */
	public final String model;
	
	/**
	 * the vehicles vehicle class.
	 */
	public final VehicleClass vehicleClass;
	
	private final HashMap<String, String> fields;
	
	/**
	 * Creates a new Vehicle with id.
	 */
	protected Vehicle create(int id, Vehicle entry) {
		return new Vehicle(id, entry.description, entry.manufacturer, entry.model, entry.vehicleClass);
	}
	
	/**
	 * @return  The fields of the vehicle instance.
	 */
	public HashMap<String, String> getFields() {
		return fields;
	}
	
	/**
	 * @return  The name of the SQL table associated with vehicles.
	 */
	public String getSQLTable() {
		return "vehicle";
	}
	
	/**
	 * Creates a new vehicle with a given id.
	 */
	private Vehicle(int id, String description, String manufacturer, String model, VehicleClass vehicleClass) {
		this.id 			= id;
		this.description 	= description;
		this.manufacturer	= manufacturer;
		this.model 			= model;
		this.vehicleClass	= vehicleClass;
		
		fields = new HashMap<String, String>();
		fields.put("id", id + "");
		fields.put("description", description);
		fields.put("manufacturer", manufacturer);
		fields.put("model", model);
		fields.put("vehicleClass", vehicleClass.id + "");
	}
	
	/**
	 * Creates a new public vehicle that hasn't been saved in the database yet.
	 */
	public Vehicle(String description, String manufacturer, String model, VehicleClass vehicleClass) {
		this.id 			= 0;
		this.description 	= description;
		this.manufacturer	= manufacturer;
		this.model 			= model;
		this.vehicleClass	= vehicleClass;
		
		fields = new HashMap<String, String>();
		fields.put("description", description);
		fields.put("manufacturer", manufacturer);
		fields.put("model", model);
		fields.put("vehicleClass", vehicleClass.id + "");
	}
	
}
