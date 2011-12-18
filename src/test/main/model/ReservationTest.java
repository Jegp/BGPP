package test.main.model;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import main.model.*;

import org.junit.*;

public class ReservationTest extends Reservation {

	private Customer customer;
	private Vehicle vehicle;
	private VehicleClass vehicleClass;
	private Period period;
	private Reservation reservation; 
	private Reservation reservationWithId; 

	private long now = System.currentTimeMillis();
	private long day = 1000 * 60 * 60 * 24;
	
	// Dummy
	public ReservationTest() {
	  super(null, null, null);
  }
	
	/**
	 * Set up fixtures.
	 */
	@Before public void setUp() throws Exception {
		customer 			= new Customer("Anders", "Sand", "anders@sand.net", "+45 93748263", "Paradisæblevej 111, 0000 Andeby, Danmark");
		vehicleClass 	= new VehicleClass("Sports car");
		vehicle				= new Vehicle("A nice car", "Renault", "Mégane", vehicleClass);
		period				= new Period(new Date(now), new Date(now + day));
		
		// Make the factory methods public in customer, vehicleClass and period
		Method customerFactory = Customer.class.getDeclaredMethod("factory", int.class, Customer.class);
		Method vehicleFactory  = Vehicle.class.getDeclaredMethod("factory", int.class, Vehicle.class);
		Method periodFactory   = Period.class.getDeclaredMethod("factory", int.class, Period.class);
		customerFactory.setAccessible(true);
		vehicleFactory.setAccessible(true);
		periodFactory.setAccessible(true);
		
		// Initialize them all with id's
		customer = (Customer) customerFactory.invoke(customer, 60, customer);
		vehicle  = (Vehicle) vehicleFactory.invoke(vehicle, 99, vehicle);
		period   = (Period) periodFactory.invoke(period, 87, period);
		
		reservation 			= new Reservation(customer, period, vehicle);
		reservationWithId = factory(42, reservation);
	}
	
	/**
	 * Test factory method.
	 */
	@Test public void testFactory() {
		assertEquals("Unable to construct reservation with id", 42, reservationWithId.id);
	}
	
	/**
	 * Test the field generated for the reservation.
	 */
	@Test public void testFields() {
		HashMap<String, String> fields = reservationWithId.getFields();
		
		assertTrue("Unable to store correct values in the reservation field",
				fields.get("id").equals(42 + "") &&
				fields.get("customer").equals(60 + "") &&
				fields.get("vehicle").equals(99 + "") &&
				fields.get("period").equals(87 + ""));
  }
	
}
