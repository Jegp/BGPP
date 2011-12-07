package main.model;

import java.util.HashMap;

/**
 * The interface for a data entries.
 *
 * @param <T>  The type of the entry.
 */
public abstract class ModelEntity<T extends ModelEntity> extends EntryFactory<T> {

	/**
	 * The id of the entry.
	 */
	public int id;
	
	/**
	 * @return The database fields associated with the given model entry.
	 */
	public HashMap<String, String> getFields;
	
	/**
	 * @return The name of the SQL table associated with the model entry.
	 */
	public String getSQLTable;
	
	/**
	 * Saves a generic entry in the database.
	 * @param entry  The entry to store.
	 * @param <T>  The type of the entry.
	 * @return  The stored entry with an id.
	 */
	public static <E extends ModelEntity> E save(E entry) {
		String query = "insert into " + entry.getSQLTable;
		int newId = 10;
		// read all the fields from entry.getFields();
		// "insert into " + table + " values (id = 1, description = "I'm with stupid");
		return (E) entry.create(newId, entry);
	}
	
}
