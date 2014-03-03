package uk.ac.glasgow.senotes.components.lab.alarm.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import uk.ac.glasgow.senotes.components.lab.alarm.Alert;
import uk.ac.glasgow.senotes.components.lab.alarm.AlertListener;

public class AlertCatcher implements AlertListener {

	private BlockingQueue<Alert> queue = new LinkedBlockingQueue<Alert>();
	
	@Override
	public void alert(Alert sensorID) {
		try {
			queue.put(sensorID);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Alert retrieveOldestAlert() throws InterruptedException{
		return queue.take();
	}
	
	
}
