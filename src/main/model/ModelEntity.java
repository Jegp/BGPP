package main.model;

import java.util.HashMap;

/**
 * The interface for a data entities.
 *
 * @param <T>  The type of the entity.
 */
public abstract class ModelEntity<T extends ModelEntity<T>> extends EntityFactory<T> {

	/**
	 * The id of the entity.
	 */
	public int id;
	
	/**
	 * @return The database fields associated with the given model entity.
	 */
	public HashMap<String, String> getFields;
	
	/**
	 * @return The name of the SQL table associated with the model entity.
	 */
	public String getSQLTable;
	
	/**
	 * Saves a generic entity in the database.
	 * @param entity  The entity to store.
	 * @param <E>  The type of the entity.
	 * @return  The stored entity with an id.
	 */
	public static <E extends ModelEntity<E>> E save(E entity) {
		String query = "insert into " + entity.getSQLTable;
		int newId = 10;
		// read all the fields from entry.getFields();
		// "insert into " + table + " values (id = 1, description = "I'm with stupid");
		return (E) entity.create(newId, entity);
	}
	
}
