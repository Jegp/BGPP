package main.model;

import java.util.Date;

public class Reservation {

	public final int id;
	public final User user;
	public final Date start;
	public final Date end;
	public final Vehicle vehicle;
	
	public Reservation(int id, User user, Date start, Date end, Vehicle vehicle) {
		this.id 	 = id;
		this.user	 = user;
		this.start	 = start;
		this.end	 = end;
		this.vehicle = vehicle;
	}
	
	public static Reservation update(Reservation before, Reservation after) {
		// Call to Model with SQL magic
		return after;
	}
	
}