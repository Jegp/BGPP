package main.model;

import java.util.Date;
import java.util.HashMap;

public class Reservation extends ModelEntry<Reservation> {

	public final int id;
	public final User user;
	public final Date start;
	public final Date end;
	public final Vehicle vehicle;
	
	private HashMap<String, String> fields;
	
	/**
	 * Creates a Reservation with a given id.
	 */
	protected Reservation create(int id, Reservation entry) {
		return new Reservation(id, entry.user, entry.start, entry.end, entry.vehicle);
	}
	
	/**
	 * Instantiates a reservation with a given id.
	 */
	private Reservation(int id, User user, Date start, Date end, Vehicle vehicle) {
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
	public Reservation(User user, Date start, Date end, Vehicle vehicle) {
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
	
}