package main.model;

import java.util.HashMap;

public class User extends ModelEntity<User> {

	public final int id;
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String phone;
	public final String adress;
	
	private HashMap<String, String> fields;
	
	/**
     * Creates a new user with a given id.
     */
     protected User create(int id, User entry) {
     	return new User(id, entry.firstName, entry.lastName, entry.email, entry.phone, entry.adress);
     }
	
	/**
	 * Creates a public User that hasn't been stored in the database yet.
	 * @param id  The id of the user.
	 * @param firstName  The first name of the user.
	 * @param lastName  The last name of the user.
	 * @param email  The email of the user.
	 * @param phone The phone of the user.
	 * @param adress The adress of the user.
	 */
	public User(String firstName, String lastName, String email, String phone, String adress) {
		this.id			= 0;
		this.firstName  = firstName;
		this.lastName   = lastName;
		this.email 		= email;
		this.phone 		= phone;
		this.adress		= adress;
		
		fields = new HashMap<String, String>();
		fields.put("firstName", firstName);
		fields.put("lastName", lastName);
		fields.put("email", email);
		fields.put("phone", phone);
		fields.put("adress", adress);
	}
	
	/**
	 * Creates a user with a given id.
	 */
	private User(int id, String firstName, String lastName, String email, String phone, String adress) {
		this.id         = id;
		this.firstName  = firstName;
		this.lastName   = lastName;
		this.email 		= email;
		this.phone 		= phone;
		this.adress		= adress;
		
		fields = new HashMap<String, String>();
		fields.put("id", id + "");
		fields.put("firstName", firstName);
		fields.put("lastName", lastName);
		fields.put("email", email);
		fields.put("phone", phone);
		fields.put("adress", adress);
	}
	
}