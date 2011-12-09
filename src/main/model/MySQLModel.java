package main.model;

import main.util.Log;

import java.sql.*;
import java.util.Map;

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
			
			// Initialize connection with database, username and password
			connection = DriverManager.getConnection("jdbc:mysql://mysql.itu.dk/jegp_bgpp", "jegp_bgpp", "ldo8tf6o");
			
			// Retrieve statement
			statement = connection.createStatement();
			
			
			
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

	public ResultSet get(String table, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet get(String table, int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public int save(String table, Map<String, String> fields) {
		// Check that the entity exists.
		String sql = "SELECT COUNT(*) from " + table + " WHERE ";
		
		// Iterate through the fields to add the condition to the statement.
		int n = 1;
		for (Map.Entry<String, String> e : fields.entrySet()) {
			sql += e.getKey() + " = '" + e.getValue() + "' ";
			if (n < fields.size())
				sql += "AND ";
			n++;
		}
		
		// Execute the query and examine the result.
		try {
			statement.execute(sql);
			ResultSet res = statement.getResultSet();
			if (res != null && res.next()) {
				int id = res.getInt(1);
				if (id != 0) return id;
			}
		} catch (SQLException e){
			Log.error("Unable to perform select query from " + table + "." + e);
		}
		
		// If nothing exists in the database then store the information.
		sql = "INSERT INTO " + table + " ";
		String columns = "(";
		String values  = " VALUES (";
		
		n = 1;
		for (Map.Entry<String, String> e : fields.entrySet()) {
			columns += e.getKey();
			values += "'" + e.getValue() + "'";
			if (n < fields.size()) {
				columns += ", ";
				values  += ", ";
			}
			n++;
		}
		
		// Finish the query
		columns += ")"; 
		values += ")";
		sql += columns + " " + values;
		
		// Execute insert statement.
		try {
			statement.executeUpdate(sql);
			
			// Retrieve the latest key and return it
			ResultSet res = statement.getGeneratedKeys();
			if (res != null && res.next())
				return res.getInt(1);
		} catch (SQLException e) {
			Log.error("Unable to insert entity in " + table + ".");
		}
		
		// If nothing has been returned so far we're unsuccessful. Return 0.
		return 0;
	}

	public ResultSet update(String table, int id, Map<String, String> fields) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
