package uk.ac.glasgow.senotes.components.lab.alarm;

import java.util.List;

import uk.ac.glasgow.senotes.components.lab.repository.Reading;
import uk.ac.glasgow.senotes.components.lab.repository.Temperature;

/**
 * A container class for information that resulted in an
 * AlertCondition generating an alert.
 * 
 * @author tws
 * 
 */
public class Alert {

	private String sensorID;
	private List<Reading<Temperature>> readings;
	private String message;

	public Alert(
		String sensorID,
		List<Reading<Temperature>> readings,
		String message) {

		this.sensorID = sensorID;
		this.readings = readings;
		this.message = message;
	}

	public String getSensorID() {
		return sensorID;
	}

	public List<Reading<Temperature>> getReadings() {
		return readings;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Alert [sensorID=" + sensorID
				+ ", readings=" + readings + ", message="
				+ message + "]";
	}
}
