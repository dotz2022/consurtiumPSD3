package repository.impl;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import repository.IRepositoryLecturerService;

public class Activator implements BundleActivator {


	private DBMS dbms;
	private IRepositoryLecturerService identification;

	
	private ServiceRegistration<IRepositoryLecturerService> 
	IRepositoryLecturerServiceRegistration;

	
	public void start(BundleContext context)
		throws Exception {

		dbms = new DBMS();

		RepositoryHandler handler = new RepositoryHandler(dbms);
		
	 	identification = handler;
		
	 	
	 	
	 	IRepositoryLecturerServiceRegistration =
	          context.registerService(
	        		  IRepositoryLecturerService.class, identification, null);

	}
	

	
	public void stop(BundleContext context)
	throws Exception {
	
		IRepositoryLecturerServiceRegistration.unregister();

    }
		
}