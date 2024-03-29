package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.util.Log;

/**
 * A reservation represented by a customer, vehicle and period.
 */
public class Reservation extends ModelEntity<Reservation> {
	
	/**
	 * the reservations user.
	 */
	public final Customer customer;
	
	/**
	 * The fields of the reservation.
	 */
	private final HashMap<String, String> fields = new HashMap<String, String>();

	/**
	 * the reservations ID. 
	 */
	public final int id;
	
	/**
	 * The time-period of the reservation.
	 */
	public final Period period;
	
	/**
	 * The vehicle that is reserved.
	 */
	public final Vehicle vehicle;
	
	/**
	 * The table associated with the reservation entity.
	 */
	public final static String table = "reservation";
	
	/**
	 * Instantiates a reservation with a given id.
	 */
	private Reservation(int id, Customer customer, Period period, Vehicle vehicle) {
		this.id 	  = id;
		this.customer = customer;
		this.period	  = period;
		this.vehicle  = vehicle;
		
		setFields(customer, period, vehicle, id);
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
		
		setFields(customer, period, vehicle, 0);		
	}
	
	/**
	 * Creates a Reservation with a given id.
	 */
	protected Reservation factory(int id, Reservation entry) {
		return new Reservation(id, entry.customer, entry.period, entry.vehicle);
	}
	
	/**
	 * Set the fields to their respective values.
	 */
	private void setFields(Customer customer, Period period, Vehicle vehicle, int id) {
		if (customer != null) fields.put("customer", customer.id + "");
		if (period   != null) fields.put("period", period.id + "");
		if (vehicle  != null) fields.put("vehicle", vehicle.id + "");
		if (id       != 0)    fields.put("id", id + "");
	}
	
	/**
	 * Gets the fields of the current reservation.
	 */
	public HashMap<String, String> getFields() {
		return fields;
	}
	
	/**
	 * Fetches a number of Reservations from the database, which collide with the given period, i. e.
	 * reservations whose period  
	 * @param fields  The fields (keys) with their expected values.
	 * @return  The entry from the database if it exists, otherwise null.
	 */
	public static Reservation[] getFromPeriod(Period period) {
		// Set the condition
		// Set a query:      |-----------------|
		// Get the situation where the start time and endTime is inside the query
		// Data example:            |----|
		String condition  = "(period.start >= " + period.start.getTime() + " AND period.end <= " + period.end.getTime() + ") " +
		// Get the situation where the start time is before the query, BUT where the end time is after the start of the query
		// Data example: |--------|
							"OR (period.start <= " + period.start.getTime() + " AND period.end >= " + period.start.getTime() + ") " +
		// Get the situation where the end time is after the query, BUT where the start time is before the end time of the query
		// Data example:                  |---------|
							"OR (period.end >= " + period.end.getTime() + " AND period.start <= " + period.end.getTime() + ")";
		
		// Execute the query
		ResultSet result = model.get(table, condition, "period", "reservation.period", "period.id");

		// Examine if the result has any content
		if (getFirstRowInResultSet(result)) {
			// Retrieve the results
			try {
				result.last();
				Reservation[] arr = new Reservation[result.getRow()];
				result.beforeFirst();
				while (result.next()) {
					int id 		= result.getInt(1);
					Customer c  = Customer.getWhereId(result.getInt(2));
					Vehicle v   = Vehicle.getWhereId(result.getInt(3));
					Period p    = new Period(new Date(result.getLong("start")), new Date(result.getLong("end")));
					arr[result.getRow() - 1] = new Reservation(id, c, p, v);
				}
				
				// Return
				return arr;
			} catch (SQLException e) {
				Log.error("Unable to retrieve a reservation from result: " + e);
			}
		} else {
			Log.info("Query for reservations with the given condition returned empty.");
		}
		
		// Return failure
		return null;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * Returns the name of the table for the reservations. 
	 */
	public String getTable() {
		return table;
	}

    /**
	 * Fetches a single Reservation from a given id, if it exists.
	 * @param entryId  The id of the reservation.
	 * @return  The Reservation if it was found, otherwise null.
	 */
	public static Reservation getWhereId(int entryId) {
		ResultSet result = model.get("reservation", entryId);
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
				Log.error("Unable to retrieve a reservation from result: " + e);
			}
		} else {
			Log.info("Query for Reservation returned empty.");
		}

		// If nothing is found return null.
		return null;
	}

	
	/**
	 * Fetches any number of reservations which matches the search
	 * criteria specified by the fields parameter.
	 * @param fields  The fields to search for.
	 */
	public static Reservation[] searchWhere(Map<String, String> fields) {
		// Execute the query
		ResultSet result = model.search(table, fields);
		
		// Examine if the result has any content
		if (getFirstRowInResultSet(result)) {
			// Retrieve the results
			try {
				result.last();
				Reservation[] arr = new Reservation[result.getRow()];
				result.beforeFirst();
				while (result.next()) {
					int id 		= result.getInt(1);
					Customer c  = Customer.getWhereId(result.getInt(2));
					Vehicle v   = Vehicle.getWhereId(result.getInt(3));
					Period p    = Period.getWhereId(result.getInt(4));
					
					// Return
					arr[result.getRow() - 1] = new Reservation(id, c, p, v);
				}
				
				// Return
				return arr;
			} catch (SQLException e) {
				Log.error("Unable to retrieve a reservation from result: " + e);
			}
		} else {
			Log.info("Query for reservations with the given condition returned empty.");
		}
		
		// Return failure
		return null;
	}
	
}
