package main.model;

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
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk", "jegp_bgpp", "ldo8tf6o");
			statement = conn.createStatement();
			System.out.println("Connection established!");
		} catch (Exception e) {
			System.err.println("Cannot connect to database: " + e);
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
