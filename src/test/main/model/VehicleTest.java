package test.main.model;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.junit.*;

import main.model.*;

/**
 * Tests for vehicle.
 */
public class VehicleTest extends Vehicle {

	private VehicleClass vehicleClass;
	private Vehicle	vehicle;
	private Vehicle vehicleWithId;
	
	// Dummy
	public VehicleTest() {
	  super(null, null, null, null);
  }

	/**
	 * Set up fixtures
	 */
	@Before public void setUp() throws Exception {
		// Make access public to construct vehicle classes with id
		Method factory = VehicleClass.class.getDeclaredMethod("factory", int.class, VehicleClass.class);
		factory.setAccessible(true);
		
		vehicleClass = new VehicleClass("5 doors car");
		vehicleClass = (VehicleClass) factory.invoke(vehicleClass, 43, new VehicleClass("5 doors car"));
		vehicle = new Vehicle("A nice car", "Renault", "Mégane", vehicleClass);
		vehicleWithId = factory(42, vehicle);
	}

	/**
	 * Test factory method.
	 */
	@Test public void testFactoryIntVehicle() {
		assertEquals("Cannot construct a vehicle with associated id", 42, vehicleWithId.id);
	}

	/**
	 * Test the fields set for the vehicle
	 */
	@Test public void testGetFields() {
		HashMap<String, String> fields = vehicleWithId.getFields();
		
		assertTrue("Unable to store correct values in the vehicle class field",
				fields.get("id").equals(42 + "") &&
				fields.get("description").equals("A nice car") &&
				fields.get("manufacturer").equals("Renault") &&
				fields.get("model").equals("Mégane") &&
				fields.get("vehicleClass").equals(43 + ""));
	}

}
