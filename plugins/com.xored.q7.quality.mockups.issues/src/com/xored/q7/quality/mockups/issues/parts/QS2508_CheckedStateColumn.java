package com.xored.q7.quality.mockups.issues.parts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2508_CheckedStateColumn extends BaseMockupPart {
	
	private TableViewer viewer;
	
	private static class SampleData {
		public String name;
		public String value;
		public Boolean checked;

		public SampleData(String name, String value, Boolean checked) {
			this.name = name;
			this.value = value;
			this.checked = checked;
		}

		public static List<SampleData> createSample() {
			return new ArrayList<SampleData>(Arrays.asList(new SampleData(
					"Name1", "value1", false), new SampleData("Name2", "value2", true)));
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
	
	@Override
	public Control construct(Composite parent) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);

		viewer = new TableViewer(parent);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
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

		TableViewerColumn col3 = new TableViewerColumn(viewer, SWT.NONE);
		col3.getColumn().setText("Checked?");
		col3.getColumn().setWidth(20);
		col3.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleData) element).checked? "Yes" : "No";
			}
		});
		col3.setEditingSupport(new EditingSupport(viewer) {

			protected void setValue(Object element, Object value) {
				((SampleData) element).checked = (Boolean) value;
				viewer.update(element, null);
			}

			protected Object getValue(Object element) {
				return ((SampleData) element).checked;
			}

			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor(viewer.getTable(), SWT.CHECK);
			}

			protected boolean canEdit(Object element) {
				return true;
			}
		});
		
		viewer.setInput(SampleData.createSample());
		return viewer.getControl();
	}
}
