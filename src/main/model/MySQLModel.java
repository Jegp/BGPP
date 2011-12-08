package main.model;

import main.util.Log;

import java.sql.*;

/**
 * The Model in the booking-system. There can only be one instance of the model which
 * is accessed through the <pre>getInstance()</pre> method.
 */
public class MySQLModel extends Model {
	
	/**
	 * The concrete instance of the MySQLModel.
	 */
	private static MySQLModel model;
	
	/**
	 * Constructs a model and connects to a database.
	 */
	private MySQLModel() {
		try {
			// Retrieve the database driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// Initialize connection
			conn = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk", "jegp_bgpp", "ldo8tf6o");
			
			// Retrieve statement
			statement = conn.createStatement();
			
			// Log the success
			Log.info("Successfully connected to MySQL database at itu.dk");
		} catch (Exception e) {
			// Print the error in case the connection failed
			Log.error("Cannot connect to database: " + e);
		}
	}
	
	/**
	 * Gets an instance of the model. If the model hasn't been used before we  
	 * instantiates a new, otherwise we reuse the old.
	 * @return An instance of the Model.
	 */
	public static MySQLModel getInstance() {
		// Create a new model if none exists
		if (model == null) {
			model = new MySQLModel();
		}
		
		// Return
		return model;
	}
	
}
