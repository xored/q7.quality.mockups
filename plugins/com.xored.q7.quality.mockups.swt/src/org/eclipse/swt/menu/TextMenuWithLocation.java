package org.eclipse.swt.menu;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

// http://support.xored.com/helpdesk/tickets/908
public class TextMenuWithLocation extends BaseMockupPart {

	private static final String[] MENU_ITEMS = new String[] { "First",
			"Second", "Third" };
	private String selectedItem = null;

	@Override
	public Control construct(Composite parent) {
		GridDataFactory grab = GridDataFactory.fillDefaults().grab(true, false);
		parent = new Composite(parent, SWT.NONE); //Additional composite to alleviate errors below
		grab.applyTo(parent);
		GridLayoutFactory.fillDefaults().applyTo(parent);
		final Text text = new Text(parent, SWT.NONE);
		grab.applyTo(text);
		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				//ERROR using comparing parent-relative coordinates with child relative 
				if (!text.getBounds().contains(e.x, e.y)) {
					return;
				}

				final Menu menu = createMenu(text);
				// ERROR here getLocation() is parent relative, so
				// text.getParent().toDisplay() should be used instead
				Point point = text.toDisplay(text.getLocation());
				menu.setLocation(point.x - 2, point.y + text.getSize().y - 2);
				menu.setVisible(true);

			}

			protected Menu createMenu(final Text text) {
				final Menu menu = new Menu(text);
				for (String item : MENU_ITEMS) {
					final MenuItem menuItem = new MenuItem(menu, SWT.RADIO);
					menuItem.setText(item);
					menuItem.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String item = ((MenuItem) e.widget).getText();
							if (selectedItem == null
									|| !item.equals(selectedItem)) {
								selectedItem = item;
								text.setText(item);
							}
						}
					});
					menuItem.setSelection(selectedItem != null
							&& selectedItem.equals(item));
				}
				return menu;
			}

		});
		return null;
	}

	@Override
	public String getLabel() {
		return "Text control with customized menu location";
	}

}
