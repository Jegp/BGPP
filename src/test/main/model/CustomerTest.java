package test.main.model;

import static org.junit.Assert.*;

import java.util.HashMap;

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
		customer 		= new Customer("Anders", "Sand", "anders@sand.net", "+45 93748263", "Paradisæblevej 111, 0000 Andeby, Danmark");
		
		Customer temp 	= new Customer("André", "Volk", "andre@volk.de", "+299 257231", "VejAlléen 26, 3900 Nuuk (Godthåb), Grønland");
		customerWithId  = factory(625, temp);
	}

	@Test
	public void testFactoryCustomer() {
		Customer newCustomer = factory(720, customer); 
		
		assertEquals("Unable to associate a new id to an existing customer", 720, newCustomer.id);
	}

	@Test
	public void testGetFields() {
		HashMap<String, String> fields = customerWithId.getFields();

		assertTrue("Cannot store the correct values of the fields",
				fields.get("id").equals(625 + "") &&
				fields.get("firstName").equals("André") &&
				fields.get("lastName").equals("Volk") &&
				fields.get("email").equals("andre@volk.de") && 
				fields.get("phone").equals("+299 257231") &&
				fields.get("address").equals("VejAlléen 26, 3900 Nuuk (Godthåb), Grønland"));
	}

}
