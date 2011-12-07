package main.model;

import java.util.Date;
import java.util.HashMap;

/**
 * An immutable reservation.
 */
public class Reservation extends ModelEntity<Reservation> {
<<<<<<< HEAD

	/**
	 * the reservations ID. 
	 */
	public final int id;
	
	/**
	 * the reservations user.
	 */
	public final User user;
	
	/**
	 * the date where the reservation started
	 */
	public final Date start;
	
	/**
	 * the date where the reservation ended.
	 */
	public final Date end;
	
	/**
	 * the vehicle that is reservated.
	 */
	public final Vehicle vehicle;
=======
>>>>>>> branch 'master' of git@github.com:Jegp/BGPP.git
	
	// The fields of a reservation table in the database.
	private HashMap<String, String> fields;

	/**
	 * The is of the reservation.
	 */
	public final int id;
	
	/**
	 * A reference to the user owning the reservation.
	 */
	public final User user;
	
	/**
	 * The starting date of the reservation.
	 */
	public final Date start;
	
	/**
	 * The ending date of the reservation.
	 */
	public final Date end;
	
	/**
	 * The vehicle to reserve.
	 */
	public final Vehicle vehicle;
	
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
	
	/**
	 * Creates a Reservation with a given id.
	 */
	protected Reservation create(int id, Reservation entry) {
		return new Reservation(id, entry.user, entry.start, entry.end, entry.vehicle);
	}
	
}
