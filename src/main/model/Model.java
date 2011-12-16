package main.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 * The interface of the Model. Implemented as a singleton available through the 
 * <code>getInstance()</code> method.
 */
public abstract class Model {
	
	/**
	 * Close the connection to the database
	 */
	abstract public void close();
	
	/**
	 * Deletes a single entity by it's id from a given table.
	 * @return  A boolean signalling success of failure.
	 */
	abstract public boolean delete(String table, int id);
	
	/**
	 * Retrieves a number of fields with the given condition.
	 * @param table  The table to perform the query on.
	 * @param condition The condition that must be met for the query.
	 * @return  The ResultSet of the query.
	 */
	abstract public ResultSet get(String table, String condition);
	
	/**
	 * Retrieves a number of fields with the given condition. This function also 
	 * allows the query to join with another table.
	 * 
	 * @param table  The table to perform the query on.
	 * @param condition  The condition the query must meet.
	 * @param joinTable  The table to join in the query.
	 * @param joinKey1  The first key of the join query.
	 * @param joinKey2  The second key of the join query.
	 * @return  The ResultSet of the query.
	 */
	abstract public ResultSet get(String table, String condtion, String joinTable, String joinKey1, String joinKey2);
	
	/**
	 * Retrieves a single field with the given id from a given table.
	 * @param table  The table to perform the query on.
	 * @param id  The id of the entry to search for.
	 * @return  The ResultSet of the query.
	 */
	abstract public ResultSet get(String table, int id);

	/**
	 * Returns the current implementation of the model.
	 * @return An instance of the model.
	 */
	public static Model getInstance() {
		return MySQLModel.getInstance();
	}
	
	/**
	 * Examines whether a connection is available.
	 */
	abstract public boolean isConnected();
	
	/**
	 * Store an entity in the given table with the given values of the fields.
	 * If the entity already exists we simply return the id of the existing entity.
	 * @return  The id of the stored entry. 0 (zero) if unsuccessful.
	 */
	abstract public int save(String table, Map<String, String> fields);

	/**
	 * Searches a table in the database for a number of entries with values as in the given fields.
	 * @param table  The table to perform the query on.
	 * @param fields  The fields (keys) which must resemble the given value (values).
	 * @return  The ResultSet of the query.
	 */
	abstract public ResultSet search(String table, Map<String, String> fields);
	
	/**
	 * Updates a single column in the given table with the given id. The column is 
	 * updated with the given fields. 
	 * @return  True if success false if failure.
	 */
	abstract public boolean update(String table, int id, Map<String, String> fields);
	
}
