package com.xored.q7.quality.mockups.issues.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RCPTT351_DragAndDropAtTable extends BaseMockupPart {

	public class TableLabelProvider extends BaseLabelProvider implements
			ITableLabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			return element.toString();
		}

	}

	public class TableContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

		@Override
		public Object[] getElements(Object inputElement) {
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) inputElement;
			return list.toArray();
		}

	}

	public class MyDragListener implements DragSourceListener {

		private final TableViewer viewer;

		public MyDragListener(TableViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public void dragFinished(DragSourceEvent event) {
		}

		@Override
		public void dragSetData(DragSourceEvent event) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			String firstElement = (String) selection.getFirstElement();

			if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
				event.data = firstElement;
			}

		}

		@Override
		public void dragStart(DragSourceEvent event) {
		}

	}

	public class MyDropListener extends ViewerDropAdapter {

		private int location = 2;
		private final Viewer viewer;
		private String current = "";

		public MyDropListener(Viewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		public void drop(DropTargetEvent event) {
			location = this.determineLocation(event);
			if (event.item != null) {
				current = event.item.getData().toString();
			}
			super.drop(event);
		}

		@Override
		public boolean performDrop(Object data) {
			@SuppressWarnings("unchecked")
			List<String> input = (List<String>) viewer.getInput();

			switch (location) {
			case 1:
				log(data.toString() + " > Dropped before the target");
				input.add(0, data.toString());
				break;
			case 2:
				log(data.toString() + " > Dropped after the target");
				input.add(data.toString());
				break;
			case 3:
				log(data.toString() + " > Dropped on the target");
				int k = 0;
				for (int i = 0; i < input.size(); i++) {
					if (input.get(i).equals(current)) {
						k = i;
						break;
					}
				}
				input.remove(k);
				input.add(k, data.toString());
				break;
			case 4:
				log(data.toString() + " > Dropped into nothing");
				input.add(0, data.toString());
				break;
			}

			viewer.setInput(input);
			return false;
		}

		@Override
		public boolean validateDrop(Object target, int operation,
				TransferData transferType) {
			return true;

		}
	}

	private Composite composite = null;
	private Text text = null;

	@Override
	public Control construct(Composite parent) {
		composite = createComposit(parent);
		TableViewer tableA = createTable(composite, getItemsForA());
		TableViewer tableB = createTable(composite, getItemsForB());
		TableViewer tableC = createTable(composite, getItemsForC());

		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		tableA.addDragSupport(operations, transferTypes, new MyDragListener(tableA));
		tableC.addDragSupport(operations, transferTypes, new MyDragListener(tableC));

		tableB.addDropSupport(operations, transferTypes, new MyDropListener(tableB));

		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));

		return null;
	}

	private Composite createComposit(Composite parent) {
		composite = new Composite(parent, SWT.FILL);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(4).applyTo(composite);
		return composite;
	}

	private TableViewer createTable(Composite shell, List<String> items) {

		TableViewer viewer = new TableViewer(shell, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new TableContentProvider());
		viewer.setLabelProvider(new TableLabelProvider());
		viewer.setInput(items);

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(viewer.getControl());

		return viewer;
	}

	private List<String> getItemsForA() {
		List<String> itemsA = new ArrayList<String>();
		itemsA.add("Indigo");
		itemsA.add("Juno");
		itemsA.add("Kepler");
		itemsA.add("Luna");
		itemsA.add("Mars");
		return itemsA;
	}

	private List<String> getItemsForB() {
		List<String> itemsB = new ArrayList<String>();
		itemsB.add("Default");
		return itemsB;
	}

	private List<String> getItemsForC() {
		List<String> itemsC = new ArrayList<String>();
		itemsC.add("Callisto");
		itemsC.add("Europa");
		itemsC.add("Ganymede");
		itemsC.add("Galileo");
		itemsC.add("Helios");
		return itemsC;
	}

	public void log(String string) {
		text.setText(text.getText() + string + "\n");
	}
}
