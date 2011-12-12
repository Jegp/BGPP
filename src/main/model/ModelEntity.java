package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.util.Log;

/**
 * The interface for a data entities.
 *
 * @param <T>  The type of the entity.
 */
public abstract class ModelEntity<T extends ModelEntity<T>> extends EntityInterface<T> {
	
	/**
	 * The ModelEntity's access to the model.
	 */
	protected static Model model = Model.getInstance();
	
	/**
	 * @return The database fields associated with the given model entity.
	 */
	abstract public Map<String, String> getFields();
	
	/**
	 * Returns the id of the entity.
	 */
	abstract public int getId();
	
	/**
	 * A helper function to examine whether a given result set contains any
	 * rows. Also moves the cursor to the first line of the result set.
	 * @return A boolean value signaling either success or failure.
	 */
	protected static boolean getFirstRowInResultSet(ResultSet res) {
		try {
			return (res != null && res.next());
		} catch (SQLException e) {
			Log.error("Error in retrieving result: " + e);
			return false;
		}
	}
	
	/**
	 * @return The name of the SQL table associated with the model entity.
	 */
	abstract public String getTable();
	
	/**
	 * Saves a generic entity in the database.
	 * @param entity  The entity to store.
	 * @param <E>  The type of the entity.
	 * @return  The stored entity with an id.
	 */
	public static <E extends ModelEntity<E>> E save(E entity) {
		int newId = model.save(entity.getTable(), entity.getFields());
		return (E) entity.factory(newId, entity);
	}
	
}
