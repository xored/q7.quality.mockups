package org.eclipse.swt.table;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TableWithColumnMenu extends BaseMockupPart {
	int columnIndex = -1;
	boolean headerArea = false;
	TableItem onItem;

	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		// GridLayout layout = new GridLayout();

		final Table table = new Table(composite, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		final TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Column with checkbox ");
		column1.setWidth(100);

		final TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Empty column");
		column2.setWidth(100);

		final TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("Values");
		column3.setWidth(100);

		for (int i = 0; i < 5; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "", "", i + "" });

		}

		for (int i = 5; i < 10; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "", "item" + i, i + "" });

		}

		for (int i = 10; i < 12; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { "", "", "" });

		}

		Rectangle clientArea = composite.getClientArea();
		table.setBounds(clientArea.x, clientArea.y, 100, 100);
		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				String string = event.detail == SWT.CHECK ? "Checked" : "Selected";
				System.out.println(event.item + " " + string);
			}
		});

		MenuManager menuManager = new MenuManager();
		menuManager.setRemoveAllWhenShown(true);

		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.add(new Action("SomeAction") {
					@Override
					public String getText() {
						return "Column:" + columnIndex;
					}

					@Override
					public void run() {
						MessageDialog mdialog = new MessageDialog(table.getShell(), "Action", null,
								"Selected column:" + columnIndex, SWT.ICON_INFORMATION, new String[] { "OK" }, 0);
						mdialog.open();
					}
				});
			}
		});

		table.setMenu(menuManager.createContextMenu(table));

		table.addListener(SWT.MenuDetect, new Listener() {
			@Override
			public void handleEvent(Event event) {

				Point curLoc = Display.getCurrent().map(null, table, new Point(event.x, event.y));
				Rectangle clientArea = table.getClientArea();

				int headerHeight = table.getHeaderHeight();
				if (clientArea.x <= curLoc.x && curLoc.x < (clientArea.x + clientArea.width)) {
					int xOffset = 0; // Accumulates previous column widths
					for (int colIdx : table.getColumnOrder()) {
						int colWidth = table.getColumn(colIdx).getWidth();
						// Check if cursor location lies within the current
						// column
						if (xOffset <= curLoc.x && curLoc.x < (xOffset + colWidth)) {
							System.out.println("column header " + colIdx); // Your
																			// code
																			// goes
																			// here
							columnIndex = colIdx;
							break;
						}
						xOffset += colWidth;
					}
				}
			}
		});

		return null;

	}
}
