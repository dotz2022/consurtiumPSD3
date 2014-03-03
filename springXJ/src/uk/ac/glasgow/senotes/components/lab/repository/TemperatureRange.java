package uk.ac.glasgow.senotes.components.lab.repository;

/**
 * A container class for two temperatures bounding a range.
 * @author tws
 *
 */
public class TemperatureRange {
	private Temperature low;
	private Temperature high;
	
	/**
	 * Constructs a new temperature range bounded by the two
	 * parameters. If the temperature boundaries are of
	 * different metrics, the upper bound is first converted
	 * to a temperature in the lower bound's metric.
	 * 
	 * An IllegalArgumentException will be thrown if the
	 * lower bound is greater than the upper bound.
	 * 
	 * @param low
	 *            the range's lower bound.
	 * @param high
	 *            the range's upper bound.
	 */
	public TemperatureRange(
		Temperature low, Temperature high) {
		
		if (low.compareTo(high) > 0){
			String messageTemplate = 
				"Low [%s] must be less than or equal to high [%s]";
			
			String message =
				String.format(messageTemplate, low, high);
			
			throw new IllegalArgumentException(message);
		}
		
		this.low = low;
		this.high = high.convertTo(low.getMetric());
		
	}

	public Temperature getLow() {
		return low;
	}

	public Temperature getHigh() {
		return high;
	}
	
	/**
	 * @return the metric for this range.
	 */
	public TemperatureMetric getMetric() {
		return low.getMetric();
	}

	/**
	 * Checks whether the specified temperature is within
	 * this range.
	 * 
	 * @param temperature
	 *            the value to check.
	 * @return true if the temperature to check is greater
	 *         than or equal to the lower bound of this
	 *         range and less than or equal to the upper
	 *         bound of this range.
	 */
	public Boolean contains(Temperature temperature) {
		return
			(low.compareTo(temperature) <= 0 &&
			 high.compareTo(temperature) >= 0 );
	}

	@Override
	public String toString() {
		return
			"TemperatureRange [low=" + low + ", high="+ high + "]";
	}
}
