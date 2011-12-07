package main.model;

import main.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * The Model in the booking-system. There can only be one instance of the model which
 * is accessed through the <pre>getInstance()</pre> method.
 */
public class MySQLModel extends Model {

	private static Connection conn;
	private static MySQLModel model;
	private static Statement statement;
	
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
			Log.info("Successfully connected to MySQL database at itu.dk");
		} catch (Exception e) {
			// Print the error in case no driver could be found
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
