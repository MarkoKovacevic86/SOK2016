package rcpproject.views;

import java.io.File;

import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.viewers.internal.ZoomManager;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import rcpproject.controls.NodeFilter;

public class MainView extends ViewPart implements IZoomableWorkbenchPart{
	
	public static final String ID = "project.views.mainview";
	private GraphViewer viewer;
	private Graph graphOnView;
	private Text searchText;
	private Button searchButton;
	
	private Layout layout = Layout.Radial;
	private Viewport viewPort = null;
	private NodeFilter nfilter;
	private Combo combo;
	private Combo zoomCombo;
	
	private Point start = null;
	private Point previousPoint = null;
	private Point diff = null;
	private boolean scrollStarted  = false;
	private ZoomManager zm ;
	
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
	
	public void setLayout(String layoutText){
		switch(layoutText){
		case "Radial": layout = Layout.Radial;
		case "Tree" : layout = Layout.Tree;
		case "Grid" : layout = Layout.Grid;
		case "Spring" : layout = Layout.Spring;
		case "Directed" : layout = Layout.Directed;
		}
		
	}
	
	
	public MainView() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 5;
		parent.setLayout(layout);
		setupComponents(parent);
	}
	
	private void setupComponents(Composite parent){
		Text searchText = new Text(parent, SWT.BORDER);
		searchText.setMessage("Filter");
		GridData stgd = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);
		searchText.setLayoutData(stgd);
		searchText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				filterGraph(searchText.getText());
				
			}
		});
		Label layout = new Label(parent, SWT.NONE);
		layout.setText("Layout:");
		Combo combo = initialiseLayoutBox(parent);
		initialiseZoomBox(parent);
		GridData gridData = initialiseGridData();
		initialiseViewer(parent,gridData);
		
	}
	
	private GridData initialiseGridData(){
		GridData gridData = new GridData(); 
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 5;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		return gridData;
	}
	
	private void initialiseViewer(Composite parent, GridData gridData){
		
		viewer = new GraphViewer(parent, SWT.NONE);
		
		viewPort = viewer.getGraphControl().getViewport();
		viewer.getGraphControl().setLayoutData(gridData);
		viewer.setLayoutAlgorithm(new RadialLayoutAlgorithm());
		viewer.applyLayout();
		setFilters();	
		initializeMouseClick(viewer);
		initializeMove(viewer);
		initializeZoom(viewer);
	}
	
	private void initializeMouseClick(GraphViewer viewer){
		viewer.getControl().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				scrollStarted = false;
				start = null;
				previousPoint = null;
				if(viewer.getGraphControl().getDragDetect() && !viewer.getSelection().isEmpty()){
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					bView.refresh(viewer, null);
				}
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.button == 1 && viewer.getSelection().isEmpty()){
					if(start == null){
						start = new Point();
						start.x = e.x;
						start.y = e.y;
					}scrollStarted = true;
				}
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}
	
	private void initializeMove(GraphViewer viewer){
		viewer.getControl().addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				// TODO Auto-generated method stub
				if(scrollStarted == true){
					Point position = new Point();
					position.x = e.x;
					position.y = e.y;
					if(previousPoint == null){
						diff = new Point();
						previousPoint = new Point();
						previousPoint.x = position.x;
						previousPoint.y = position.y;
						diff.x = start.x - position.x;
						diff.y = start.y - position.y;
						System.out.println(diff.x);
					}else{
						diff.x = previousPoint.x - position.x;
						diff.y = previousPoint.y - position.y;
						previousPoint.x = position.x;
						previousPoint.y = position.y;
					}viewPort.setViewLocation(viewPort.getViewLocation().x + diff.x, viewPort.getViewLocation().y + diff.y);
					
				}
				
			}
		});
	}
	
	private void initializeZoom(GraphViewer viewer){
		viewer.getControl().addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseScrolled(MouseEvent e) {
				// TODO Auto-generated method stub
				if(viewer.getGraphControl().getGraph() != null){
					if (( e.stateMask & SWT.CTRL ) == 0)
	                    return;                
	                if (e.count < 0) {
	                    zm.zoomOut();
	                } else if (e.count > 0) {
	                    zm.zoomIn();
	                }
				}
			}
		});
	}
	
	private void initZoom(){
		Graph graph = viewer.getGraphControl().getGraph();
		zm = new ZoomManager(graph.getRootLayer(), graph.getViewport());		
	}
	
	private void setFilters(){
		nfilter = new NodeFilter();
		ViewerFilter[] filters = new ViewerFilter[1];
		filters[0] = nfilter;
		viewer.setFilters(filters);
	}
	
	public void filterGraph(String filterWord){
		nfilter.setFilterWord(filterWord);
		setLayoutAlg(combo.getText());
		viewer.refresh();
		bView.filterGraph(filterWord);
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
	
	public LayoutAlgorithm getLayoutAlgorithm(String layoutText){
		switch(layoutText){
			case "Radial": {
				layout = Layout.Radial;
				return new RadialLayoutAlgorithm();
			}
			case "Tree":{
				layout = Layout.Tree;
				return new TreeLayoutAlgorithm();
			}
			case "Grid": {
				layout = Layout.Grid;
				return new GridLayoutAlgorithm();
			}
			case "Spring": {
				layout = Layout.Spring;
				return new SpringLayoutAlgorithm();
			}
			case "Directed": {
				layout = Layout.Directed;
				return new DirectedGraphLayoutAlgorithm(SWT.NONE);
			}case "":{
				layout = Layout.Radial;
				return new RadialLayoutAlgorithm();
			}
		}return null;
	}
	
	public void setLayoutAlg(String layoutText){
		viewer.setLayoutAlgorithm(getLayoutAlgorithm(layoutText));
		viewer.applyLayout();
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
		initZoom();
	}


	public GraphViewer getViewer() {
		return viewer;
	}

	BirdView bView = (BirdView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
	findView("rcpproject.views.birdview");; 
	
	private Combo initialiseLayoutBox(Composite parent){
		combo = new Combo(parent, SWT.BORDER);
		String[] layouts = {"Radial", "Tree", "Grid", "Spring", "Directed"	};
		final String selectedLayout = layouts[0];
		combo.setItems(layouts);
		combo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				setLayoutAlg(combo.getText());
				
				bView.setLayoutAlg();
			}
		});
		return combo;
	}
	
	private void initialiseZoomBox(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));
		Button zoomIn = new Button(composite, SWT.NONE);
		zoomIn.setText("+");
		initialiseZoomListener(zoomIn);
		Button zoomOut = new Button(composite, SWT.NONE);
		zoomOut.setText("-");
		composite.pack();
		
		initialiseZoomListener(zoomOut);
		
	}
	
	private void initialiseZoomListener(Button button){
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(button.getText().equals("+")){
					zm.zoomIn();
				}else if (button.getText().equals("-")){
					zm.zoomOut();
				}else
					return;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	public Text getSearchText() {
		return searchText;
	}
	
}
