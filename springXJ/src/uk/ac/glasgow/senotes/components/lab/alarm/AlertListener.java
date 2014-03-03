package uk.ac.glasgow.senotes.components.lab.alarm;

/**
 * Implementing classes can register with an Alarm instance to receive alerts.
 * @author tws
 *
 */
public interface AlertListener {
	
	/**
	 * Notifies an implementing instance of an alert.
	 * @param alert the notification alert.
	 */
	public void alert(Alert alert);
}
