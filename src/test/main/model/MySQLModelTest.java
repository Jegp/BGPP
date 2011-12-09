package test.main.model;

import static org.junit.Assert.*;

import main.model.Model;
import main.model.MySQLModel;

import org.junit.Before;
import org.junit.Test;

/**
 * The the MySQL implementation of the model.
 */
public class MySQLModelTest {

	private static MySQLModel model;
	
	@Before
	public void setUp() throws Exception {
		model = MySQLModel.getInstance();
	}

	@Test
	public void testGetInstance() {
		// Test type
		assertTrue("Another", Model.getInstance() instanceof MySQLModel);
		
		// Test equality
	 	assertSame("Test something", model, Model.getInstance()); 
	}

}
