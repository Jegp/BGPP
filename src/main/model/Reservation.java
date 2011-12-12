package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.util.Log;

public class Reservation extends ModelEntity<Reservation> {

	/**
	 * the reservations ID. 
	 */
	public final int id;
	
	/**
	 * the reservations user.
	 */
	public final Customer customer;
	
	/**
	 * The time-period of the reservation.
	 */
	public final Period period;
	
	/**
	 * The vehicle that is reserved.
	 */
	public final Vehicle vehicle;
	
	/**
	 * The fields of the reservation.
	 */
	private HashMap<String, String> fields;
	
	/**
	 * Instantiates a reservation with a given id.
	 */
	private Reservation(int id, Customer customer, Period period, Vehicle vehicle) {
		this.id 	  = id;
		this.customer = customer;
		this.period	  = period;
		this.vehicle  = vehicle;
		
		fields = new HashMap<String, String>();
		fields.put("id", id + "");
		fields.put("customer", customer.id + "");
		fields.put("period", period.id + "");
		fields.put("vehicle", vehicle.id + "");
	}
	
	/**
	 * Creates a public reservation that hasn't been saved in the database yet.
	 * @param user  The user associated with the reservation.
	 * @param start  The start date of the reservation.
	 * @param end  The end date of the reservation.
	 * @param vehicle  The vehicle to be reserved.
	 */
	public Reservation(Customer customer, Period period, Vehicle vehicle) {
		this.id 	  = 0;
		this.customer = customer;
		this.period   = period;
		this.vehicle  = vehicle;
		
		fields = new HashMap<String, String>();
		fields.put("customer", customer.id + "");
		fields.put("period", period.id + "");
		fields.put("vehicle", vehicle.id + "");
	}
	
	/**
	 * Creates a Reservation with a given id.
	 */
	protected Reservation factory(int id, Reservation entry) {
		return new Reservation(id, entry.customer, entry.period, entry.vehicle);
	}
	
	/**
	 * Gets the fields of the current reservation.
	 */
	public HashMap<String, String> getFields() {
		return fields;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the table for the reservations. 
	 */
	public String getTable() {
		return "reservation";
	}

    /**
	 * Fetches a single Reservation from a given id, if it exists.
	 * @param entryId  The id of the reservation.
	 * @return  The Reservation if it was found, otherwise null.
	 */
	public static Reservation getWhereId(int entryId) {
		ResultSet result = ModelEntity.model.get("reservation", entryId);
		// Examine if the result has any data
		if (getFirstRowInResultSet(result)) {
			try {
				int id 			  = result.getInt(1);
				Customer customer = Customer.getWhereId(result.getInt(2));
				Period period  	  = Period.getWhereId(result.getInt(3));
				Vehicle vehicle   = Vehicle.getWhereId(result.getInt(4));
				// Return
				return new Reservation(id, customer, period, vehicle);
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for Reservation returned empty.");
		}

		// If nothing is found return null.
		return null;
	}
	
	/**
	 * Fetches a number of Reservation from the database, which fulfills the 
	 * @param fields  The fields (keys) with their expected values.
	 * @return  The entry from the database if it exists, otherwise null.
	 */
	public static Reservation[] getWhere(Map<String, String> fields) {
		ResultSet result = ModelEntity.model.get("customer", fields);
		// Examine if the result has any content
		if (getFirstRowInResultSet(result)) { 
			// Retrieve the results
			try {
				result.last();
				Reservation[] arr = new Reservation[result.getRow()];
				result.beforeFirst();
				
				while (result.next()) {
					int id 			  = result.getInt(1);
					Customer customer = Customer.getWhereId(result.getInt(2));
					Period period  	  = Period.getWhereId(result.getInt(3));
					Vehicle vehicle   = Vehicle.getWhereId(result.getInt(4));
					arr[result.getRow() - 1] = new Reservation(id, customer, period, vehicle);
				}
				
				// Return
				return arr;
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for Reservation with condition returned empty.");
		}
		
		// Return failure
		return null;
	}
	
}