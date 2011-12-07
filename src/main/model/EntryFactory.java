package main.model;

/**
 * Factory for entries in the database.
 */
public abstract class EntryFactory<T extends ModelEntity> {
	
	/**
	 * Creates a new instance of an entry with an associated id.
	 * @param id  The id to associate.
	 * @param entry  The entry to copy.
	 * @return  A new entry with an associated id.
	 */
	abstract protected T create(int id, T entry);

}
