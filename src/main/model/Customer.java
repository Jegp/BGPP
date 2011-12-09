package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.util.Log;

public class Customer extends ModelEntity<Customer> {

	/**
	 * a users ID in the database.
	 */
	public final int id;
	
	/**
	 * a users first name.
	 */
	public final String firstName; 
	
	/**
	 * a users last name.
	 */
	public final String lastName;
	
	/**
	 * a users e-mail adress.
	 */
	public final String email;
	
	/**
	 * a users phone number.
	 */
	public final String phone;
	
	/**
	 * a users address.
	 */
	public final String adress;
	
	/**
	 * The fields of the current user.
	 */
	private HashMap<String, String> fields;
	
	/**
	 * Creates a public customer that hasn't been stored in the database yet.
	 * @param id  The id of the user.
	 * @param firstName  The first name of the user.
	 * @param lastName  The last name of the user.
	 * @param email  The email of the user.
	 * @param phone The phone of the user.
	 * @param adress The adress of the user.
	 */
	public Customer(String firstName, String lastName, String email, String phone, String adress) {
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
		fields.put("address", adress);
	}
	
	/**
	 * Creates a user with a given id.
	 */
	private Customer(int id, String firstName, String lastName, String email, String phone, String adress) {
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
	
	/**
     * Creates a new user with a given id.
     */
    protected Customer factory(int id, Customer entry) {
     	return new Customer(id, entry.firstName, entry.lastName, entry.email, entry.phone, entry.adress);
    }
    
	public int getId() {
		return id;
	}
    
    /**
     * Returns the fields of the current customer.
     */
    public HashMap<String, String> getFields() {
    	return fields;
    }
     
    /** 
     * Returns the name of the table for the User entity.
     */
    public String getTable() {
    	return "customer";
    }
	
}