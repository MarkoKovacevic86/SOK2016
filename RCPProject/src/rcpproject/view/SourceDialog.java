package rcpproject.view;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class SourceDialog extends Dialog {

	private Combo sourceCombo;
	private String source;
	
	protected SourceDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setText("Source");
	}
	
	protected Control initializeDialog(Composite parent){
		Composite com = new Composite(parent, SWT.NONE);
		
		return parent;
		
	}

}
