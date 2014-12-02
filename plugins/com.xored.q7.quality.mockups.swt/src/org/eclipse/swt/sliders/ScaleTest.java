package org.eclipse.swt.sliders;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ScaleTest extends BaseMockupPart {

	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		Scale scale = new Scale (composite, SWT.BORDER);
		Rectangle clientArea = composite.getClientArea ();
		scale.setBounds (clientArea.x, clientArea.y, 500, 64);
		scale.setMaximum (40);
		scale.setPageIncrement (5);
		
		final Text t = new Text(composite, SWT.BORDER | SWT.MULTI);
		t.setText ("Test at the moving");
		Rectangle clientArea2 = composite.getClientArea ();
		t.setBounds (clientArea2.x, clientArea2.y, 400, 300);
		
		scale.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				t.setText("Moving is executed");
			}
		});
		
	    Label label = new Label(composite, SWT.NULL);
	    label.setText("Volume:");
	    
	    final Scale scale2 = new Scale(composite, SWT.VERTICAL);
	    scale2.setBounds(0, 0, 40, 200);
	    scale2.setMaximum(20);
	    scale2.setMinimum(0);
	    scale2.setIncrement(1);
	    scale2.setPageIncrement(5);
	    
	    final Text value = new Text(composite, SWT.BORDER | SWT.SINGLE);
	    
	    scale2.addListener(SWT.Selection, new Listener() {
	      public void handleEvent(Event event) {
	        int perspectiveValue = scale2.getMaximum() - scale2.getSelection() + scale2.getMinimum();
	        value.setText("Vol: " + perspectiveValue);
	      }
	    });
	    
	    value.setEditable(false);
	    scale2.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
	    value.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
	
		return null;
	}

}
