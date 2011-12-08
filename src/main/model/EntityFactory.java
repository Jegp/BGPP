package main.model;

/**
 * Factory for entries in the database.
 */
public abstract class EntityFactory<T extends ModelEntity<T>> {
	
	/**
	 * Creates a new instance of an entry with an associated id.
	 * @param id  The id to associate.
	 * @param entity  The entity to copy.
	 * @return  A new entry with an associated id.
	 */
	abstract protected T create(int id, T entity);

}
