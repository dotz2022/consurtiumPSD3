package uk.ac.glasgow.senotes.components.lab.alarm;

import java.util.HashSet;
import java.util.Set;

import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;

/**
 * An alarm is a thread that periodically checks the state
 * of the sensor readings repository against configured
 * alert conditions. Alerts are issued to configured
 * AlertListeners when an AlertCondition is satisfied.
 * Checking continues until the stopMonitoring method is
 * invoked.
 * 
 * @author tws
 * 
 */
public class Alarm extends Thread {
	
	private TemperatureQuery query;

	private Long periodicity;
	
	private Boolean isMonitoring;

	private Set<AlertCondition> conditions;
	
	private Set<AlertListener> listeners;
	
	/**
	 * Constructs a new Alarm that checks the specified repository
	 * @param query the repository to check
	 * @param periodicity the period of time between checks.
	 */
	public Alarm(TemperatureQuery query, Long periodicity){
		this.query = query;
		this.periodicity = periodicity;
		
		isMonitoring = true;
		conditions = new HashSet<AlertCondition>();
		listeners = new HashSet<AlertListener>();
	}
	
	/**
	 * Stops the Alarm thread.
	 */
	public void stopMonitoring() {
		this.isMonitoring = false;
	}
	
	/**
	 * Registers a new alert condition.
	 * @param condition a new condition to check.
	 */
	public void registerAlertCondition(AlertCondition condition){
		conditions.add(condition);
	}
	
	/**
	 * Registers a new alert listener
	 * @param listener the AlertListener to be notified when an alert is raised.
	 */
	public void registerAlertListener(AlertListener listener){
		listeners.add(listener);
	}
	
	public void run (){
		while (isMonitoring){
			
			for (AlertCondition condition: conditions){
				
				Alert alert = 
					condition.checkForAlert(query);
				
				if (alert != null)
					raiseAlert(alert);
													
				try {
					Thread.sleep(periodicity);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void raiseAlert(Alert alert) {
				
		for (AlertListener listener: listeners)
			listener.alert(alert);
	}
}
