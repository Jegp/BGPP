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
	 * Deletes an entity from the model.
	 * @param table  The table to delete from.
	 * @return boolean  A flag to signal success or failure.
	 */
	public static boolean delete(String table, int id) {
		// Return if id isn't set.
		if (id != 0)
			// Delete it and see if we're successful
			return model.delete(table, id);
		else
			Log.error("Unable to delete entity without a specified id.");
		
		// Return failure
		return false;
	}
	
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
	
	/**
	 * Updates an entity to the given fields.
	 * @param entity  The entity with the updated fields.
	 * @param id  The id of the entity to store.
	 * @param <E>  The type of the entity.
	 * @return  The entity.
	 */
	public static <E extends ModelEntity<E>> E update(E entity, int id) {
		if (id != 0) {
			// Execute the update query and retrieve the success flag.
			boolean success = model.update(entity.getTable(), id, entity.getFields());
			
			// Log failure
			if (!success)
				Log.warning("Unable to update entity in table " + entity.getTable() + " where id = " + id + ".");
			
			// Return under any circumstances.
			return entity;
		} else {
			Log.warning("Unable to update entity withouth an id. Please store the entity in the database first. Returning unchanges entity.");
			return entity;
		}
	}
}
