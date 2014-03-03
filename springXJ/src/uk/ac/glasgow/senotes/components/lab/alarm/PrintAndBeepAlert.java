package uk.ac.glasgow.senotes.components.lab.alarm;

/**
 * A simple implementation of an alert listener that prints
 * the alert to stdout and issues an audible warning.
 * 
 * @author tws
 * 
 */
public class PrintAndBeepAlert implements AlertListener {

	@Override
	public void alert(Alert alert) {
		System.out.println(alert);
		java.awt.Toolkit.getDefaultToolkit().beep();
		
	}
	
	
}
