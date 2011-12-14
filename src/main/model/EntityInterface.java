package main.model;

import java.util.Map;

/**
 * Factory-interface for model entities.
 */
public abstract class EntityInterface<T extends ModelEntity<T>> {
	
	/**
	 * Creates a new instance of an entry with an associated id.
	 * @param id  The id to associate with the entity.
	 * @param entity  The entity to copy.
	 * @return  A new entry with an associated id.
	 */
	abstract protected T factory(int id, T entity);
	
}
