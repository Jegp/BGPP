package main.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 * The interface of the Model. Implemented as a singleton available through the 
 * <code>getInstance()</code> method. The model implements a SQL interface.
 */
public abstract class Model {	
	
	/**
	 * The SQL connection accessible to the model interface.
	 */
	protected Connection connection;
	
	/**
	 * The SQL statement accessible to the model interface.
	 */
	protected Statement statement;
	
	/**
	 * Deletes a single entity by it's id from a given table.
	 * @return  A boolean signaling success of failure.
	 */
	abstract public boolean delete(String table, int id);
	
	/**
	 * Retrieves a number of fields with the given fields from a given table.
	 * @return  The ResultSet of the query.
	 */
	abstract public ResultSet get(String table, Map<String, String> fields);
	
	/**
	 * Retrieves a single field with the given id from a given table.
	 * @return  The ResultSet of the query.
	 */
	abstract public ResultSet get(String table, int id);
	
	/**
	 * Retrieves a number of fields with the given id's from a given table.
	 * @return  The ResultSet of the query.
	 */
	abstract public ResultSet get(String table, int[] ids);
	
	/**
	 * Returns the model as the current implementation of the model.
	 * @return An instance of the model.
	 */
	public static Model getInstance() {
		return MySQLModel.getInstance();
	}
	
	/**
	 * Store an entity in the given table with the given values of the fields.
	 * If the entity already exists we simply return the id of the existing entity.
	 * @return  The id of the stored entry. 0 (zero) if unsuccessful.
	 */
	abstract public int save(String table, Map<String, String> fields);
	
	/**
	 * Updates a single column in the given table with the given id. The column is 
	 * updated with the given fields. 
	 */
	abstract public ResultSet update(String table, int id, Map<String, String> fields);
	
}
