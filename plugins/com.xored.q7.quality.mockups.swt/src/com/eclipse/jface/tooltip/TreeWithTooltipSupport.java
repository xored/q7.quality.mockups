package com.eclipse.jface.tooltip;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TreeWithTooltipSupport extends BaseMockupPart {
	private static class FancyToolTipSupport extends ColumnViewerToolTipSupport {

		protected FancyToolTipSupport(ColumnViewer viewer, int style,
				boolean manualActivation) {
			super(viewer, style, manualActivation);
		}

		protected Composite createToolTipContentArea(Event event,
				Composite parent) {
			Composite comp = new Composite(parent, SWT.NONE);
			GridLayout l = new GridLayout(1, false);
			l.horizontalSpacing = 0;
			l.marginWidth = 0;
			l.marginHeight = 0;
			l.verticalSpacing = 0;

			comp.setLayout(l);
			Browser browser = new Browser(comp, SWT.BORDER);
			browser.setText(getText(event));
			browser.setLayoutData(new GridData(200, 150));

			return comp;
		}


		public boolean isHideOnMouseDown() {
			return false;
		}

		public static final void enableFor(ColumnViewer viewer, int style) {
			new FancyToolTipSupport(viewer, style, false);
		}
	}




	@Override
	public Control construct(Composite parent) {

		TableViewer v = new TableViewer(parent, SWT.FULL_SELECTION);
		v.getTable().setLinesVisible(true);
		v.getTable().setHeaderVisible(true);
		v.setContentProvider(ArrayContentProvider.getInstance());
		FancyToolTipSupport.enableFor(v, ToolTip.NO_RECREATE);

		CellLabelProvider labelProvider = new CellLabelProvider() {

			@Override
			public String getToolTipText(Object element) {
				return "<html><body>Tooltip ("
						+ element
						+ ")<br /><a href='http://www.xored.com' target='_NEW'>www.xored.com</a></body></html>";
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5, 5);
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 2000;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 5000;
			}

			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
			}


		};
		

		TableViewerColumn column = new TableViewerColumn(v, SWT.NONE);

		column.setLabelProvider(labelProvider);
		
		column.getColumn().setText("Column 1");
		column.getColumn().setWidth(100);
		String[] values = new String[] { "one", "two", "three", "four", "five",
				"six", "seven", "eight", "nine", "ten" };
		v.setInput(values);

		System.out.println("TableViewer v: "+v);
		System.out.println("labelProvider: "+labelProvider);
		System.out.println("column: "+column);

		
		return null;
	}

}
