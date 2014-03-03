package uk.ac.glasgow.senotes.components.lab.alarm.test;

import java.util.Date;

import uk.ac.glasgow.senotes.components.lab.monitor.RandomTemperatureSensor;
import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureMetric;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureRange;

public class BrokenTemperatureSensor 
	extends RandomTemperatureSensor {
	
	private Date breaksAfter;
			
	private Temperature lastReading;

	public BrokenTemperatureSensor(TemperatureRange range, Long minimumTime) {
		super(range);
		
		Date now = new Date();
		
		this.breaksAfter =
			new Date (now.getTime() + minimumTime);		
	}

	@Override
	public Temperature takeReading() {
		
		Date now = new Date ();
		
		if (now.before(breaksAfter) || lastReading == null)		
			lastReading = super.takeReading();
		else {
			
			Double newValue = lastReading.getValue() + 1.0;
					
			TemperatureMetric metric = lastReading.getMetric();
			
			lastReading = new Temperature(metric, newValue);
		}
		
		return lastReading;
	}

}
