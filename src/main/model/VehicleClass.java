package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.util.Log;

/**
 * A representation of a certain type of vehicles e. g. VW or bicycle.
 */
public class VehicleClass extends ModelEntity<VehicleClass> {
	
	// The hashmap with the field info.
	private final HashMap<String, String> fields;
	
	/**
	 * The id of the VehicleClass. 
	 */
	public final int id;
	
	/**
	 * The description of the VehicleClass.
	 */
	public final String description;
	
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
		this.id 		 = 0; // Set the id to 0, since the entity hasn't been stored
		this.description = description;
		
		// Invoke fields
		fields = new HashMap<String, String>();
		fields.put("description", description);
	}
	
	/**
	 * Creates a VehicleClass with an associated id.	
	 */
	protected VehicleClass factory(int id, VehicleClass entity) {
		return new VehicleClass(id, entity.description);
	}
	
	/**
	 * Retrieves all vehicle classes from the database.
	 * @return An array with vehicles.
	 */
	public static VehicleClass[] getAll() {
		ResultSet result = model.get("vehicleClass", "");
		
		return getVehicleClassesFromResultSet(result);
	}
	
	public HashMap<String, String> getFields() {
		return fields;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTable() { 
		return "vehicleClass"; 
	}
	
	/**
	 * Retrievs a number of vehiclesclasses from a given ResultSet.
	 */
	private static VehicleClass[] getVehicleClassesFromResultSet(ResultSet result) {
		// Examine if the result has any content
		if (getFirstRowInResultSet(result)) { 
			// Retrieve the results
			try {
				result.last();
				VehicleClass[] arr = new VehicleClass[result.getRow()];
				result.beforeFirst();
				
				while (result.next()) {
					int id 			   = result.getInt(1);
					String description = result.getString(2);
					arr[result.getRow() - 1] = new VehicleClass(id, description);
				}
				
				// Return
				return arr;
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for VehicleClasses returned empty.");
		}
		
		// Return failure.
		return null;
	}
	
	/**
	 * Fetches an entry from the database, if it exists.
	 * @param entryId  The id of the VehicleClass to fetch.
	 * @return  The entry from the database if it exists, otherwise null.
	 */
	public static VehicleClass getWhereId(int entryId) {
		ResultSet result = model.get("vehicleClass", entryId);
		// Examine if the result has any data
		if (getFirstRowInResultSet(result)) {
			try {
				int id 			   = result.getInt(1);
				String description = result.getString(2);
				// Return
				return new VehicleClass(id, description);
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for VehicleClass returned empty.");
		}

		// If nothing is found return null.
		return null;
	}
	
	/**
	 * Fetches a number of VehicleClasses from the database, which fulfills the 
	 * @param  fields  The fields (keys) with their expected values.
	 * @return  The entry from the database if it exists, otherwise null.
	 */
	public static VehicleClass[] searchWhere(Map<String, String> fields) {
		ResultSet result = model.search("vehicleClass", fields);
		
		return getVehicleClassesFromResultSet(result);
	}
	
}
