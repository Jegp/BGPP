package test.main.model;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import main.model.Customer;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the customer class.
 */
public class CustomerTest extends Customer {

	// Dummy constructor
	public CustomerTest() {
		super("", "", "", "", "");
	}

	Customer customer;
	Customer customerWithId;
	
	@Before
	public void setUp() throws Exception {
		customer 		= new Customer("Anders", "Sand", "anders@sand.net", "+45 93748263", "Paradis�blevej 111, 0000 Andeby, Danmark");
		
		Customer temp 	= new Customer("Andr�", "Volk", "andre@volk.de", "+299 257231", "VejAll�en 26, 3900 Nuuk (Godth�b), Gr�nland");
		customerWithId  = factory(625, temp);
	}

	@Test
	public void testGetId() {
		assertEquals("Unable to fetch the correct id", 0, customer.getId());
		assertEquals("Unable to fetch the correct id", 625, customerWithId.getId());
	}

	@Test
	public void testFactoryIntCustomer() {
		Customer newCustomer = factory(720, customer); 
		
		assertEquals("Unable to associate a new it to a customer", 720, newCustomer.id);
	}

	@Test
	public void testGetFields() {
		HashMap<String, String> fields = customerWithId.getFields();

		for (Map.Entry<String, String> e : fields.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
		
		assertTrue("Cannot store the correct values of the fields",
				fields.get("id").equals(625 + "") &&
				fields.get("firstName").equals("Andr�") &&
				fields.get("lastName").equals("Volk") &&
				fields.get("email").equals("andre@volk.de") && 
				fields.get("phone").equals("+299 257231") &&
				fields.get("address").equals("VejAll�en 26, 3900 Nuuk (Godth�b), Gr�nland"));
	}

}