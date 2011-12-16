package test.main.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import main.model.MySQLModel;

import org.junit.Before;
import org.junit.Test;

/**
 * The the MySQL implementation of the model.
 */
public class MySQLModelTest extends MySQLModel {
	
	// Define the table and the query variable to use.
	String table = "table";
	String query = "";
	
	// Set up conditions and join statements
	String condition	= "field = 'value'";
	String selector		= "field1, field2";
	String field		= "field";
	String join 		= "otherTable ON field3 = 'value'";
	
	// Set up hash map
	HashMap<String, String> fields;
	
	/**
	 * Dummy constructor.
	 */
	public MySQLModelTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		fields = new HashMap<String, String>();
		fields.put("field", "value");
		fields.put("anotherField", "yetAnotherValue");
	}

	@Test
	// Test query from selector and condition
	public void testQueryBuilderWithSelector() {
		query = buildSelectQuery(table, condition, selector, "");
		assertEquals("Unable to build queries from a condition with a given selector", 
					 "SELECT field1, field2 FROM table WHERE field = 'value'", query);
	}
	
	@Test
	// Test query from selector, condition and join statement
	public void testQueryBuilderWithConditionAndJoin() {
		query = buildSelectQuery(table, condition, selector, join);
		assertEquals("Unable to build queries from a condition, selector and join statement", 
					 "SELECT field1, field2 FROM table JOIN otherTable ON field3 = 'value' WHERE field = 'value'", query);
	}
		
	@Test
	// Test query from hash map
	public void testQueryBuilderWithHashMap() {
		query = buildSelectQuery(table, fields, "*", "");
		assertEquals("Unable to build queries from a HashMap",
					 "SELECT * FROM table WHERE field LIKE 'value' AND anotherField LIKE 'yetAnotherValue'", query);
	}
	
	@Test
	// Test query from a hash map with join and selectors
	public void testQueryBuilderWithHashMapAndJoin() {
		query = buildSelectQuery(table, fields, selector, join);
		assertEquals("Unable to build queries from a HashMap and with JOIN statements", 
					 "SELECT field1, field2 FROM table JOIN otherTable ON field3 = 'value' WHERE field LIKE 'value' AND anotherField LIKE 'yetAnotherValue'", query);
		
	}

}
