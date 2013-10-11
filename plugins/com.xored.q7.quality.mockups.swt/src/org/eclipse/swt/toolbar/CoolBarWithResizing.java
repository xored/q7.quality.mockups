package org.eclipse.swt.toolbar;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CoolBarWithResizing extends BaseMockupPart {
	
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite); 
		
	    CoolBar coolBar = new CoolBar(composite, SWT.NONE);
	    createItem(coolBar, 3);
	    createItem(coolBar, 2);
	    createItem(coolBar, 3);
	    createItem(coolBar, 4);
	    int style = SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL;
	    Text text = new Text(composite, style);
	    FormLayout layout = new FormLayout();
	    composite.setLayout(layout);
	    FormData coolData = new FormData();
	    coolData.left = new FormAttachment(0);
	    coolData.right = new FormAttachment(100);
	    coolData.top = new FormAttachment(0);
	    coolBar.setLayoutData(coolData);
	    coolBar.addListener(SWT.Resize, new Listener() {
	        public void handleEvent(Event event) {
	            composite.layout();
	        }
	    });
	    FormData textData = new FormData();
	    textData.left = new FormAttachment(0);
	    textData.right = new FormAttachment(100);
	    textData.top = new FormAttachment(coolBar);
	    textData.bottom = new FormAttachment(100);
	    text.setLayoutData(textData);

		return null;
	}
	
	static int itemCount;
	static CoolItem createItem(CoolBar coolBar, int count) {
	    ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);
	    for (int i = 0; i < count; i++) {
	        ToolItem item = new ToolItem(toolBar, SWT.PUSH);
	        item.setText(itemCount++ +"");
	    }
	    toolBar.pack();
	    Point size = toolBar.getSize();
	    CoolItem item = new CoolItem(coolBar, SWT.NONE);
	    item.setControl(toolBar);
	    Point preferred = item.computeSize(size.x, size.y);
	    item.setPreferredSize(preferred);
	    return item;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
