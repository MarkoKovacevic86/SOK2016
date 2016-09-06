package rcpproject.view;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import rcpproject.source.SourceDescription;
import rcpproject.source.SourceManager;

public class SelectionDialog extends Dialog {

	private Combo sourceCombos;
	private String sourceName;
	
	public SelectionDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		Composite c = new Composite(parent, SWT.BORDER);
		GridLayout lay = new GridLayout(2, true);
		c.setLayout(lay);
		Label l = new Label(c, SWT.NONE);
		l.setText("Source: ");
		l.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		sourceCombos = new Combo(c, SWT.NONE);
		for(SourceDescription sd : SourceManager.getInstance().getSources()){
			sourceCombos.add(sd.getName());
		}
		sourceCombos.select(0);
		return c;
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		sourceName = sourceCombos.getText();
		super.okPressed();
	}

	public Combo getSourceCombos() {
		return sourceCombos;
	}

	public void setSourceCombos(Combo sourceCombos) {
		this.sourceCombos = sourceCombos;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	

}
