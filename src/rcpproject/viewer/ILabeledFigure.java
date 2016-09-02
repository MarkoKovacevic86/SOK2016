package rcpproject.viewer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Image;

public interface ILabeledFigure extends IFigure {

	 /**
	  * Set the label text. If needed, the figure should adjust its size. 
	  *  
	  * @param text 
	  *            the text 
	  */ 
	 public void setText(String text); 
	 
	 /**
	  * Get the label text. 
	  *  
	  * @return the label text 
	  */ 
	 public String getText(); 
	 
	 /**
	  * Set the label icon. If needed, the figure should adjust its size. 
	  *  
	  * @param icon 
	  *            the icon image or <code>null</code> for no icon 
	  */ 
	 public void setIcon(Image icon); 
	 
	 /**
	  * Get the label icon. 
	  *  
	  * @return the icon image 
	  */ 
	 public Image getIcon(); 
	 
	}
