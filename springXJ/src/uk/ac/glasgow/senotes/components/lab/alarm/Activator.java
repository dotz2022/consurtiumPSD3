package uk.ac.glasgow.senotes.components.lab.alarm;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.ac.glasgow.senotes.components.lab.repository.Temperature;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureMetric;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureRange;

public class Activator implements BundleActivator {

	private Alarm alarm;
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		ServiceReference<TemperatureQuery> serviceReference =
				context.getServiceReference(TemperatureQuery.class);
		
		TemperatureQuery query = context.getService(serviceReference);
		
		Alarm alarm = new Alarm (query, 5000l);
		
		Temperature low  = 
			new Temperature(TemperatureMetric.CELSIUS, 15.5);
		Temperature high =
			new Temperature(TemperatureMetric.CELSIUS, 16.5);
		
		TemperatureRange range =
			new TemperatureRange(low, high);
		
		AlertCondition condition = 
			new TemperatureRangeAlertCondition(range, "Temp02", 5);
		
		alarm.registerAlertCondition(condition);
		
		AlertListener listener = new PrintAndBeepAlert();
		
		alarm.registerAlertListener(listener);
							
		alarm.start();
	}	

	@Override
	public void stop(BundleContext context)
		throws Exception {
		
		alarm.stopMonitoring();
	}
}
