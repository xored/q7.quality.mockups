package com.eclipse.jface.tooltip;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TableWithTooltipSupport extends BaseMockupPart {

	private static class ToolTipSupport extends DefaultToolTip {
		private ColumnViewer viewer;

		protected ToolTipSupport(ColumnViewer viewer, int style, boolean manualActivation) {
			super(viewer.getControl(), style, manualActivation);
			this.viewer = viewer;
		}

		@Override
		protected Object getToolTipArea(Event event) {
			Table table = (Table) event.widget;
			int columns = table.getColumnCount();
			Point point = new Point(event.x, event.y);
			TableItem item = table.getItem(point);

			if (item != null) {
				for (int i = 0; i < columns; i++) {
					if (item.getBounds(i).contains(point)) {
						return item.getText();
					}
				}
			}

			return null;
		}

		@Override
		protected Composite createToolTipContentArea(Event event, Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new FillLayout());

			if (event.widget instanceof Table) {
				Table table = (Table) event.widget;
				Point point = new Point(event.x, event.y);
				TableItem tableItem = table.getItem(point);

				Label label = new Label(composite, SWT.NONE);
				label.setText("Label text here for TABLE Item " + tableItem.getText());

				return composite;
			}

			return null;
		}

		public static void enableFor(ColumnViewer viewer) {
			new ToolTipSupport(viewer, ToolTip.NO_RECREATE, false);
		}
	}

	@Override
	public Control construct(Composite parent) {

		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Column Alfa");
		column1.setWidth(100);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Column Beta");
		column2.setWidth(100);
		TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("Column Gamma");
		column3.setWidth(100);

		for (int i = 0; i < 5; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "tooltiped-" + i, "b", i + "" });

		}

		TableViewer tableViewer = new TableViewer(table);
		ToolTipSupport.enableFor(tableViewer);

		return null;
	}

}
