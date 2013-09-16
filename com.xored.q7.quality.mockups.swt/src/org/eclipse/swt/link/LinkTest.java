package org.eclipse.swt.link;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class LinkTest extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
        Composite composite = new Composite(parent, SWT.NONE);
		
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(parent);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		
		Link l1 = new Link(composite, SWT.NONE);
		
		final Text t1 = new Text (composite, SWT.None);
		t1.setText("Listening...");
		
		l1.setText("The SWT component is designed to provide <a>efficient</a>, <a>portable</a> <a href=\"native\">access to the UI facilities of the OSs</a> on which it is implemented.");
		l1.addListener (SWT.Selection, new Listener () {
			public void handleEvent(Event event) {
				//System.out.println("Selection: " + event.text);
				t1.setText(event.text);
			}
		});
		
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Link Test";
	}

}
