package com.xored.q7.quality.mockups.issues.parts;

import static org.eclipse.swt.SWT.FILL;
import static org.eclipse.swt.SWT.NONE;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.QualityIssuesPlugin;

public class QS2531_TableWithCheckboxColumn extends BaseMockupPart {

	public QS2531_TableWithCheckboxColumn() {
	}

	@Override
	public Control construct(Composite parent) {
		checked = QualityIssuesPlugin.getImageDescriptor("icons/checked.gif")
				.createImage();
		unchecked = QualityIssuesPlugin.getImageDescriptor(
				"icons/unchecked.gif").createImage();
		Composite content = new Composite(parent, NONE);
		GridDataFactory.swtDefaults().grab(true, true).align(FILL, FILL)
				.applyTo(content);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(content);

		TableViewer viewer = new TableViewer(content);
		viewer.setContentProvider(new ArrayContentProvider());

		GridDataFactory.swtDefaults().grab(true, true).align(FILL, FILL)
				.applyTo(viewer.getTable());

		TableViewerColumn column1 = new TableViewerColumn(viewer, NONE);
		column1.getColumn().setText("Enabled");
		column1.getColumn().setWidth(100);
		column1.setLabelProvider(new QS2531LabelProvider(0));
		column1.setEditingSupport(new QS2531EditingSupport(viewer));

		TableViewerColumn column2 = new TableViewerColumn(viewer, NONE);
		column2.getColumn().setText("Name");
		column2.getColumn().setWidth(500);
		column2.setLabelProvider(new QS2531LabelProvider(1));

		viewer.getTable().setHeaderVisible(true);
		viewer.setInput(data);
		content.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				checked.dispose();
				unchecked.dispose();
			}
		});
		return content;
	}

	private Image checked = null;
	private Image unchecked = null;

	private static final Object[][] data = { { true, "Row1" },
			{ false, "Row2" } };

	private class QS2531EditingSupport extends EditingSupport {
		private ColumnViewer viewer;

		public QS2531EditingSupport(ColumnViewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new CheckboxCellEditor();
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			return ((Object[]) element)[0];
		}

		@Override
		protected void setValue(Object element, Object value) {
			((Object[]) element)[0] = value;
			viewer.update(element, null);
		}
	}

	private class QS2531LabelProvider extends CellLabelProvider {
		public QS2531LabelProvider(int column) {
			this.column = column;
		}

		private int column;

		private Object value(Object input) {
			return ((Object[]) input)[column];
		}

		private Image getImage(Object element) {
			Object val = value(element);
			return val instanceof Boolean ? (Boolean) val ? checked : unchecked
					: null;
		}

		private String getText(Object element) {
			Object val = value(element);
			return val instanceof Boolean ? "" : val.toString();
		}

		@Override
		public void update(ViewerCell cell) {
			cell.setText(getText(cell.getElement()));
			cell.setImage(getImage(cell.getElement()));

		}

	}
}
