package main.model;

import java.util.HashMap;

/**
 * A representation of a certain type of vehicles e. g. VW or bicycle.
 */
public class VehicleClass extends ModelEntity<VehicleClass> {
	
	public final int id;
	public final String description;
	
	// The hashmap with the field info.
	private final HashMap<String, String> fields;
	
	protected VehicleClass create(int id, VehicleClass entry) {
		return new VehicleClass(id, entry.description);
	}
	
	/**
	 * @return The name of the vehicle class SQL table.
	 */
	public String getSQLTable() { 
		return "vehicle_class"; 
	}
	
	/**
	 * @return  The fields of the entry and their associated values.
	 */
	public HashMap<String, String> getFields() {
		return fields;
	}
	
	/**
	 * Creates a new class of vehicles with the given id.
	 */
	 private VehicleClass(int id, String description) {
	 	this.id 		 = id;
	 	this.description = description;
		
		// Invoke fields
		fields = new HashMap<String, String>();
		fields.put("id", id + "");
		fields.put("description", description);
	 }
	
	/**
	 * Creates a new class of vehicles that hasn't been stored in the database yet.
	 */
	public VehicleClass(String description) {
		// Store the description
		this.id 		 = 0;
		this.description = description;
		
		// Invoke fields
		fields = new HashMap<String, String>();
		fields.put("description", description);
	}
	
}
