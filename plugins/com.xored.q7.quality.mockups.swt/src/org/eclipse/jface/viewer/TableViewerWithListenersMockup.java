package org.eclipse.jface.viewer;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TableViewerWithListenersMockup extends BaseMockupPart {

	@SuppressWarnings("unused")
	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		final Display display = Display.getCurrent();

		final TableViewer v = new TableViewer(parent, SWT.FULL_SELECTION);
		TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);

		MonthModel[] model = createMonthModel();

		v.setContentProvider(new MyContentProvider());

		final Table table = v.getTable();

		v.getTable().setLinesVisible(true);
		v.getTable().setHeaderVisible(true);

		// ////
		Listener tableListener = new Listener() {

			Shell tooltip = null;

			Label label = null;

			/*
			 * 
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.
			 * widgets.Event)
			 */

			public void handleEvent(Event event) {

				switch (event.type) {

				case SWT.KeyDown:

				case SWT.Dispose:

				case SWT.MouseMove: {

					if (tooltip == null)
						break;

					tooltip.dispose();

					tooltip = null;

					label = null;

					break;

				}

				case SWT.MouseHover: {

					Point coords = new Point(event.x, event.y);

					TableItem item = table.getItem(coords);

					if (item != null) {

						int columnCount = table.getColumnCount();

						for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

							if (item.getBounds(columnIndex).contains(coords)) {

								/* Dispose of the old tooltip (if one exists */

								if (tooltip != null && !tooltip.isDisposed())
									tooltip.dispose();

								/* Create a new Tooltip */

								tooltip = new Shell(table.getShell(), SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);

								tooltip.setBackground(table.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

								FillLayout layout = new FillLayout();

								layout.marginWidth = 2;

								tooltip.setLayout(layout);

								label = new Label(tooltip, SWT.NONE);

								label.setForeground(table.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));

								label.setBackground(table.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

								/*
								 * Store the TableItem with the label so we can
								 * pass the mouse event later
								 */

								label.setData("_TableItem_", item);

								/* Set the tooltip text */

								label.setText("Tooltip: " + ((MonthModel) item.getData()).monthName + " : "
										+ columnIndex);

								/*
								 * Setup Listeners to remove the tooltip and
								 * transfer the received mouse events
								 */

								// label.addListener (SWT.MouseExit,
								// tooltipLabelListener);

								// label.addListener (SWT.MouseDown,
								// tooltipLabelListener);

								/* Set the size and position of the tooltip */

								Point size = tooltip.computeSize(SWT.DEFAULT, SWT.DEFAULT);

								Rectangle rect = item.getBounds(columnIndex);

								Point pt = table.toDisplay(rect.x, rect.y);

								tooltip.setBounds(pt.x, pt.y, size.x, size.y);

								/* Show it */

								tooltip.setVisible(true);

								break;

							}

						}

					}

				}

				}

			}

		};

		// ////

		table.addListener(SWT.Dispose, tableListener);
		table.addListener(SWT.KeyDown, tableListener);
		table.addListener(SWT.MouseMove, tableListener);
		table.addListener(SWT.MouseHover, tableListener);

		TableViewerColumn vColumn1 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column1 = vColumn1.getColumn();
		layout.setColumnData(column1, new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, false));
		column1.setText("#");

		vColumn1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {

				MonthModel m = (MonthModel) element;
				return "" + m.monthNum;

			}

		});

		TableViewerColumn vColumn2 = new TableViewerColumn(v, SWT.NONE);
		TableColumn column2 = vColumn2.getColumn();
		layout.setColumnData(column2, new ColumnWeightData(2, ColumnWeightData.MINIMUM_WIDTH, false));
		column2.setText("Month");

		vColumn2.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {

				MonthModel m = (MonthModel) element;
				return "" + m.monthName;

			}

		});

		v.setInput(model);

		return null;
	}

	private class MyContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return (MonthModel[]) inputElement;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

	}

	// /////

	// ////

	public class MonthModel {

		public int monthNum;
		public String monthName;
		public String comment;

		public MonthModel(int counter) {

			this.monthNum = counter;
			this.comment = "";

			switch (counter) {
			case 1:
				this.monthName = "January";
				break;
			case 2:
				this.monthName = "February";
				break;
			case 3:
				this.monthName = "March";
				break;
			case 4:
				this.monthName = "April";
				break;
			case 5:
				this.monthName = "May";
				break;
			case 6:
				this.monthName = "June";
				break;
			case 7:
				this.monthName = "July";
				break;
			case 8:
				this.monthName = "August";
				break;
			case 9:
				this.monthName = "September";
				break;
			case 10:
				this.monthName = "October";
				break;
			case 11:
				this.monthName = "November";
				break;
			case 12:
				this.monthName = "December";
				break;
			}

		}

	}

	private MonthModel[] createMonthModel() {

		MonthModel[] elements = new MonthModel[12];

		for (int i = 1; i < 13; i++) {

			elements[i - 1] = new MonthModel(i);

		}

		return elements;

	}
}
