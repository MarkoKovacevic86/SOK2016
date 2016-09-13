package rcpproject.java.source;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;

public class SourceDialog extends Dialog{

	private Text locationText;
	private String location;
	private Composite composite;
	
	
	public SourceDialog(IWorkbenchWindow window) {
		// TODO Auto-generated constructor stub
		super(window);
	}
	
	protected SourceDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
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
		composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(3, false));
		Label l = new Label(composite, SWT.NONE);
		l.setText("Document: ");
		l.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,false,false,1,1));
		
		locationText = new Text(composite, SWT.BORDER);
		locationText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,true,1,1));
		Button btn = new Button(composite,SWT.PUSH);
		btn.setText("Brows files");
		btn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.button == 1){
					FileDialog fd = new FileDialog(composite.getShell(), SWT.OPEN);
					fd.setText("Open");
					fd.setFilterPath("C:/");
					String[] extensions = {"*.xml", "*.xsd"};
					fd.setFilterExtensions(extensions);
					String path = fd.open();
					if(path != null){
						locationText.setText(path);
					}else{
						locationText.setText("");
					}
				}
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		return super.createDialogArea(parent);
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		if(locationText.getText().equals("")){
			MessageDialog.openError(getShell(), "Warning", "Blank location field");
			return;
		}
		location = locationText.getText();
		super.okPressed();
	}

	public String getLocation() {
		return location;
	}
	
}
