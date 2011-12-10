package main.model;

import java.util.Map;

/**
 * Factory for entries in the database.
 */
public abstract class EntityInterface<T extends ModelEntity<T>> {
	
	/**
	 * Creates a new instance of an entry with an associated id.
	 * @param id  The id to associate.
	 * @param entity  The entity to copy.
	 * @return  A new entry with an associated id.
	 */
	abstract protected T factory(int id, T entity);
	
	/**
	 * Creates a new instance of an entity with the associated fields.
	 * @param fields  A map over the fields the entity must contain.
	 * @return  A new entity with the given values.
	 * @throws IllegalArgumentException  If the fields doesn't contain the correct values.
	 */
	//abstract protected T factory(Map<String, String> fields) throws IllegalArgumentException;
	
}
