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
	 * a users e-mail address.
	 */
	public final String email;
	
	/**
	 * a users phone number.
	 */
	public final String phone;
	
	/**
	 * a users address.
	 */
	public final String address;
	
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
	 * @param adress The address of the user.
	 */
	public Customer(String firstName, String lastName, String email, String phone, String address) {
		this.id			= 0; // Set to null to represent an entity that doesn't exist.
		this.firstName  = firstName;
		this.lastName   = lastName;
		this.email 		= email;
		this.phone 		= phone;
		this.address	= address;
		
		fields = new HashMap<String, String>();
		fields.put("firstName", firstName);
		fields.put("lastName", lastName);
		fields.put("email", email);
		fields.put("phone", phone);
		fields.put("address", address);
	}
	
	/**
	 * Creates a user with a given id.
	 */
	private Customer(int id, String firstName, String lastName, String email, String phone, String address) {
		this.id         = id;
		this.firstName  = firstName;
		this.lastName   = lastName;
		this.email 		= email;
		this.phone 		= phone;
		this.address	= address;
		
		fields = new HashMap<String, String>();
		fields.put("id", id + "");
		fields.put("firstName", firstName);
		fields.put("lastName", lastName);
		fields.put("email", email);
		fields.put("phone", phone);
		fields.put("address", address);
	}
	
	/**
   * Creates a new user with a given id.
   */
  protected Customer factory(int id, Customer entry) {
   	return new Customer(id, entry.firstName, entry.lastName, entry.email, entry.phone, entry.address);
  }
  
  /**
   * Creates an array of customers from a given ResultSet.
   * @param result  The ResultSet to extract data from.
   */
  private static Customer[] getCustomersFromResultSet(ResultSet result) {
  	// Examine if the result has any content
 		if (getFirstRowInResultSet(result)) { 
 			// Retrieve the results
 			try {
 				result.last();
 				Customer[] arr = new Customer[result.getRow()];
 				result.beforeFirst();
 				
 				// Loop through the rows
 				while (result.next()) {
 					int id 			 		 = result.getInt(1);
 					String firstName = result.getString(2);
 					String lastName  = result.getString(3);
 					String email 	 	 = result.getString(4);
 					String phone 	 	 = result.getString(5);
 					String address	 = result.getString(6);
 					arr[result.getRow() - 1] = new Customer(id, firstName, lastName, email, phone, address);
 				}
 				
 				// Return
 				return arr;
 			} catch (SQLException e) {
 				Log.error("Unable to retrieve data from result: " + e);
 			}
 		} else {
 			Log.info("Query for Customers with condition returned empty.");
 		}
 		
 		// Return failure.
 		return null;
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
  
  /**
   * Returns an array with all the customers available in the database.
   * @return An array with customers.
   */
  public static Customer[] getAll() {
  	ResultSet result = model.get("customer", "");
  	
  	// Return the result
  	return getCustomersFromResultSet(result);
  }
    
  /**
	 * Fetches a single Customer from a given id, if it exists.
	 * @param entryId  The id of the customer.
	 * @return  The Customer if it was found, otherwise null.
	 */
	public static Customer getWhereId(int entryId) {
		ResultSet result = ModelEntity.model.get("customer", entryId);
		// Examine if the result has any data
		if (getFirstRowInResultSet(result)) {
			try {
				int id 			 = result.getInt(1);
				String firstName = result.getString(2);
				String lastName  = result.getString(3);
				String email 	 = result.getString(4);
				String phone 	 = result.getString(5);
				String address	 = result.getString(6);
				// Return
				return new Customer(id, firstName, lastName, email, phone, address);
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for Customer returned empty.");
		}

		// If nothing is found return null.
		return null;
	}
	
	/**
	 * Fetches a number of Customer from the database, which resembles the given fields.
	 * @param fields  The fields (keys) with their expected values.
	 * @return  The entry from the database if it exists, otherwise null.
	 */
	public static Customer[] searchWhere(Map<String, String> fields) {
		ResultSet result = ModelEntity.model.search("customer", fields);
		
		// Retrieve the array
		return getCustomersFromResultSet(result);
	}
	
}