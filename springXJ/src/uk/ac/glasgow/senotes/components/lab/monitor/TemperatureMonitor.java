package uk.ac.glasgow.senotes.components.lab.monitor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import uk.ac.glasgow.senotes.components.lab.repository.Reading;
import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureReport;

/**
 * Manages the collection and reporting of temperature
 * readings from configured sensors. The class is a Thread
 * that will continuously monitor configured sensors once
 * started until the stopMonitoring() method is invoked.
 * 
 * @author tws
 * 
 */
public class TemperatureMonitor extends Thread {

	private Long sleepTime = 5000l;
		
	private TemperatureReport repository;

	private Boolean isMonitoring;
	
	private Map<String,TemperatureSensor> sensors;
	
	/**
	 * Constructs a new temperature monitor that reports
	 * temperature readings for configured sensors to the
	 * specified repository. The sensors are polled at most
	 * once for each period.
	 * 
	 * @param repository
	 *            the store for temperature readings.
	 * @param periodicity
	 *            the time between each poll of the sensors.
	 */
	public TemperatureMonitor(TemperatureReport repository, Long periodicity){
		this.repository = repository;
		isMonitoring = true;
		sensors = new HashMap<String,TemperatureSensor>();
	}
	
	/**
	 * Adds a temperature sensor to the monitor.
	 * @param sensorID the unique identifier for the sensor.
	 * @param sensor the sensor implementation.
	 */
	public void addTemperatureSensor(String sensorID, TemperatureSensor sensor){
		sensors.put(sensorID, sensor);
	}
	
	@Override
	public void run() {
		while (isMonitoring){
			
			for (String sensorID: sensors.keySet()){
				Date now = new Date();

				TemperatureSensor sensor = sensors.get(sensorID);
				
				Temperature temperature = sensor.takeReading();
				
				Reading<Temperature> reading = 
					new Reading<Temperature>(now, sensorID, temperature);
				
				repository.recordTemperatureReading(reading);
			}
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Stops the monitor polling the sensors for data.
	 */
	protected void stopMonitoring(){
		isMonitoring = false;
	}
}