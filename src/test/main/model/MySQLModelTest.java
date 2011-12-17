package test.main.model;

import java.sql.*;
import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.*;

import main.model.MySQLModel;

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
	
	// Set up Statement and ResultSet
	ResultSet r;
	Statement s;
	
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
		
		// Create a table just for testing with three fields: id, field and anotherField
		s = getStatement();
		s.execute("CREATE TABLE test (" +
				"id INTEGER UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT," +
				"field char(50)," +
				"anotherField char(50));");
		
		// Add dummy value
		s.execute("INSERT INTO test VALUES (1, 'value', 'yetAnotherValue')");
	}
	
	@After
	public void tearDown() throws Exception {
		// Remove table test and all it's entities
		s.execute("DROP TABLE test");
	}
	
	// Test query insertion
	@Test
	public void tesInsertionQuery() {
		query = buildInsertQuery(table, fields);
		assertEquals("Unable to build insert query with the given fields",
				"INSERT INTO table (field, anotherField) VALUES('value', 'yetAnotherValue')", query);
	}

	@Test
	// Test query from selector and condition
	public void testSelectQueryWithSelector() {
		query = buildSelectQuery(table, condition, selector, "");
		assertEquals("Unable to build select query from a condition with a given selector", 
					 "SELECT field1, field2 FROM table WHERE field = 'value'", query);
	}
	
	@Test
	// Test query from selector, condition and join statement
	public void testSelectQueryWithConditionAndJoin() {
		query = buildSelectQuery(table, condition, selector, join);
		assertEquals("Unable to build select query from a condition, selector and join statement", 
					 "SELECT field1, field2 FROM table JOIN otherTable ON field3 = 'value' WHERE field = 'value'", query);
	}
		
	@Test
	// Test query from hash map
	public void testSelectQueryWithHashMap() {
		query = buildSelectQuery(table, fields, "*", "");
		assertEquals("Unable to build select query from a HashMap",
					 "SELECT * FROM table WHERE field LIKE 'value' AND anotherField LIKE 'yetAnotherValue'", query);
	}
	
	@Test
	// Test query from a hash map with join and selectors
	public void testSelectQueryWithHashMapAndJoin() {
		query = buildSelectQuery(table, fields, selector, join);
		assertEquals("Unable to build select query from a HashMap and with JOIN statements", 
					 "SELECT field1, field2 FROM table JOIN otherTable ON field3 = 'value' WHERE field LIKE 'value' AND anotherField LIKE 'yetAnotherValue'", query);
	}
	
	@Test
	// Test update query
	public void testUpdateQuery() {
		query = buildUpdateQuery(table, 42, fields);
		assertEquals("Unable to build update query with the given fields",
				"UPDATE table SET field = 'value', anotherField = 'yetAnotherValue' WHERE id = '42'", query);
	}
	
	//////////////////////////////////////////////
	// Test database queries
	//////////////////////////////////////////////
	
	@Test
	// Test insertion
	public void testSave() throws Exception {
		int insertedId = save("test", fields);
		
		// examine if the table contains the values
		s.execute("SELECT * FROM test WHERE field = 'value' AND anotherField = 'yetAnotherValue'");
		
		r = s.getResultSet();
		
		// test insertion
		assertTrue("Unable to insert elements", r.next());
		
		// Test values
		assertEquals("Unable to insert the right values", "value", r.getString(2));

		// test inserted Id
		assertEquals("Unable to retrieve inserted id", 1, insertedId);
	}
	
	@Test
	// Test search
	public void testSearch() throws Exception {
		// Define search criteria
		HashMap<String, String> search = new HashMap<String, String>();
		search.put("anotherField", "yetAnotherValue");
		
		r = search("test", search);
		
		// test id
		assertTrue("Unable to search for elements", r.next());
	}
	
	@Test
	// Test update
	public void testUpdate() throws Exception {
		// Define search criteria
		HashMap<String, String> search = new HashMap<String, String>();
		search.put("field", "updatedValue");
		
		boolean success = update("test", 1, search);
		
		// Examine if the values are correct
		s.execute("SELECT COUNT(*) FROM test WHERE field = 'updatedValue'");
		
		r = s.getResultSet();
		
		// test
		assertTrue("Unable to insert elements", success);
		assertTrue("Unable to update values", r.next());
	}
	
	@Test
	// Test delete
	public void testDelete() throws Exception {
		boolean success = delete("test", 1);
		
		// Examine if element still exists
		s.execute("SELECT COUNT(*) FROM test");
		r = s.getResultSet();
		r.next();
		
		// Test
		assertTrue("Unable to delete entry", success);
		assertEquals("Unable to delete entry", 0, r.getInt(1));
	}

}
