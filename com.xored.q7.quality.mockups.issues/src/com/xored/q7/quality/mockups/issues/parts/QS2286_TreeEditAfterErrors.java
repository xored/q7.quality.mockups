package com.xored.q7.quality.mockups.issues.parts;

import java.util.AbstractMap;
import java.util.Map;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2286_TreeEditAfterErrors extends BaseMockupPart {

	private static final class PairEditingSupport extends
			EditingSupport {
		private PairEditingSupport(ColumnViewer viewer) {
			super(viewer);
		}

		@Override
		protected void setValue(Object element, Object value) {
			@SuppressWarnings("unchecked")
			Map.Entry<String,String> pair = (Map.Entry<String,String>)element;
			pair.setValue((String)value);
			getViewer().refresh(pair);
		}

		@Override
		@SuppressWarnings("unchecked")			
		protected Object getValue(Object element) {
			return ((Map.Entry<String,String>)element).getValue();
		}

		@Override
		protected CellEditor getCellEditor(Object element) {				
			return new TextCellEditor((Composite) getViewer().getControl());
		}

		@Override
		protected boolean canEdit(Object element) {
			if (element instanceof Map.Entry<?, ?>) {
				Map.Entry<?,?> entry = (Map.Entry<?,?>)element; 
				return entry.getKey() instanceof String
						&& entry.getValue() instanceof String;
			}
			return false;
		}
	}
	@Override
	public Control construct(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.MULTI);
		viewer.getTree().setLinesVisible(true);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getTree());
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setWidth(100);
		column.setLabelProvider(new ColumnLabelProvider(){

			@SuppressWarnings("unchecked")
			@Override
			public String getText(Object element) {
				return ((Map.Entry<String,String>)element).getKey();
			}});
		column = new TreeViewerColumn(viewer, SWT.LEFT);
		column.getColumn().setWidth(100);
		column.setEditingSupport(new PairEditingSupport(viewer));
		column.setLabelProvider(new ColumnLabelProvider(){

			@Override
			public String getText(Object element) {
				@SuppressWarnings("unchecked")
				String buffer = ((Map.Entry<String,String>)element).getValue();
				return buffer.toString();
			}			
		});
		viewer.setContentProvider(new ITreeContentProvider() {
			
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
			
			public void dispose() {
			}
			
			public boolean hasChildren(Object element) {
				return false;
			}
			
			public Object getParent(Object element) {
				return null;
			}
			
			@SuppressWarnings("unchecked")
			public Object[] getElements(Object inputElement) {
				return (Map.Entry<String,String>[]) inputElement;
			}
			
			public Object[] getChildren(Object parentElement) {
				return null;
			}
		});
		Map.Entry<String,String> value = new AbstractMap.SimpleEntry<String,String>("Key 1", "Value 1");
		viewer.setInput(new Map.Entry<?,?>[]{value});
		return viewer.getTree();
	}

	@Override
	public String getLabel() {
		return "QS-2286 - Tree edit after errors";
	}

}
