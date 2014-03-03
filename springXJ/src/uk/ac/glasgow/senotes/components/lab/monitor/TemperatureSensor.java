package uk.ac.glasgow.senotes.components.lab.monitor;

import uk.ac.glasgow.senotes.components.lab.repository.Temperature;

/**
 * An interface for reading temperatures from a heterogeneous range of temperature sensors.
 * @author tws
 *
 */
public interface TemperatureSensor {
	
	/**
	 * @return a Temperature value taken from the underlying sensor. 
	 */
	public Temperature takeReading();
}
