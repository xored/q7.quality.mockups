package org.eclipse.jface.viewer;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ComboboxCellEditorMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		final TableViewer v = new TableViewer(parent, SWT.FULL_SELECTION);
		TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);

		v.getTable().setLinesVisible(true);
		v.getTable().setHeaderVisible(true);

		final TableViewerColumn vColumn1 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column1 = vColumn1.getColumn();
		layout.setColumnData(column1, new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, false));
		column1.setText("#");

		vColumn1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				MonthModel m = (MonthModel) element;
				return String.valueOf(m.num);

			}
		});

		TableViewerColumn vColumn2 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column2 = vColumn2.getColumn();
		layout.setColumnData(column2, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, false));
		column2.setText("Month");

		vColumn2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MonthModel m = (MonthModel) element;
				return m.name;

			}
		});

		vColumn2.setEditingSupport(new EditingSupport(v) {

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected CellEditor getCellEditor(Object element) {

				ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Table) v.getControl(),
						SWT.READ_ONLY);
				cellEditor.setContentProvider(new ArrayContentProvider());

				cellEditor.setLabelProvider(new LabelProvider() {
					@Override
					public String getText(Object element) {
						return ((MonthModel) element).name;
					}
				});

				cellEditor.setInput(MONTHS);

				return cellEditor;
			}

			@Override
			protected Object getValue(Object element) {
				return MONTHS[((MonthModel) element).num - 1];
			}

			@Override
			protected void setValue(Object element, Object value) {
				final MonthModel valueModel = (MonthModel) value;
				((MonthModel) element).name = valueModel.name;
				((MonthModel) element).num = valueModel.num;

				v.update(element, null);
			}

		});

		MonthModel[] m = createModel();

		v.setContentProvider(new MyContentProvider());

		v.setInput(m);

		return null;

	}

	public class MyContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return (MonthModel[]) inputElement;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

	}

	public static class MonthModel {

		int num;
		String name;

		static String[] names = new String[] { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" };

		MonthModel(int i) {
			this.num = i + 1;
			this.name = names[i];
		}
	}

	public MonthModel[] createModel() {

		MonthModel[] elements = new MonthModel[1];

		for (int i = 0; i < 1; i++) {

			elements[i] = new MonthModel(i);

		}

		return elements;
	}

	private static MonthModel[] createMonths() {
		MonthModel[] elements = new MonthModel[MonthModel.names.length];

		for (int i = 0; i < elements.length; i++)
			elements[i] = new MonthModel(i);

		return elements;
	}

	private final static MonthModel[] MONTHS = createMonths();

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Combobox Cell Editor Test";
	}

}
