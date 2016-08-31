package rcpproject;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import rcpproject.view.MainWindow;

import org.eclipse.ui.plugin.*;

public class Activator extends AbstractUIPlugin implements BundleActivator {

	public static final String ID =  "Project";
	
	private static BundleContext context;
	private static Activator plugin;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		plugin = this;
		Activator.context = bundleContext;
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
