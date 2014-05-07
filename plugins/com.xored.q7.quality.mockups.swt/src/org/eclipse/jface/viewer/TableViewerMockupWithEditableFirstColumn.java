package org.eclipse.jface.viewer;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TableViewerMockupWithEditableFirstColumn extends BaseMockupPart {

	@Override
	
		public Control construct(Composite parent) {
			// TODO Auto-generated method stub

			final Display display = Display.getCurrent();

			final TableViewer v = new TableViewer(parent, SWT.FULL_SELECTION);
			TableColumnLayout layout = new TableColumnLayout();
			parent.setLayout(layout);

			MonthModel[] model = createMonthModel();

			v.setContentProvider(new MyContentProvider());
			v.setLabelProvider(new MyLabelProvider2());

			v.getTable().setLinesVisible(true);
			v.getTable().setHeaderVisible(true);
			ColumnViewerToolTipSupport.enableFor(v, ToolTip.NO_RECREATE);

			
			////
			TableViewerColumn tableViewerColumn_0 = new TableViewerColumn(v,
					SWT.NONE);
			TableColumn tblclmnF = tableViewerColumn_0.getColumn();
			layout.setColumnData(tblclmnF, new ColumnWeightData(1,
					ColumnWeightData.MINIMUM_WIDTH, true));
			tblclmnF.setText("Num...");
			tableViewerColumn_0.setLabelProvider(new ColumnLabelProvider() {

				public String getText(Object element) {

					MonthModel m = (MonthModel) element;
					return m.monthIndex;

				}

				public Color getForeground(Object element) {

					Color color = display.getSystemColor(SWT.COLOR_DARK_GREEN);
					return color;
				}

			});

			tableViewerColumn_0.setEditingSupport(new EditingSupport(v) {
				@Override
				protected boolean canEdit(Object element) {
					return true;
				}

				@Override
				protected CellEditor getCellEditor(Object element) {
					return new TextCellEditor((Table) v.getControl());
				}

				@Override
				protected Object getValue(Object element) {
					return ((MonthModel) element).monthIndex;
				}

				@Override
				protected void setValue(Object element, Object value) {
					((MonthModel) element).monthIndex = (String) value;
					v.refresh();
				}
			});

			
			
			
			
			
			
			
			/////
			TableViewerColumn tableViewerColumn = new TableViewerColumn(v, SWT.NONE);
			TableColumn tblclmnFirst = tableViewerColumn.getColumn();
			layout.setColumnData(tblclmnFirst, new ColumnWeightData(1,
					ColumnWeightData.MINIMUM_WIDTH, false));
			tblclmnFirst.setText("#");

			tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {

				public String getText(Object element) {

					MonthModel m = (MonthModel) element;
					return "" + m.monthNum;

				}

				public String getToolTipText(Object element) {
					return "Tooltip (" + ((MonthModel) element).monthNum + ")";
				}

				public Point getToolTipShift(Object object) {
					return new Point(5, 5);
				}

				public int getToolTipDisplayDelayTime(Object object) {
					return 100; // msec
				}

				public int getToolTipTimeDisplayed(Object object) {
					return 5000; // msec
				}

			});

			TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(v,
					SWT.NONE);
			TableColumn tblclmnMonth = tableViewerColumn_1.getColumn();
			layout.setColumnData(tblclmnMonth, new ColumnWeightData(2,
					ColumnWeightData.MINIMUM_WIDTH, true));
			tblclmnMonth.setText("Month");

			tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {

				public String getText(Object element) {

					MonthModel m = (MonthModel) element;
					return m.monthName;

				}

				public Color getBackground(Object element) {

					Color color = display.getSystemColor(SWT.COLOR_CYAN);
					return color;

				}

				public String getToolTipText(Object element) {
					return "Tooltip (" + ((MonthModel) element).monthName + ")";
				}

				public Point getToolTipShift(Object object) {
					return new Point(5, 5);
				}

				public int getToolTipDisplayDelayTime(Object object) {
					return 100; // msec
				}

				public int getToolTipTimeDisplayed(Object object) {
					return 5000; // msec
				}

			});

			TableColumnSorter cSorter = new TableColumnSorter(v, tblclmnMonth) {
				protected int doCompare(Viewer vv, Object e1, Object e2) {

					ITableLabelProvider lp = ((ITableLabelProvider) v
							.getLabelProvider());

					String t1 = lp.getColumnText(e1, 1);
					String t2 = lp.getColumnText(e2, 1);
					return t1.compareTo(t2);
				}
			};

			cSorter.setSorter(cSorter, TableColumnSorter.ASC);
			TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(v,
					SWT.NONE);
			TableColumn tblclmnLast = tableViewerColumn_2.getColumn();
			layout.setColumnData(tblclmnLast, new ColumnWeightData(10,
					ColumnWeightData.MINIMUM_WIDTH, true));
			tblclmnLast.setText("Comments...");
			tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {

				public String getText(Object element) {

					MonthModel m = (MonthModel) element;
					return m.comment;

				}

				public Color getForeground(Object element) {

					Color color = display.getSystemColor(SWT.COLOR_DARK_GREEN);
					return color;
				}

			});

			tableViewerColumn_2.setEditingSupport(new EditingSupport(v) {
				@Override
				protected boolean canEdit(Object element) {
					return true;
				}

				@Override
				protected CellEditor getCellEditor(Object element) {
					return new TextCellEditor((Table) v.getControl());
				}

				@Override
				protected Object getValue(Object element) {
					return ((MonthModel) element).comment;
				}

				@Override
				protected void setValue(Object element, Object value) {
					((MonthModel) element).comment = (String) value;
					v.refresh();
				}
			});

			v.setInput(model);

			return null;
		}

		@Override
		public String getLabel() {
			// TODO Auto-generated method stub
			return "JFace Table Viewer Test";
		}

		private static abstract class TableColumnSorter extends ViewerComparator {
			public static final int ASC = 1;

			public static final int NONE = 0;

			public static final int DESC = -1;

			private int direction = 0;

			private TableColumn column;

			private TableViewer viewer;

			public TableColumnSorter(TableViewer viewer, TableColumn column) {
				this.column = column;
				this.viewer = viewer;
				this.column.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						if (TableColumnSorter.this.viewer.getComparator() != null) {
							if (TableColumnSorter.this.viewer.getComparator() == TableColumnSorter.this) {
								int tdirection = TableColumnSorter.this.direction;

								if (tdirection == ASC) {
									setSorter(TableColumnSorter.this, DESC);
								} else if (tdirection == DESC) {
									setSorter(TableColumnSorter.this, NONE);
								}
							} else {
								setSorter(TableColumnSorter.this, ASC);
							}
						} else {
							setSorter(TableColumnSorter.this, ASC);
						}
					}
				});
			}

			public void setSorter(TableColumnSorter sorter, int direction) {
				if (direction == NONE) {
					column.getParent().setSortColumn(null);
					column.getParent().setSortDirection(SWT.NONE);
					viewer.setComparator(null);
				} else {
					column.getParent().setSortColumn(column);
					sorter.direction = direction;

					if (direction == ASC) {
						column.getParent().setSortDirection(SWT.DOWN);
					} else {
						column.getParent().setSortDirection(SWT.UP);
					}

					if (viewer.getComparator() == sorter) {
						viewer.refresh();
					} else {
						viewer.setComparator(sorter);
					}

				}
			}

			public int compare(Viewer viewer, Object e1, Object e2) {
				return direction * doCompare(viewer, e1, e2);
			}

			protected abstract int doCompare(Viewer TableViewer, Object e1,
					Object e2);
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

		private class MyLabelProvider2 implements ITableLabelProvider {

			public Image getColumnImage(Object element, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}

			public String getColumnText(Object element, int columnIndex) {
				if (!(element instanceof MonthModel)) {
					return null;
				}
				MonthModel p = (MonthModel) element;
				switch (columnIndex) {
				case 0:
					return p.monthIndex;
				case 1:
					return p.monthNum + "";
				case 2:
					return p.monthName;
				case 3:
					return p.comment;

				}
				return "Error";
			}

			public void addListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub

			}

			public void dispose() {
				// TODO Auto-generated method stub

			}

			public boolean isLabelProperty(Object element, String property) {
				// TODO Auto-generated method stub
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub

			}

		}

		public class MonthModel {

			public int monthNum;
			public String monthName;
			public String comment;
			public String monthIndex;

			public MonthModel(int counter) {

				this.monthNum = counter;
				this.comment = "";
				this.monthIndex = "...";

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
