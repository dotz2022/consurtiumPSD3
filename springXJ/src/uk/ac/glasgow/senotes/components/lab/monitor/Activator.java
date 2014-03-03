package uk.ac.glasgow.senotes.components.lab.monitor;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureMetric;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureRange;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureReport;

public class Activator implements BundleActivator {

	private TemperatureMonitor monitor;
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		ServiceReference<TemperatureReport> serviceReference =
				context.getServiceReference(TemperatureReport.class);	
		
		TemperatureReport temperatureReport = 
			context.getService(serviceReference);
						

		monitor = 
			new TemperatureMonitor(temperatureReport, 5000l);

		Temperature low  = 
			new Temperature(TemperatureMetric.CELSIUS, 15.5);
		Temperature high =
			new Temperature(TemperatureMetric.CELSIUS, 20.5);
		
		TemperatureRange range =
			new TemperatureRange(low, high);

		RandomTemperatureSensor sensor = 
			new RandomTemperatureSensor(range);
		
		monitor.addTemperatureSensor("Temp02", sensor);
		
		monitor.start();
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		monitor.stopMonitoring();
		
	}
}
