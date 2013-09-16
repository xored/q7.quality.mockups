package org.eclipse.swt.spinner;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class SpinnerTest extends BaseMockupPart {
	
	public Control construct(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
//	    GridLayout layout = new GridLayout();
		
		Label l1 = new Label(composite, SWT.NONE);
		l1.setText("Simple spinner");
		Spinner spinner = new Spinner (composite, SWT.BORDER);
		spinner.setMinimum(0);
		spinner.setMaximum(1000);
		spinner.setSelection(500);
		spinner.setIncrement(1);
		spinner.setPageIncrement(100);
		Rectangle clientArea = composite.getClientArea();
		spinner.setLocation(clientArea.x, clientArea.y);
		spinner.pack();
		
		Label l2 = new Label(composite, SWT.NONE);
		l2.setText("Spinner with float values");

		final Spinner spinner_f = new Spinner(composite, SWT.NONE);
		// allow 3 decimal places
		spinner_f.setDigits(3);
		// set the minimum value to 0.001
		spinner_f.setMinimum(1);
		// set the maximum value to 20
		spinner_f.setMaximum(20000);
		// set the increment value to 0.010
		spinner_f.setIncrement(10);
		// set the seletion to 3.456
		spinner_f.setSelection(3456);
		spinner_f.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int selection = spinner_f.getSelection();
				int digits = spinner_f.getDigits();
				System.out.println("Selection is "+(selection / Math.pow(10, digits)));
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
