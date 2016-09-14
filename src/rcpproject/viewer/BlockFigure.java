package rcpproject.viewer;

import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import rcpproject.model.GraphNode;
import rcpproject.model.Property;

public class BlockFigure extends RectangleFigure implements ILabeledFigure, IStylableFigure {

	private Color borderColor;
	private FrameBorder border = new FrameBorder();
	
	
	
	public BlockFigure(GraphNode node) {
		super();
		border.setLabel(node.getName());
		this.setBorder(border);
		GridLayout layout = new GridLayout(2, false);
		setLayoutManager(layout);
		setPreferredSize(100,100);
		Label name;
		Label value;
		Font f = new Font(null, "Arial", 7, SWT.NORMAL);
		if(node.getNodeProperties().size() != 0){
			for(Property p : node.getNodeProperties()){
				name = new Label(p.getType() + " >> " + p.getName());
				value = new Label(p.getName() );
				name.setFont(f);
				value.setFont(f);
				add(name);
				//add(value);
			}
		}else{
			name = new Label(" ");
			value = new Label(" ");
			name.setFont(f);
			value.setFont(f);
			add(name);
			add(value);
		}
		adjustSize();
		
	}
	
	protected void adjustSize(){
		setSize(getPreferredSize());
	}

	@Override
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;

	}

	@Override
	public void setBorderWidth(int borderWidth) {
		setLineWidth(borderWidth);

	}

	@Override
	public void setText(String text) {
		border.setLabel(text);

	}

	@Override
	public String getText() {
		return border.getLabel();
	}

	@Override
	public void setIcon(Image icon) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getIcon() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
