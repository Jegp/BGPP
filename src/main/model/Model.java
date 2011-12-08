package main.model;

import java.sql.Connection;
import java.sql.Statement;

/**
 * The interface of the Model. Implemented as a singleton available through the 
 * <code>getInstance()</code> method.
 */
public abstract class Model {	
	
	/**
	 * The SQL connection accessible to the model interface.
	 */
	protected Connection conn;
	
	/**
	 * The SQL statement accessible to the model interface.
	 */
	protected Statement statement;
	
	/**
	 * Returns the model as the current implementation of the model.
	 * @return An instance of the model.
	 */
	public static Model getInstance() {
		return MySQLModel.getInstance();
	}
	
}
