package uk.ac.glasgow.senotes.components.lab.alarm.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.senotes.components.lab.alarm.Alarm;
import uk.ac.glasgow.senotes.components.lab.alarm.Alert;
import uk.ac.glasgow.senotes.components.lab.alarm.AlertCondition;
import uk.ac.glasgow.senotes.components.lab.alarm.AlertListener;
import uk.ac.glasgow.senotes.components.lab.alarm.PrintAndBeepAlert;
import uk.ac.glasgow.senotes.components.lab.alarm.TemperatureRangeAlertCondition;
import uk.ac.glasgow.senotes.components.lab.monitor.TemperatureMonitor;
import uk.ac.glasgow.senotes.components.lab.monitor.TemperatureSensor;
import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureMetric;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureRange;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureReport;
import uk.ac.glasgow.senotes.components.lab.repository.impl.DBMS;
import uk.ac.glasgow.senotes.components.lab.repository.impl.TemperatureHandler;

public class AlarmTest {
		
	private DBMS dbms;
	private TemperatureReport report;
	private TemperatureQuery query;
	
	private Alarm alarm;

	private TemperatureMonitor monitor;
	private AlertCatcher catcher;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		dbms = new DBMS();
		TemperatureHandler handler = new TemperatureHandler(dbms);
		report = handler;
		query = handler;
		
		//Create a monitor with a periodicity of 5s
		monitor = new TemperatureMonitor(report,5000l);
		
		Temperature low  = 
			new Temperature(TemperatureMetric.CELSIUS, 15.5);
		Temperature high =
			new Temperature(TemperatureMetric.CELSIUS, 16.5);
		
		TemperatureRange range =
			new TemperatureRange(low, high);
		
		TemperatureSensor brokenSensor = 
			new BrokenTemperatureSensor(range,5000l);
		
		monitor.addTemperatureSensor("Temp02",brokenSensor);
		
		alarm = new Alarm(query, 5000l);
		
		AlertCondition condition = 
			new TemperatureRangeAlertCondition(range, "Temp02", 5);
		
		alarm.registerAlertCondition(condition);
	
		catcher = new AlertCatcher();
		
		AlertListener listener = new PrintAndBeepAlert();
		
		alarm.registerAlertListener(listener);
		alarm.registerAlertListener(catcher);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		alarm.start();
		monitor.start();
		
		Alert alert;
		try {
			alert = catcher.retrieveOldestAlert();
			assertEquals("Temp02", alert.getSensorID());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
