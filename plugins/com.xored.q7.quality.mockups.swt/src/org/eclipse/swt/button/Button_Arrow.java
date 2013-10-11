package org.eclipse.swt.button;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Button_Arrow extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Button 1");

			
		Button button1 = new Button(composite, SWT.ARROW | SWT.RIGHT);
		button1.setSize(100, 100);
		button1.setToolTipText("Button1");
		
		
		Label label2 = new Label(composite, SWT.NONE);
		label2.setText("Button 2");
		
		Button button2 = new Button(composite, SWT.ARROW | SWT.LEFT);
		button2.setToolTipText("Button 2");
		button2.setEnabled(false);
		
		
		Label label3 = new Label(composite, SWT.NONE);
		label3.setText("Button 3");
		
		
		Button button3 = new Button(composite, SWT.ARROW | SWT.UP);
		button3.setToolTipText("Button 3");
				
		Label label4 = new Label(composite, SWT.NONE);
		label4.setText("Button 4");
		
		Button button4 = new Button(composite, SWT.ARROW | SWT.DOWN);
		button4.setToolTipText("Button 4");
		
		
		final Text t1 = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.CENTER);
		t1.setText("Text...");
		
		
		
		button1.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        t1.setText("RIGHT");
		        //t1.selectAll();
		      }
		    });
		
		button2.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        t1.setText("LEFT");
		        //t1.selectAll();
		      }
		    });
		
		button3.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        t1.setText("UP");
		        //t1.selectAll();
		      }
		    });
		
		button4.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		        t1.setText("DOWN");
		        //t1.selectAll();
		      }
		    });
		
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Button Arrow test";
	}

}
