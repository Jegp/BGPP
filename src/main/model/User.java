package main.model;

public class User {

	public final int id;
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String phone;
	public final String adress;
	
	public User(int id, String firstName, String lastName, String email, String phone, String adress) {
		this.id         = id;
		this.firstName  = firstName;
		this.lastName   = lastName;
		this.email 		= email;
		this.phone 		= phone;
		this.adress		= adress;
	}
	
}