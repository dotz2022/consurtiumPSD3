package uk.ac.glasgow.senotes.components.lab.alarm;

import java.util.List;

import uk.ac.glasgow.senotes.components.lab.repository.Reading;
import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureRange;

/**
 * An AlertCondition that will generate an alert when
 * checked if all sensor readings polled are outwith the
 * condition's range.
 * 
 * @author tws
 * 
 */
public class TemperatureRangeAlertCondition implements AlertCondition{
	
	private TemperatureRange range;
	private String sensorID;
	private Integer window;

	/**
	 * 
	 * @param range
	 *            the temperature range that at least one
	 *            recent sensor reading must be within in
	 *            order to not cause an alert to be raised.
	 * @param sensorID
	 *            the identifier of the sensor to check.
	 * @param window
	 *            the number of most recent sensor readings
	 *            to check.
	 */
	public TemperatureRangeAlertCondition(
		TemperatureRange range,
		String sensorID,
		Integer window) {
		
		this.range = range;
		this.sensorID = sensorID;
		this.window = window;
	}

	/**
	 * Issues an alert if all n most recent temperature
	 * readings taken from the specified sensor are outwith
	 * the specified range.
	 */
	@Override
	public Alert checkForAlert(TemperatureQuery query) {
		
		List<Reading<Temperature>> readings = 
			query.getMostRecentTemperatureReadings(
				sensorID,window);
				
		if (readings.size() < window) return null;
		
		for (Reading<Temperature> reading: readings){
			Temperature temperature =
				reading.getSensorValue();
			
			if (range.contains(temperature))
				return null;
		}
		
		String messageTemplate = 
			"All readings were outside range [%s-%s].";
		
		String message =
			String.format(messageTemplate,range.getLow(),range.getHigh());
		
		return new Alert(sensorID, readings, message);
	}	
}