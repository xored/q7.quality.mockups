package org.eclipse.jface.viewer;

import java.util.ArrayList;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TreeViewerMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new FillLayout());

		final TreeViewer tv = new TreeViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		tv.setContentProvider(new MyTreeContentProvider());

		tv.getTree().setLinesVisible(true);
		tv.getTree().setHeaderVisible(true);

		TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(tv,
				new FocusCellOwnerDrawHighlighter(tv));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tv) {
			protected boolean isEditorActivationEvent(
					ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
						|| event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
						|| (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
						|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};

		TreeViewerEditor.create(tv, focusCellManager, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);

		final TextCellEditor textCellEditor = new TextCellEditor(tv.getTree());

		TreeViewerColumn column = new TreeViewerColumn(tv, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Column 1");

		column.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return "Column 1 => " + element.toString();
			}

		});

		column = new TreeViewerColumn(tv, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.getColumn().setText("Column 2");
		column.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return "Column 2 => " + element.toString();
			}

		});

		column.setEditingSupport(new EditingSupport(tv) {
			protected boolean canEdit(Object element) {
				return true;
			}

			protected CellEditor getCellEditor(Object element) {
				return textCellEditor;
			}

			protected Object getValue(Object element) {
				return ((MyModel) element).counter + "";
			}

			protected void setValue(Object element, Object value) {
				((MyModel) element).counter = Integer
						.parseInt(value.toString());
				tv.update(element, null);
			}
		});

		tv.setLabelProvider(new LabelProvider());
		tv.setInput(createModel());

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "JFace Tree Viewer Test";
	}

	public class MyTreeContentProvider implements ITreeContentProvider {
		public Object[] getChildren(Object parentElement) {
			return getElements(parentElement);
		}

		public Object[] getElements(Object inputElement) {
			return ((MyModel) inputElement).child.toArray();
		}

		public boolean hasChildren(Object element) {
			return ((MyModel) element).child.size() > 0;
		}

		public Object getParent(Object element) {
			if (element == null) {
				return null;
			}

			return ((MyModel) element).parent;
		}

		public void dispose()
		{
		}

		public void inputChanged(Viewer viewer, Object old_input, Object new_input)
		{
		}
	}

	@SuppressWarnings("rawtypes")
	public class MyModel {
		public MyModel parent;
		public ArrayList child = new ArrayList();
		public int counter;

		public MyModel(int counter, MyModel parent) {
			this.parent = parent;
			this.counter = counter;
		}

		public String toString() {
			String rv = "Item ";
			if (parent != null) {
				rv = parent.toString() + ".";
			}

			rv += counter;

			return rv;
		}
	}

	@SuppressWarnings("unchecked")
	private MyModel createModel() {

		MyModel root = new MyModel(0, null);
		root.counter = 0;

		MyModel tmp;
		MyModel tmp2;
		for (int i = 1; i < 10; i++) {
			tmp = new MyModel(i, root);
			root.child.add(tmp);
			for (int j = 1; j < i; j++) {
				tmp2 = new MyModel(j, tmp);
				tmp.child.add(tmp2);
				if (j == 3) {
					for (int k = 0; k < 2; k++) {
						tmp2.child.add(new MyModel(k, tmp2));
					}
				}
			}
		}

		return root;
	}

}
