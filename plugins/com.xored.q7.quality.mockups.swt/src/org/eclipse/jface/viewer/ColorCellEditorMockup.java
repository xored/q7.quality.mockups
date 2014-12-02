package org.eclipse.jface.viewer;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ColorCellEditorMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		final Display display = Display.getCurrent();

		final TableViewer v = new TableViewer(parent, SWT.FULL_SELECTION);
		TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);

		v.getTable().setLinesVisible(true);
		v.getTable().setHeaderVisible(true);

		TableViewerColumn vColumn1 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column1 = vColumn1.getColumn();
		layout.setColumnData(column1, new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, false));
		column1.setText("Pick the Color");

		vColumn1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {

				return "";

			}

			public Color getBackground(Object element) {

				ColorModel m = (ColorModel) element;
				return m.cl;

			}

		});

		vColumn1.setEditingSupport(new EditingSupport(v) {
			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new ColorCellEditor((Table) v.getControl());
			}

			@Override
			protected Object getValue(Object element) {
				return ((ColorModel) element).cl.getRGB();
			}

			@Override
			protected void setValue(Object element, Object value) {
				((ColorModel) element).rgb = (RGB) value;
				((ColorModel) element).cl = new Color(display, (RGB) value);
				((ColorModel) element).name = (String) value.toString();
				v.getTable().deselectAll();
				v.refresh();
			}
		});

		TableViewerColumn vColumn2 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column2 = vColumn2.getColumn();
		layout.setColumnData(column2, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, false));
		column2.setText("Color RGB");

		vColumn2.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {

				ColorModel m = (ColorModel) element;
				return m.name;

			}

		});

		v.setContentProvider(new MyContentProvider());
		ColorModel[] m = createColorModel();

		v.setInput(m);

		return null;
	}

	private class MyContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return (ColorModel[]) inputElement;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

	}

	public class ColorModel {

		Color cl;
		String name;
		RGB rgb;

		public ColorModel(int id) {

			this.cl = Display.getCurrent().getSystemColor(id);
			this.name = this.cl.toString();
			this.rgb = this.cl.getRGB();
		}

	}

	private ColorModel[] createColorModel() {

		ColorModel[] elements = new ColorModel[5];
		final int[] colorID = new int[] { SWT.COLOR_BLUE, SWT.COLOR_CYAN, SWT.COLOR_DARK_GREEN, SWT.COLOR_DARK_YELLOW,
				SWT.COLOR_RED };

		for (int i = 0; i < 5; i++) {

			elements[i] = new ColorModel(colorID[i]);

		}

		return elements;

	}

}
