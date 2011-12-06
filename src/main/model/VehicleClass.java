package main.model;

/**
 * A representation of a certain type of vehicles e. g. VW or bicycle.
 */
public class VehicleClass {
	
	public final int id;
	public final String description;
	
	public VehicleClass(int id, String description) {
		this.id 		 = id;
		this.description = description;
	}

}
