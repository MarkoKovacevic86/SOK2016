package rcpproject.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Viewport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
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
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

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
		
		
	/*	viewer.setContentProvider(new MyContentProvider());
		viewer.setLabelProvider(new MyLabelProvider());
		TestDataProvider tdp = new TestDataProvider();
	//	viewer.setInput(tdp.getNodes());
		viewer.refresh();*/
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


	public GraphViewer getViewer() {
		return viewer;
	}

	public void searchId(String search){
		List listOfGraphNodes = viewer.getGraphControl().getNodes();
		List tempOfGraphNodes = new ArrayList();
		/*for(Object node : listOfGraphNodes){
			if(node instanceof GraphNode){
				GraphNode gnode = ((GraphNode) node);
				if(gnode.getData() instanceof rcpproject.model.GraphNode){
					rcpproject.model.GraphNode graphNode = (rcpproject.model.GraphNode)gnode.getData();
					
				}
			}
		}*/
		if(!listOfGraphNodes.isEmpty()){
			StructuredSelection selection = new StructuredSelection();
		}
	}
	
}
