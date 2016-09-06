package rcpproject.views;

import javax.swing.plaf.FontUIResource;
import javax.xml.ws.Dispatch;

import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import rcpproject.providers.MyContentProvider;
import rcpproject.providers.MyLabelProvider;
import rcpproject.source.SourceDescription;
import rcpproject.source.SourceManager;
import rcpproject.test.TestDataProvider;
import rcpproject.view.SelectionDialog;

public class MainView extends ViewPart implements IZoomableWorkbenchPart{
	
	public static final String ID = "project.views.mainview";
	private GraphViewer viewer;
	private Graph graphOnView;
	private Text searchText;
	private Button searchButton;
	
	private Layout layout = Layout.Radial;
	private Viewport viewPort = null;

	public enum Layout{
		Radial("Radial"),
		Tree("Tree"),
		Grid("Grid"),
		Spring("Spring"),
		Directed("Directed");
		
		private String title;
		private Layout(String title){
			this.title = title;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return title;
		}
	}
	
	public Layout getLayout(){
		return layout;
	}
	
	
	public MainView() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new GraphViewer(parent, SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		parent.setLayout(layout);
		setupComponents(parent);
	}
	
	private void setupComponents(Composite parent){
		Label filter = new Label(parent, SWT.NONE);
		filter.setText("Filter:");
		
		Text searchText = new Text(parent, SWT.BORDER);
		GridData stgd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);
		searchText.setLayoutData(stgd);
		searchText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				filterGraph(searchText.getText());
				System.out.println(searchText.getText());
				System.out.println("hej");
			}
		});
		searchButton = new Button(parent, SWT.NONE);
		searchButton.setText("Brows");
		searchButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				SelectionDialog sd = new SelectionDialog(Display.getCurrent().getActiveShell());
				sd.open();

			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		GridData gridData = initialiseGridData();
		initialiseViewer(parent,gridData);
		
	}
	
	private GridData initialiseGridData(){
		GridData gridData = new GridData(); 
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		return gridData;
	}
	
	private void initialiseViewer(Composite parent, GridData gridData){
		
		viewer = new GraphViewer(parent, SWT.NONE);
		
		viewPort = viewer.getGraphControl().getViewport();
		//viewPort.setSize(new Dimension(500,500));
		viewer.getGraphControl().setLayoutData(gridData);
		
		
		viewer.setContentProvider(new MyContentProvider());
		viewer.setLabelProvider(new MyLabelProvider());
		TestDataProvider tdp = new TestDataProvider();
		viewer.setInput(tdp.getNodes());
		viewer.refresh();
		viewer.setLayoutAlgorithm(new RadialLayoutAlgorithm());
		viewer.applyLayout();
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}
	
	private void setFilters(){
	//	nfilter = new NodeFilter();
	//	ViewerFilter[] filters = new ViewerFilter[1];
	//	filters[0] = nfilter;
	//	viewer.setFilters(filters);
	}
	
	public void filterGraph(String filterWord){
	//	nfilter.setFilterWord(filterWord);
		viewer.refresh();
	}
	
	public LayoutAlgorithm getLayoutAlgorithm(){
		switch(layout){
			case Radial: return new RadialLayoutAlgorithm();
			case Tree: return new TreeLayoutAlgorithm();
			case Grid: return new GridLayoutAlgorithm();
			case Spring: return new SpringLayoutAlgorithm();
			case Directed: return new DirectedGraphLayoutAlgorithm(SWT.NONE);
		}return null;
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	

	@Override
	public AbstractZoomableViewer getZoomableViewer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setContentProvider(IGraphEntityContentProvider provider){
		viewer.setContentProvider(provider);
	}
	
	public void setLableProvider(LabelProvider provider){
		viewer.setLabelProvider(provider);
		viewer.getGraphControl().applyLayout();
	}
	
	public void setInput(Object o){
		if(o instanceof Graph){
			graphOnView = (Graph)o;
		}
		viewer.setInput(o);
		viewer.refresh();
		viewer.getGraphControl().applyLayout();
		viewer.refresh();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}

}
