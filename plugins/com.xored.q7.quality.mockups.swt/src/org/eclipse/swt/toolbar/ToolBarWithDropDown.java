package org.eclipse.swt.toolbar;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ToolBarWithDropDown extends BaseMockupPart  {
	
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		final Text t = new Text(composite, SWT.BORDER | SWT.MULTI);
		t.setText ("Test at the pressing");
		Rectangle clientArea2 = composite.getClientArea ();
		t.setBounds (clientArea2.x, clientArea2.y, 400, 300);
				
		final ToolBar toolBar = new ToolBar (composite, SWT.BORDER);
		Rectangle clientArea = composite.getClientArea ();
		toolBar.setLocation(clientArea.x, clientArea.y);
				
		final Menu menu = new Menu (composite);
		
		for (int i=0; i<8; i++) {
			MenuItem item = new MenuItem (menu, SWT.PUSH);
			item.setText ("Item " + i);
			final String t2 = item.getText();
			item.addListener (SWT.Selection, new Listener () {
				public void handleEvent (Event e) {
					t.setText(t2);
				}
			});				
		}
		final ToolItem item = new ToolItem (toolBar, SWT.DROP_DOWN);
		item.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				if (event.detail == SWT.ARROW) {
					Rectangle rect = item.getBounds ();
					Point pt = new Point (rect.x, rect.y + rect.height);
					pt = toolBar.toDisplay (pt);
					menu.setLocation (pt.x, pt.y);
					menu.setVisible (true);
				}
			
			}
		});
		toolBar.pack ();
		
		return null;
	}

}
