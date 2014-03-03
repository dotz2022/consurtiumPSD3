package uk.ac.glasgow.senotes.components.lab.repository.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureMetric;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureRange;

public class TemperatureRangeTest {

	private TemperatureRange range;
	
	@Before
	public void setUp() throws Exception {
		Temperature low  = new Temperature(TemperatureMetric.CELSIUS, -10.0);

		Temperature high = new Temperature(TemperatureMetric.CELSIUS, 50.0);
		
		range = new TemperatureRange(low, high);
		
	}

	@After
	public void tearDown() throws Exception {
		range = null;
	}

	@Test
	public void testContains() {
		
		Temperature withinNeg = new Temperature(TemperatureMetric.CELSIUS, -5.0);
		Temperature withinPos = new Temperature(TemperatureMetric.CELSIUS, 22.0);
		Temperature withoutPos = new Temperature(TemperatureMetric.CELSIUS, 65.0);
		Temperature withoutNeg = new Temperature(TemperatureMetric.CELSIUS, -11.0);
		
		Temperature withinBoundUpper = new Temperature(TemperatureMetric.CELSIUS, 50.0);
		Temperature withinBoundLower = new Temperature(TemperatureMetric.CELSIUS, -10.0);
		
		assertTrue(range.contains(withinNeg));
		assertTrue(range.contains(withinPos));
		assertTrue(range.contains(withinBoundUpper));
		assertTrue(range.contains(withinBoundLower));
		assertFalse(range.contains(withoutPos));
		assertFalse(range.contains(withoutNeg));
		
	}

}
