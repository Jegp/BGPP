package test.main.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import main.model.Period;

import org.junit.Before;
import org.junit.Test;

public class PeriodTest {

	// Setup periods
	private Period p1;
	private Period p2;
	private Period p3;
	private Period p4;

	// Setup relevant time stuff
	private long minute = 1000 * 60;
	private long hour   = minute * 60;
	private long day    = hour * 24;
	private long now   = System.currentTimeMillis();
	
	/**
	 * Set up fixtures.
	 */
	@Before public void setUp() {		
		Date d1 = new Date(now);
		Date d2 = new Date(System.currentTimeMillis() + day); // plus one day
		Date d3 = new Date(now - day);
		Date d4 = new Date(0); // 1970 something something
		Date d5 = new Date(Long.MAX_VALUE);
		Date d6 = new Date(day * 1000);
		
		p1 = new Period(d1, d2);
		p2 = new Period(d1, d3);
		p3 = new Period(d4, d5);
		p4 = new Period(d4, d6);
	}
	
	/**
	 * Test the fields of the period.
	 */
	@Test public void testFields() {
		Map<String, String> fields = p1.getFields();
		
		assertTrue("Fields was not stored correctly",
				fields.get("start").equals(now + "") &&
				fields.get("end").equals(now + day + ""));
	}

	/**
	 * Test the length of the days.
	 */
	@Test public void testGetLengthInDays() {
		int l1 = p1.getLengthInDays();
		int l2 = p2.getLengthInDays();
		int l3 = p4.getLengthInDays();
		
		assertEquals("Could not calculate length properly.", 1, l1);
		assertEquals("Could not calculate length properly.", 0, l2);
		assertEquals("Could not calculate length properly.", 1000, l3);
	}

	/**
	 * Test the calculation of whether a given date is included in the period.
	 */
	@Test public void testIsIncluded() {
		Date d1 = new Date(now);
		Date d2 = new Date(now + day << 2);
		Date d3 = new Date(now + 1);
		Date d4 = new Date(Long.MAX_VALUE);
		Date d5 = new Date(0);
		
		assertTrue("Failed to include date", p1.isIncluded(d1));
		assertFalse("Failed to exclude date", p1.isIncluded(d2));
		assertFalse("Failed to exclude date", p2.isIncluded(d3));
		assertTrue("Failed to include date", p3.isIncluded(d3));
		assertTrue("Failed to include date", p3.isIncluded(d4));
		assertTrue("Failed to include date", p3.isIncluded(d5));
	}

}
