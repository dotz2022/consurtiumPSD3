package uk.ac.glasgow.senotes.components.lab.alarm;

import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;

/**
 * A general interface for classes that check the state of a
 * temperature repository in order to check whether an alert
 * should be issued.
 * @author tws
 * 
 */
public interface AlertCondition {

	/**
	 * @param query
	 *            an interface to a temperature readings
	 *            store.
	 * @return an Alert if, when this method is invoked, the
	 *         alert condition is satisifed, else a null
	 *         value is returned.
	 */
	public Alert checkForAlert(TemperatureQuery query);

}
