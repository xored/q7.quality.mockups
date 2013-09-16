package org.eclipse.swt.tab;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TabFoldetTest extends BaseMockupPart {
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		final TabFolder tabFolder = new TabFolder(composite, SWT.BORDER);
		Rectangle clientArea = composite.getClientArea();
		tabFolder.setBounds(clientArea.x, clientArea.y, 200, 300);
		for (int i = 0; i < 6; i++) {
			TabItem item = new TabItem(tabFolder, SWT.NONE);
			item.setText("TabItem " + i);
			Button button = new Button(tabFolder, SWT.PUSH);
			button.setText("Page " + i);
			button.setBounds(20, 20, 100, 80);
			item.setControl(button);

			// Text t = new Text (tabFolder, SWT.BORDER);
			// t.setText("Text on page" + i);
			// t.setBounds(120, 20, 200, 80);
			// item.setControl(t);
		}
		tabFolder.pack();

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Button Push Test";
	}
}
