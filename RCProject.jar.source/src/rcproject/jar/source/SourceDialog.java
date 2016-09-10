package rcproject.jar.source;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;

public class SourceDialog extends Dialog{
	
	private Composite composite;
	private String location;
	private Text locationText;
	
	
	protected SourceDialog(IShellProvider parentShell) {
		super(parentShell);
		// TODO Auto-generated constructr stub
	}
	
	public SourceDialog(IWorkbenchWindow window){
		super(window);
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Select source file");
		newShell.setSize(300, 300);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(3,false));
		Label l = new Label(composite, SWT.NONE);
		l.setText("Document: ");
		l.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		locationText = new Text(composite, SWT.BORDER);
		locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1,1));
		Button btn = new Button(composite, SWT.PUSH);
		return super.createDialogArea(parent);
	}
	
	
	
}
