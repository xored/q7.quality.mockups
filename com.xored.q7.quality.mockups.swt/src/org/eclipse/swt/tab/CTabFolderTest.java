package org.eclipse.swt.tab;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CTabFolderTest extends BaseMockupPart {

	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		CTabFolder folder = new CTabFolder(composite, SWT.BORDER);
		for (int i = 0; i < 4; i++) {
			CTabItem item = new CTabItem(folder, SWT.CLOSE);
			item.setText("Item "+i);
			Text text = new Text(folder, SWT.MULTI);
			text.setText("Content for Item "+i);
			item.setControl(text);
		}
		
		final CTabItem specialItem = new CTabItem(folder, SWT.CLOSE);
		specialItem.setText("Don't Close Me");
		Text text = new Text(folder, SWT.MULTI);
		text.setText("This tab can never be closed");
		specialItem.setControl(text);
			
		folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
			public void close(CTabFolderEvent event) {
				if (event.item.equals(specialItem)) {
					event.doit = false;
				}
			}
		});
		
		final CTabItem noCloseItem = new CTabItem(folder, SWT.NONE);
		noCloseItem.setText("No Close Button");
		Text text2 = new Text(folder, SWT.MULTI);
		text2.setText("This tab does not have a close button");
		noCloseItem.setControl(text2);
		
		return null;
	}
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Button Push Test";
	}


}
