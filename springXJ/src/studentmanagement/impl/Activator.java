package studentmanagement.impl;


import studentmanagement.IStudentService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import org.osgi.framework.ServiceRegistration;
import repository.IRepositoryLecturerService;

public class Activator implements BundleActivator {


	IStudentService studentService;
	IRepositoryLecturerService systemService;


	private ServiceRegistration<IStudentService> 
		IStudentServiceRegistration;
	
	

	public void start(BundleContext context)
		throws Exception {

			
			
			ServiceReference<IRepositoryLecturerService> serviceLecturerReference =
					context.getServiceReference(IRepositoryLecturerService.class);	
			
		    
			IRepositoryLecturerService lecturerQuery = context.getService(serviceLecturerReference);
		
			StudentHandler handler = new StudentHandler(lecturerQuery);
			
			studentService = handler;
			
			IStudentServiceRegistration =
			        context.registerService(
							IStudentService.class, studentService, null);

	}
	
	

	public void stop(BundleContext context)
	throws Exception {
		
		IStudentServiceRegistration.unregister();
    }
		
}