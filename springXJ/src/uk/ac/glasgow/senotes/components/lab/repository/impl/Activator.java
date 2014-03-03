package uk.ac.glasgow.senotes.components.lab.repository.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import uk.ac.glasgow.senotes.components.lab.repository.TemperatureQuery;
import uk.ac.glasgow.senotes.components.lab.repository.TemperatureReport;

public class Activator implements BundleActivator {

	private DBMS dbms;
	private TemperatureReport report;
	private TemperatureQuery query;
	
	private ServiceRegistration<TemperatureReport> 
		temperatureReportRegistration;
	
	private ServiceRegistration<TemperatureQuery> 
		temperatureQueryRegistration;

	//...
	
	@Override
	public void start(BundleContext context)
		throws Exception {
		
		dbms = new DBMS();
		
		TemperatureHandler handler = 
			new TemperatureHandler(dbms);
		
		report = handler;
		query = handler;
		
		temperatureReportRegistration = 
			context.registerService(
				TemperatureReport.class, report, null);	
		
		temperatureQueryRegistration = 
				context.registerService(
					TemperatureQuery.class, query, null);	
	}	
	
	

	@Override
	public void stop(BundleContext context)
		throws Exception {
		
		temperatureReportRegistration.unregister();
		temperatureQueryRegistration.unregister();
	}
}