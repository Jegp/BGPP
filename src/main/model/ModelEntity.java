package main.model;

import java.sql.ResultSet;
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
