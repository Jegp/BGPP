package test.main.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import main.model.Period;

import org.junit.Before;
import org.junit.Test;

public class PeriodTest {

	// Setup periods
	Period p1;
	Period p2;
	Period p3;

	// Setup relevant time stuff
	int minute = 1000 * 60;
	int hour   = minute * 60;
	int day    = hour * 24;
	long now   = System.currentTimeMillis();
	
	@Before
	public void setUp() throws Exception {		
		Date d1 = new Date(now);
		Date d2 = new Date(System.currentTimeMillis() + day); // plus one day
		Date d3 = new Date(now - day);
		Date d4 = new Date(0); // 1970 something something
		Date d5 = new Date(Long.MAX_VALUE);
		
		p1 = new Period(d1, d2);
		p2 = new Period(d1, d3);
		p3 = new Period(d4, d5);
	}
	
	@Test
	public void getFields() {
		Map<String, String> fields = p1.getFields();
		
		assertTrue("Fields was not stored correctly",
				fields.get("start").equals(now + "") &&
				fields.get("end").equals(now + day + ""));
	}

	@Test
	public void testGetLengthInDays() {
		int l1 = p1.getLengthInDays();
		int l2 = p2.getLengthInDays();
		
		assertEquals("Could not calculate length properly.", 1, l1);
		assertEquals("Could not calculate length properly.", 0, l2);
	}

	@Test
	public void testIsIncluded() {
		Date d1 = new Date(now);
		Date d2 = new Date(now + 2 * day);
		Date d3 = new Date(now + 1);
		Date d4 = new Date(Long.MAX_VALUE);
		
		assertTrue("Failed to include date", p1.isIncluded(d1));
		assertFalse("Failed to exclude date", p1.isIncluded(d2));
		assertFalse("Failed to exclude date", p2.isIncluded(d3));
		assertTrue("Failed to include date", p3.isIncluded(d3));
	}

}