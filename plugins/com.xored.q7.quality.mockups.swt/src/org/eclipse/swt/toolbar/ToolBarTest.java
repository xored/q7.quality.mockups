package org.eclipse.swt.toolbar;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ToolBarTest extends BaseMockupPart {
	
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		final Text t = new Text(composite, SWT.BORDER | SWT.MULTI);
		t.setText ("Test at the pressing");
		Rectangle clientArea2 = composite.getClientArea ();
		t.setBounds (clientArea2.x, clientArea2.y, 400, 300);
		
		ToolBar bar = new ToolBar (composite, SWT.BORDER);
		for (int i=0; i<8; i++) {
			ToolItem item = new ToolItem (bar, SWT.PUSH);
			item.setText ("Item " + i);
			final String t2 = item.getText();
			item.addListener (SWT.Selection, new Listener () {
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
