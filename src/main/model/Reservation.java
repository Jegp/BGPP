package main.model;

import java.util.Date;
import java.util.HashMap;

public class Reservation extends ModelEntity<Reservation> {

	/**
	 * the reservations ID. 
	 */
	public final int id;
	
	/**
	 * the reservations user.
	 */
	public final Customer user;
	
	/**
	 * the date where the reservation started
	 */
	public final Date start;
	
	/**
	 * the date where the reservation ended.
	 */
	public final Date end;
	
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
	private Reservation(int id, Customer user, Date start, Date end, Vehicle vehicle) {
		this.id 	 = id;
		this.user	 = user;
		this.start	 = start;
		this.end	 = end;
		this.vehicle = vehicle;
		
		fields = new HashMap<String, String>();
		fields.put("id", id + "");
		fields.put("user", user.id + "");
		fields.put("start", start.toString());
		fields.put("end", end.toString());
		fields.put("vehicle", vehicle.id + "");
	}
	
	/**
	 * Creates a public reservation that hasn't been saved in the database yet.
	 * @param user  The user associated with the reservation.
	 * @param start  The start date of the reservation.
	 * @param end  The end date of the reservation.
	 * @param vehicle  The vehicle to be reserved.
	 */
	public Reservation(Customer user, Date start, Date end, Vehicle vehicle) {
		this.id 	 = 0;
		this.user	 = user;
		this.start	 = start;
		this.end	 = end;
		this.vehicle = vehicle;
		
		fields = new HashMap<String, String>();
		fields.put("user", user.id + "");
		fields.put("start", start.toString());
		fields.put("end", end.toString());
		fields.put("vehicle", vehicle.id + "");
	}
	
	/**
	 * Creates a Reservation with a given id.
	 */
	protected Reservation create(int id, Reservation entry) {
		return new Reservation(id, entry.user, entry.start, entry.end, entry.vehicle);
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
	
}