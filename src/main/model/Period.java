package main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.util.Log;

/**
 * A period of time represented by a start and end time.
 */
public class Period extends ModelEntity<Period> {

	/**
	 * The id of the period.
	 */
	public final int id;
	
	/**
	 * The start time of the period.
	 */
	public Date start;
	
	/**
	 * The end time of the period.
	 */
	public Date end;
	
	/**
	 * The fields of the period entity.
	 */
	private Map<String, String> fields;
	
	/**
	 * Constructs a period with a given id.
	 */
	private Period(int id, Date start, Date end) {
		this.id    = id;
		this.start = start;
		this.end   = end;
		
		fields = new HashMap<String, String>();
		fields.put("id", id + "");
		fields.put("start", start.toString());
		fields.put("end", end.toString());
	}
	
	/**
	 * Constructs a period with a given start and end time.
	 * @param start  The time the period start.
	 * @param end  The time the period end.
	 */
	public Period(Date start, Date end) {
		this.id    = 0; // Signal that the entity hasn't been saved
		this.start = start;
		this.end   = end;
		
		fields = new HashMap<String, String>();
		fields.put("start", start.toString());
		fields.put("end", end.toString());
	}

	protected Period factory(int id, Period entity) {
		return new Period(id, entity.start, entity.end);
	}
	
	public Map<String, String> getFields() {
		return fields;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * Calculates the length between the period in days.
	 */
	public int getLengthInDays() {
																									//  ms     sec  min  hours
		return (int) (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
	}

	public String getTable() {
		return "period";
	}
	
	/**
	 * Fetches a single period from a given id, if it exists.
	 * @param entryId  The id of the period.
	 * @return  The period if it was found, otherwise null.
	 */
	public static Period getWhereId(int entryId) {
		ResultSet result = ModelEntity.model.get("period", entryId);
		// Examine if the result has any data
		if (getFirstRowInResultSet(result)) {
			try {
				int id 	   = result.getInt(1);
				long start = result.getLong(2);
				long end   = result.getLong(3);
				// Return
				return new Period(id, new Date(start), new Date(end));
			} catch (SQLException e) {
				Log.error("Unable to retrieve data from result: " + e);
			}
		} else {
			Log.info("Query for Period returned empty.");
		}

		// If nothing is found, return null.
		return null;
	}
	
	/**
	 * Examines whether the given date is included in the period, i. e. whether start <= date <= end.
	 * @param date  The date to test.
	 * @return  A boolean value signalling whether the given date is included or not.
	 */
	public boolean isIncluded(Date date) {
		return (start.getTime() <= date.getTime() && date.getTime() <= end.getTime());
	}
	

}
