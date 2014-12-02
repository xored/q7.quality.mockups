package org.eclipse.swt.toolbar;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ToolbarWithCombo extends BaseMockupPart {
	
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		ToolBar bar = new ToolBar (composite, SWT.BORDER);
		Rectangle clientArea = composite.getClientArea ();
		bar.setLocation (clientArea.x, clientArea.y);
		for (int i=0; i<4; i++) {
			ToolItem item = new ToolItem (bar, 0);
			item.setText ("Item " + i);
		}
		ToolItem sep = new ToolItem (bar, SWT.SEPARATOR);
		int start = bar.getItemCount ();
		for (int i=start; i<start+4; i++) {
			ToolItem item = new ToolItem (bar, 0);
			item.setText ("Item " + i);
		}
		Combo combo = new Combo (bar, SWT.READ_ONLY);
		for (int i=0; i<4; i++) {
			combo.add ("Item " + i);
		}
		combo.pack ();
		sep.setWidth (combo.getSize ().x);
		sep.setControl (combo);
		
		return null;
	}

}
