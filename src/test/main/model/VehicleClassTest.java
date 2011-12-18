package test.main.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import main.model.*;

import org.junit.*;

/**
 * Tests the vehicle class.
 */
public class VehicleClassTest extends VehicleClass {

	private VehicleClass vehicleClass;
	private VehicleClass vehicleClassWithId;
	
	// Dummy
	public VehicleClassTest() {
	  super(null);
  }
	
	/**
	 * Set up fixtures.
	 */
	@Before public void setUp() {
		vehicleClass 			 = new VehicleClass("Sports car");
		vehicleClassWithId = factory(26, new VehicleClass("Station car"));
	}
	
	/**
	 * Test the factory method.
	 */
	@Test public void testFactoryIntVehicleClass() {
		VehicleClass newVehicleClass = factory(42, vehicleClass); 
		
		assertEquals("Unable to associate a new id to an existing vehicle class", 42, newVehicleClass.id);
	}

	/**
	 * Test the generated fields.
	 */
	@Test public void testGetFields() {
		HashMap<String, String> fields = vehicleClassWithId.getFields();
		
		assertTrue("Unable to store correct values in the vehicle class field",
				fields.get("id").equals(26 + "") &&
				fields.get("description").equals("Station car"));
	}

}
