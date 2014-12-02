package org.eclipse.swt.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class MenuBarMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		// Display display = PlatformUI.getWorkbench().getDisplay();

		Button b = new Button(parent, SWT.PUSH);
		b.setText("Push to Launch Menu");

		b.addSelectionListener(new SelectionAdapter() {

			Display display;
			Shell shell;
			@SuppressWarnings("unused")
			Menu menu, fileMenu, editMenu, viewMenu;

			public void widgetSelected(SelectionEvent e) {

				shell = new Shell(display);
				shell.setText("Menu Example");
				shell.setSize(300, 200);

				menu = new Menu(shell, SWT.BAR);

				MenuItem fileItem = new MenuItem(menu, SWT.CASCADE);
				fileItem.setText("File");
				MenuItem editItem = new MenuItem(menu, SWT.CASCADE);
				editItem.setText("Edit");
				MenuItem viewItem = new MenuItem(menu, SWT.CASCADE);
				viewItem.setText("View");
				MenuItem helpItem = new MenuItem(menu, SWT.CASCADE);
				helpItem.setText("Help");

				Menu fileMenu = new Menu(menu);
				fileItem.setMenu(fileMenu);
				MenuItem newItem = new MenuItem(fileMenu, SWT.NONE);
				newItem.setText("New");
				MenuItem openItem = new MenuItem(fileMenu, SWT.NONE);
				openItem.setText("Open...");
				MenuItem saveItem = new MenuItem(fileMenu, SWT.NONE);
				saveItem.setText("Save");
				MenuItem saveAsItem = new MenuItem(fileMenu, SWT.NONE);
				saveAsItem.setText("Save As...");
				new MenuItem(fileMenu, SWT.SEPARATOR);
				MenuItem pageSetupItem = new MenuItem(fileMenu, SWT.NONE);
				pageSetupItem.setText("Page Setup...");
				MenuItem printItem = new MenuItem(fileMenu, SWT.NONE);
				printItem.setText("Print...");
				new MenuItem(fileMenu, SWT.SEPARATOR);
				MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
				exitItem.setText("Exit");

				Menu editMenu = new Menu(menu);
				editItem.setMenu(editMenu);
				MenuItem cutItem = new MenuItem(editMenu, SWT.NONE);
				cutItem.setText("Cut");
				MenuItem pasteItem = new MenuItem(editMenu, SWT.NONE);
				pasteItem.setText("Paste");

				Menu viewMenu = new Menu(menu);
				viewItem.setMenu(viewMenu);
				MenuItem toolItem = new MenuItem(viewMenu, SWT.NONE);
				toolItem.setText("ToolBars");
				MenuItem fontItem = new MenuItem(viewMenu, SWT.NONE);
				fontItem.setText("Font");

				exitItem.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent event) {
						if (((MenuItem) event.widget).getText().equals("Exit")) {
							shell.close();
						}
					}

				});

				shell.setMenuBar(menu);
				shell.open();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}

			}
		});

		return null;
	}

}
