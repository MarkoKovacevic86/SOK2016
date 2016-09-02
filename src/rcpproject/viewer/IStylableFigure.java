package rcpproject.viewer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;

public interface IStylableFigure extends IFigure {
	/**
	  * Set the border color 
	  *  
	  * @param borderColor 
	  *            the border color 
	  */ 
	 void setBorderColor(Color borderColor); 
	 
	 /**
	  * Set the border width 
	  *  
	  * @param borderWidth 
	  *            the border width 
	  */ 
	 void setBorderWidth(int borderWidth); 
	 
}
