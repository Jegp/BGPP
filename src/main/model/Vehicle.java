package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.util.Log;

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
	
	/**
	 * The fields of the current vehicle.
	 */
	private final HashMap<String, String> fields;
	
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
	
	/**
	 * Creates a new Vehicle with id.
	 */
	protected Vehicle factory(int id, Vehicle entry) {
		return new Vehicle(id, entry.description, entry.manufacturer, entry.model, entry.vehicleClass);
	}
	
	public HashMap<String, String> getFields() {
		return fields;
	}

	public int getId() {
		return id;
	}
	
	public String getTable() {
		return "vehicle";
	}
	
	/**
	 * Fetches a single Vehicle from a given id, if it exists.
	 * @param entryId  The id of the vehicle.
	 * @return  The Vehicle if it was found, otherwise null.
	 */
	public static Vehicle getWhereId(int entryId) {
		ResultSet result = ModelEntity.model.get("vehicle", entryId);
		// Examine if the result has any data
		if (getFirstRowInResultSet(result)) {
			try {
				int id 			   			= result.getInt(1);
				String description 			= result.getString(2);
				String manufacturer			= result.getString(3);
				String model 				= result.getString(4);
				VehicleClass vehicleClass	= VehicleClass.getWhereId(result.getInt(5));
				// Return
				return new Vehicle(id, description, manufacturer, model, vehicleClass);
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for Vehicle returned empty.");
		}

		// If nothing is found return null.
		return null;
	}
	
	/**
	 * Fetches a number of Vehicles from the database, which fulfills the 
	 * @param fields  The fields (keys) with their expected values.
	 * @return  The entry from the database if it exists, otherwise null.
	 */
	public static Vehicle[] getWhere(Map<String, String> fields) {
		ResultSet result = ModelEntity.model.get("vehicle", fields);
		// Examine if the result has any content
		if (getFirstRowInResultSet(result)) { 
			// Retrieve the results
			try {
				result.last();
				Vehicle[] arr = new Vehicle[result.getRow()];
				result.beforeFirst();
				
				while (result.next()) {
					int id 			   			= result.getInt(1);
					String description			= result.getString(2);
					String manufacturer			= result.getString(3);
					String model 				= result.getString(4);
					VehicleClass vehicleClass	= VehicleClass.getWhereId(result.getInt(5));
					arr[result.getRow() - 1]    = new Vehicle(id, description, manufacturer, model, vehicleClass);
				}
				
				// Return
				return arr;
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for Vehicles with condition returned empty.");
		}
		
		// Return failure
		return null;
	}
	
}
