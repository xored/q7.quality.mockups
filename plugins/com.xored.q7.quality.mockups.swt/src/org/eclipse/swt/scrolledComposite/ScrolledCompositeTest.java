package org.eclipse.swt.scrolledComposite;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ScrolledCompositeTest extends BaseMockupPart  {
	
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
	      GridLayout layout = new GridLayout();
	      
      // set the minimum width and height of the scrolled content 
	      final ScrolledComposite sc2 = new ScrolledComposite(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
	      sc2.setExpandHorizontal(true);
	      sc2.setExpandVertical(true);
	      final Composite c2 = new Composite(sc2, SWT.NONE);
	      sc2.setContent(c2);
	      layout = new GridLayout();
	      layout.numColumns = 4;
	      c2.setLayout(layout);
	      Button b2 = new Button (c2, SWT.PUSH);
	      b2.setText("first button");
	      sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	      
	      final Text t = new Text(composite, SWT.BORDER | SWT.MULTI);
	      
	      Button add = new Button (composite, SWT.PUSH);
	      add.setText("add children");
	      final int[] index = new int[]{0};
	      add.addListener(SWT.Selection, new Listener() {
	          public void handleEvent(Event e) {
	              index[0]++;
              
	              Button button = new Button(c2, SWT.PUSH);
	              button.setText("button "+index[0]);
	              final String t2 = button.getText();
	              // reset the minimum width and height so children can be seen - method 2
	              sc2.setMinSize(c2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	              c2.layout();
	              button.addListener (SWT.Selection, new Listener () {
					public void handleEvent (Event e) {
						t.setText(t2);
					}
				});

	          }
	      });

	
		return null;
		
	}
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "ComboBox Test";
	}

}
