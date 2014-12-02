package org.eclipse.swt.tab;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CTabFolderWithResize extends BaseMockupPart {

	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		// Display display = new Display ();
		// Image image = new Image(composite, 16, 16);
		// GC gc = new GC(image);
		// gc.setBackground(compositey.getSystemColor(SWT.COLOR_BLUE));
		// gc.fillRectangle(0, 0, 16, 16);
		// gc.setBackground(composite.getSystemColor(SWT.COLOR_YELLOW));
		// gc.fillRectangle(3, 3, 10, 10);
		// gc.dispose();
		// composite.setLayout(new GridLayout());
		final CTabFolder folder = new CTabFolder(composite, SWT.BORDER);
		folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		folder.setSimple(false);
		folder.setUnselectedImageVisible(false);
		folder.setUnselectedCloseVisible(false);
		for (int i = 0; i < 8; i++) {
			CTabItem item = new CTabItem(folder, SWT.CLOSE);
			item.setText("Item " + i);
			// item.setImage(image);
			Text text = new Text(folder, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
			text.setText("Text for item " + i + "\n\none, two, three\n\ttest");
			item.setControl(text);
		}
		folder.setMinimizeVisible(true);
		folder.setMaximizeVisible(true);
		folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
			public void minimize(CTabFolderEvent event) {
				folder.setMinimized(true);
				folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
				composite.layout(true);
			}

			public void maximize(CTabFolderEvent event) {
				folder.setMaximized(true);
				folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				composite.layout(true);
			}

			public void restore(CTabFolderEvent event) {
				folder.setMinimized(false);
				folder.setMaximized(false);
				folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
				composite.layout(true);
			}
		});

		return null;
	}

}
