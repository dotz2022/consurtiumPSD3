/**
 * 
 */
package uk.ac.glasgow.senotes.components.lab.repository.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.senotes.components.lab.repository.Reading;
import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureMetric;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureReport;
import uk.ac.glasgow.senotes.components.lab.repository.impl.DBMS;
import uk.ac.glasgow.senotes.components.lab.repository.impl.TemperatureHandler;

/**
 * @author tws
 *
 */
public class TempHandlerTest {
	
	private DBMS dbms;
	private TemperatureReport report;
	private TemperatureQuery query;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dbms = new DBMS();
		TemperatureHandler handler = new TemperatureHandler(dbms);
		report = handler;
		query = handler;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		report = null;
	}

	/**
	 * Test method for {@link uk.ac.glasgow.senotes.components.lab.repository.impl.TemperatureHandler#recordTemperatureReading(uk.ac.glasgow.senotes.components.lab.repository.Reading)}.
	 */
	@Test
	public void testRecordTemperatureReading() {
		
		Reading<Temperature> reading = getSampleReading();
		
		report.recordTemperatureReading(reading);
	}
	
	@Test
	public void testGetMostRecentTemperatureReadings(){
		Reading<Temperature> expected = getSampleReading();
		
		report.recordTemperatureReading(expected);
		
		List<Reading<Temperature>> readings = query.getMostRecentTemperatureReadings("Temp01",1);
				
		Reading<Temperature> actual = readings.get(0);
		
		System.out.println(actual);
		System.out.println(expected);
		
		assertTrue(actual.equals(expected));
	}
	
	private Reading<Temperature> getSampleReading() {
		Date now = new Date();
		String sensorID = "Temp01";
		
		TemperatureMetric metric = TemperatureMetric.CELSIUS;
		Double value = 40.0;
		
		Temperature temperature = new Temperature(metric,value);
		
		Reading<Temperature> reading =
			new Reading<Temperature>(now,sensorID,temperature);
		
		return reading;
	}
}
