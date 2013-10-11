package org.eclipse.swt.table;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class SimpleDialogCellEditPart extends BaseMockupPart {
	private class MyCellModifier implements ICellModifier {

		private TableViewer viewer;

		public MyCellModifier(TableViewer viewer) {
			this.viewer = viewer;
		}

		public boolean canModify(Object element, String property) {
			return true;
		}

		public Object getValue(Object element, String property) {
			// We need to calculate back to the index
			return new Integer(((MyModel) element).counter / 10);
		}

		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			// We get the index and need to calculate the real value
			((MyModel) item.getData()).counter = ((Integer) value).intValue() * 10;
			viewer.update(item.getData(), null);
		}
	}

	private class MyContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return (MyModel[]) inputElement;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	public class MyModel {
		public int counter;

		public MyModel(int counter) {
			this.counter = counter;
		}

		public String toString() {
			return "Item " + this.counter;
		}
	}

	private MyModel[] createModel() {
		MyModel[] elements = new MyModel[10];

		for (int i = 0; i < 10; i++) {
			elements[i] = new MyModel(i * 10);
		}

		return elements;
	}

	public String getLabel() {
		return "Simple Dialog CellEdit Table Test";
	}

	public Control construct(Composite section) {
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(section);

		final Table table = new Table(section, SWT.BORDER | SWT.FULL_SELECTION);
		final TableViewer v = new TableViewer(table);
		final MyCellModifier modifier = new MyCellModifier(v);

		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setWidth(200);

		v.setLabelProvider(new LabelProvider());
		v.setContentProvider(new MyContentProvider());
		v.setCellModifier(modifier);
		v.setColumnProperties(new String[] { "column1" });
		v.setCellEditors(new CellEditor[] { new DialogCellEditor(table) {

			protected Object openDialogBox(Control cellEditorWindow) {
				Object oldValue = getValue();
				Dialog dialog = new Dialog(cellEditorWindow.getShell()) {

					protected Control createContents(Composite parent) {
						Label l = new Label(parent, SWT.NONE);
						l.setText("Please presse OK to add 1 to cell value");
						return super.createContents(parent);
					}
				};
				int open = dialog.open();
				if (open == Dialog.OK) {
					return Integer.valueOf((((Integer) oldValue).intValue() + 1));
				}
				return oldValue;
			}
		} });

		MyModel[] model = createModel();
		v.setInput(model);
		v.getTable().setLinesVisible(true);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
		return table;
	}
}
