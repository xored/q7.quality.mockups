package org.eclipse.swt.toolbar;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CoolBarTest extends BaseMockupPart {
	
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		final Text t = new Text(composite, SWT.BORDER | SWT.MULTI);
		t.setText ("Test at the pressing");
		
		CoolBar bar = new CoolBar(composite, SWT.VERTICAL);
		for (int i=1; i<6; i++) {
			CoolItem item = new CoolItem (bar, SWT.NONE);
			final Button button = new Button (bar, SWT.PUSH);
			button.setText ("Button " + i);
			Point size = button.computeSize (SWT.DEFAULT, SWT.DEFAULT);
			item.setPreferredSize (item.computeSize (size.x, size.y));
			item.setControl (button);
			final String t2 = button.getText();
			button.addListener (SWT.Selection, new Listener () {
				public void handleEvent (Event e) {
					t.setText(t2);
				}
			});
		}
		Rectangle clientArea = composite.getClientArea ();
		bar.setLocation (clientArea.x, clientArea.y);

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
}
