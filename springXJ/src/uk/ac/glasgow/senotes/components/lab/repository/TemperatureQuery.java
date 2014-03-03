package uk.ac.glasgow.senotes.components.lab.repository;

import java.util.List;


public interface TemperatureQuery {
	
	/**
	 * Obtains the n most recent temperature readings for the specified sensor.
	 * @param sensorID the identifier for the sensor to be queried.
	 * @param n the number of most recent readings to return.
	 * @return up to n most recent readings.
	 */
	public List<Reading<Temperature>> 
		getMostRecentTemperatureReadings(
			String sensorID, Integer n);
}
