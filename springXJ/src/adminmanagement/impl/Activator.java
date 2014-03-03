package adminmanagement.impl;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import repository.IRepositoryLecturerService;
import adminmanagement.IAdminService;


	public class Activator implements BundleActivator {


		Logic logic;
		private IAdminService identification;

		private ServiceRegistration<IAdminService> 
			IAdminServiceRegistration;
		
		public void start(BundleContext context)
			throws Exception {

			logic = new Logic();
			
			ServiceReference<IRepositoryLecturerService> serviceReference =
					context.getServiceReference(IRepositoryLecturerService.class);	
		    
			IRepositoryLecturerService query = context.getService(serviceReference);

			AdminHandler handler = new AdminHandler (logic, query);
	
		 	identification = handler;

		 	IAdminServiceRegistration =
		         context.registerService(
						IAdminService.class, identification, null);
		 	
		}

		
		public void stop(BundleContext context)
		throws Exception {
		
			IAdminServiceRegistration.unregister();

	    }
	
	
	
}
