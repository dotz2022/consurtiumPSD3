package uk.ac.glasgow.senotes.components.lab.repository;

/**
 * A container class for temperature values, comprising a Double value and a metric.
 * @author tws
 *
 */
public class Temperature implements Comparable<Temperature> {
		
	private TemperatureMetric metric;
	private Double value;

	public Temperature(TemperatureMetric metric, Double value) {
		this.metric = metric;
		this.value = value;
	}
	
	public TemperatureMetric getMetric() {
		return metric;
	}

	public Double getValue() {
		return value;
	}
	
	/**
	 * Converts this temperature to a temperature in the
	 * specified target metric.
	 * 
	 * @param target
	 *            the type of the temperature value to
	 *            return.
	 * @return A new temperature instance of the specified
	 *         metric with a value equivalent to this
	 *         temperature.
	 */
	public Temperature convertTo(TemperatureMetric target){
		return metric.convertTo(target, value);
	}

	/**
	 * Compares two temperatures according to the contract
	 * specified by the Comparable interface.
	 * 
	 * @return 0 if the two temperatures are equal, less
	 *         than 0 if this temperature is less than the
	 *         compared temperature and greater than 0 if
	 *         this temperature is greater than the compared
	 *         temperature.
	 */
	@Override
	public int compareTo(Temperature temperature) {
		Temperature normalised = temperature.convertTo(metric);
		
		return  value.compareTo(normalised.getValue());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((metric == null) ? 0 : metric.hashCode());
		result = prime * result
				+ ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Temperature other = (Temperature) obj;
		if (metric != other.metric)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%.2f%s", value,metric.getSymbol());
	}
}
