package project;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import project.internal.TestThread;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private TestThread tt;
	
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		//Activator.context = bundleContext;
		tt = new TestThread();
		tt.run();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		tt.stopThread();
		tt.join();
	}

}
