package org.eclipse.jface.viewer;

import java.util.ArrayList;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.TreeViewerEditor;
import org.eclipse.jface.viewers.TreeViewerFocusCellManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CheckboxTreeViewerMockup extends BaseMockupPart {
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new FillLayout());

		final CheckboxTreeViewer ctv = new CheckboxTreeViewer(parent);

		TreeViewerFocusCellManager focusCellManager = new TreeViewerFocusCellManager(ctv,
				new FocusCellOwnerDrawHighlighter(ctv));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(ctv) {
			protected boolean isEditorActivationEvent(
					ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
						|| event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
						|| (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
						|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};
		TreeViewerEditor.create(ctv, focusCellManager, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION);

		TreeViewerColumn column = new TreeViewerColumn(ctv, SWT.NONE);
		column.getColumn().setWidth(200);
		column.getColumn().setMoveable(true);
		column.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return "Column 1 => " + element.toString();
			}

		});

		final TreeViewerColumn column2 = new TreeViewerColumn(ctv, SWT.NONE);
		column2.getColumn().setWidth(200);
		column2.getColumn().setMoveable(true);
		column2.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return "Non checked";
			}

		});
		ctv.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				// If the item is checked . . .
				if (event.getChecked()) {
					// . . . check all its children
					ctv.setSubtreeChecked(event.getElement(), true);
				}
				else {
					ctv.setSubtreeChecked(event.getElement(), false);
				}
			}
		});

		ctv.setContentProvider(new MyTreeContentProvider());
		ctv.setInput(createModel());

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

	public class MyModel {
		public MyModel parent;
		@SuppressWarnings("rawtypes")
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
