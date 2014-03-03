package uk.ac.glasgow.senotes.components.lab.repository;


public interface TemperatureReport {
	
	/**
	 * Adds the specified reading to the underlying repository.
	 * @param reading the temperature reading to record.
	 */
	public void recordTemperatureReading(Reading<Temperature> reading);
}
