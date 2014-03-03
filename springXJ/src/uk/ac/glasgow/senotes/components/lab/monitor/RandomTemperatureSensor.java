package uk.ac.glasgow.senotes.components.lab.monitor;

import java.util.Random;

import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureRange;

public class RandomTemperatureSensor implements TemperatureSensor {

	
	private Random random;
	private TemperatureRange range;

	/**
	 * Constructs a new sensor that will report temperatures
	 * randomly in the specified range.
	 * 
	 * @param range
	 *            the boundaries for random temperatures to
	 *            report.
	 */
	public RandomTemperatureSensor (TemperatureRange range){
		this.range = range;

		random = new Random();	
	}
	
	@Override
	public Temperature takeReading() {
		Double nextDouble = random.nextDouble();
		
		Double high = range.getHigh().getValue();
		Double low = range.getLow().getValue();
		
		Double scalar = high-low;
		Double value = 
			nextDouble*(scalar)+low;
		
		Temperature temperature =
			new Temperature(range.getMetric(),value);
		
		return temperature;
	}

}
