package com.xored.q7.quality.mockups.issues.parts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS1957_StuartTableDemoPart extends BaseMockupPart {
	private static class SampleData {
		public String name;
		public String value;

		public SampleData(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public static List<SampleData> createSample() {
			return new ArrayList<SampleData>(Arrays.asList(new SampleData(
					"Name1", "value1"), new SampleData("Name2", "value2")));
		}
	}

	private static class SampleTableContentProvider implements
			IStructuredContentProvider {

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			return ((List<SampleData>) inputElement).toArray();
		}

	}

	private TableViewer viewer;

	public String getLabel() {
		return "Stuart table demo: First column has cell editor, others have mouse listeners";
	}

	public Control construct(Composite section) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(section);

		viewer = new TableViewer(section);
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(viewer.getControl());
		viewer.setContentProvider(new SampleTableContentProvider());
		TableViewerColumn col1 = new TableViewerColumn(viewer, SWT.NONE);
		col1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleData) element).name;
			}
		});
		col1.setEditingSupport(new EditingSupport(viewer) {

			protected void setValue(Object element, Object value) {
				((SampleData) element).name = (String) value;
				viewer.update(element, null);
			}

			protected Object getValue(Object element) {
				return ((SampleData) element).name;
			}

			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor(viewer.getTable());
			}

			protected boolean canEdit(Object element) {
				return true;
			}
		});
		col1.getColumn().setText("Name");
		col1.getColumn().setWidth(200);

		TableViewerColumn col2 = new TableViewerColumn(viewer, SWT.NONE);
		col2.getColumn().setText("Value");
		col2.getColumn().setWidth(400);
		col2.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleData) element).value;
			}
		});

		viewer.getTable().addListener(SWT.MouseUp, new Listener() {

			public void handleEvent(Event event) {
				handleMouseEvent(new Point(event.x, event.y));

			}
		});
		viewer.setInput(SampleData.createSample());
		return viewer.getControl();
	}

	private void handleMouseEvent(Point point) {
		Table table = viewer.getTable();
		TableItem item = table.getItem(point);
		if (item == null) {
			return;
		}
		for (int i = 0; i < table.getColumnCount(); i++) {
			Rectangle bounds = item.getBounds(i);
			if (bounds.contains(point) && i == 1) {
				showPopup(item, bounds);
			}
		}
	}

	private Shell proposals;

	private void showPopup(final TableItem item, Rectangle cellBounds) {
		if (proposals != null && !proposals.isDisposed()) {
			proposals.close();
			proposals.dispose();
			proposals = null;
		}
		proposals = new Shell(item.getParent().getDisplay().getActiveShell(),
				SWT.ON_TOP | SWT.RESIZE);
		Point leftDown = item.getParent().toDisplay(
				new Point(cellBounds.x, cellBounds.y + cellBounds.height));
		Rectangle shellBounds = new Rectangle(leftDown.x, leftDown.y + 10,
				cellBounds.width, 200);
		proposals.setBounds(shellBounds);
		final Table table = new Table(proposals, SWT.H_SCROLL | SWT.V_SCROLL);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(proposals);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(table);

		for (String str : new String[] { "foo", "bar", "baz" }) {
			TableItem ti = new TableItem(table, 0);
			ti.setText(str);
		}

		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				((SampleData) item.getData()).value = table.getSelection()[0]
						.getText();
				viewer.refresh();
				proposals.close();
				proposals.dispose();
				proposals = null;
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		proposals.open();
	}
}
