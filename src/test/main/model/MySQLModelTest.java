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
	private String table = "table";
	private String query = "";
	
	// Set up conditions and join statements
	private String condition	= "field = 'value'";
	private String selector		= "field1, field2";
	private String field		= "field";
	private String join 		= "otherTable ON field3 = 'value'";
	
	// Set up hash map
	private HashMap<String, String> fields;
	
	// Set up Statement and ResultSet
	private ResultSet r;
	private Statement s;
	
	/**
	 * Dummy constructor.
	 */
	public MySQLModelTest() {
		super();
	}
	
	
	/**
	 * Set up test fixtures.
	 * @throws Exception
	 */
	@Before public void setUp() throws Exception {
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
	
	/**
	 * Tear down fixture, i. e. the test table.
	 * @throws Exception
	 */
	@After public void tearDown() throws Exception {
		// Remove table test and all it's entities
		s.execute("DROP TABLE test");
	}
	
	/**
	 * Test query insertion.
	 */
	@Test public void tesInsertionQuery() {
		query = buildInsertQuery(table, fields);
		assertEquals("Unable to build insert query with the given fields",
				"INSERT INTO table (field, anotherField) VALUES('value', 'yetAnotherValue')", query);
	}

	/**
	 * Test query from selector and condition
	 */
	@Test public void testSelectQueryWithSelector() {
		query = buildSelectQuery(table, condition, selector, "");
		assertEquals("Unable to build select query from a condition with a given selector", 
					 "SELECT field1, field2 FROM table WHERE field = 'value'", query);
	}
	
	/**
	 * Test query from selector, condition and join statement
	 */
	@Test public void testSelectQueryWithConditionAndJoin() {
		query = buildSelectQuery(table, condition, selector, join);
		assertEquals("Unable to build select query from a condition, selector and join statement", 
					 "SELECT field1, field2 FROM table JOIN otherTable ON field3 = 'value' WHERE field = 'value'", query);
	}
		
	/**
	 * Test query from hash map.
	 */
	@Test public void testSelectQueryWithHashMap() {
		query = buildSelectQuery(table, fields, "*", "");
		assertEquals("Unable to build select query from a HashMap",
					 "SELECT * FROM table WHERE field LIKE 'value' AND anotherField LIKE 'yetAnotherValue'", query);
	}
	
	/**
	 * Test query from a hash map with join and selectors
	 */
	@Test public void testSelectQueryWithHashMapAndJoin() {
		query = buildSelectQuery(table, fields, selector, join);
		assertEquals("Unable to build select query from a HashMap and with JOIN statements", 
					 "SELECT field1, field2 FROM table JOIN otherTable ON field3 = 'value' WHERE field LIKE 'value' AND anotherField LIKE 'yetAnotherValue'", query);
	}
	
	/**
	 * Test update query.
	 */
	@Test public void testUpdateQuery() {
		query = buildUpdateQuery(table, 42, fields);
		assertEquals("Unable to build update query with the given fields",
				"UPDATE table SET field = 'value', anotherField = 'yetAnotherValue' WHERE id = '42'", query);
	}
	
	//////////////////////////////////////////////
	// Test database queries
	//////////////////////////////////////////////
	
	/**
	 * Test insertion of an entry.
	 * @throws Exception
	 */
	@Test public void testSave() throws Exception {
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
	
	/**
	 * Test a search for an entity.
	 * @throws Exception
	 */
	@Test public void testSearch() throws Exception {
		// Define search criteria
		HashMap<String, String> search = new HashMap<String, String>();
		search.put("anotherField", "yetAnotherValue");
		
		r = search("test", search);
		
		// test id
		assertTrue("Unable to search for elements", r.next());
	}
	
	/**
	 * Test an update of an element in the database.
	 * @throws Exception
	 */
	@Test public void testUpdate() throws Exception {
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
	
	/**
	 * Test the deletion of an entry.
	 * @throws Exception
	 */
	@Test public void testDelete() throws Exception {
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
