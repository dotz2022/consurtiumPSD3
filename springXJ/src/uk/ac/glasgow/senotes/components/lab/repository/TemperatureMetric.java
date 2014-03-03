package uk.ac.glasgow.senotes.components.lab.repository;

/**
 * A container for different temperature metrics.
 * @author tws
 *
 */
public enum TemperatureMetric {
	CELSIUS("C"), FARENHEIT("F"), KELVIN("K");

	private String symbol;
	
	private TemperatureMetric(String symbol){
		this.symbol = symbol;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public Temperature convertTo(
			TemperatureMetric metric, Double value) {

		Double convertedValue = Double.NaN;

		if (this.equals(metric))
			convertedValue = value;

		if (this.equals(TemperatureMetric.CELSIUS)) {
			if (metric.equals(TemperatureMetric.FARENHEIT))
				convertedValue = celsiusToFarenheit(value);
			else if (metric.equals(TemperatureMetric.KELVIN))
				convertedValue = celsiusToKelvin(value);
		}

		else if (this.equals(TemperatureMetric.KELVIN)) {
			if (metric.equals(TemperatureMetric.FARENHEIT))
				convertedValue = kelvinToFarenheit(value);
			else if (metric.equals(TemperatureMetric.CELSIUS))
				convertedValue = kelvinToCelsius(value);
		}

		else if (this.equals(TemperatureMetric.FARENHEIT)) {
			if (metric.equals(TemperatureMetric.KELVIN))
				convertedValue = farenheitToKelvin(value);
			else if (metric.equals(TemperatureMetric.CELSIUS))
				convertedValue = farenheitToCelsius(value);
		}

		return new Temperature(metric, convertedValue);
	}

	private Double farenheitToCelsius(Double farenheit) {
		return (farenheit - 32) / 1.8;
	}

	private Double celsiusToFarenheit(Double celsius) {
		return 1.8 * celsius + 32;
	}

	private Double celsiusToKelvin(Double celsius) {
		return celsius + 273.15;
	}

	private Double kelvinToCelsius(Double kelvin) {
		return kelvin - 273.15;
	}

	private Double kelvinToFarenheit(Double kelvin) {
		return celsiusToFarenheit(kelvinToCelsius(kelvin));
	}

	private Double farenheitToKelvin(Double farenheit) {
		return celsiusToKelvin(farenheitToCelsius(farenheit));
	}
	
}