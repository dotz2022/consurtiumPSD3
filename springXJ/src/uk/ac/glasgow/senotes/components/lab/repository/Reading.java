package uk.ac.glasgow.senotes.components.lab.repository;

import java.util.Date;

/**
 * A container class for a sensor reading recorded from a specified sensor at a given date.
 * @author tws
 *
 * @param <T> the type of the sensor readings recorded.
 */
public class Reading<T> {

	private T value;
	private Date recorded;
	private String sensorID;
	
	/**
	 * Constructs a new reading instance
	 * @param recorded the date the sensor reading was taken
	 * @param sensorID the identifier of the sensor
	 * @param value the value read from the sensor
	 */
	public Reading(Date recorded, String sensorID, T value) {
		this.recorded = recorded;
		this.sensorID = sensorID;
		this.value = value;
	}

	public T getSensorValue() {
		return value;
	}

	public Date getRecorded() {
		return recorded;
	}

	public String getSensorID() {
		return sensorID;
	}
	
	@Override
	public String toString() {
		return "Reading [value=" + value + ", recorded="
				+ recorded + ", sensorID=" + sensorID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((recorded == null) ? 0 : recorded
						.hashCode());
		result = prime
				* result
				+ ((sensorID == null) ? 0 : sensorID
						.hashCode());
		result = prime * result
				+ ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reading<?> other = (Reading<?>) obj;
		if (recorded == null) {
			if (other.recorded != null)
				return false;
		} else if (!recorded.equals(other.recorded))
			return false;
		if (sensorID == null) {
			if (other.sensorID != null)
				return false;
		} else if (!sensorID.equals(other.sensorID))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}