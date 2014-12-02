package org.eclipse.swt.button;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Button_Check extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		Button[] checkBoxs = new Button[5];
		checkBoxs[0] = new Button(composite, SWT.CHECK);
		checkBoxs[0].setSelection(true);
		checkBoxs[0].setText("Choice 1");
		
	 
		checkBoxs[1] = new Button(composite, SWT.CHECK);
		checkBoxs[1].setText("Choice 2");
		
	 
		checkBoxs[2] = new Button(composite, SWT.CHECK);
		checkBoxs[2].setText("Choice 3");
		checkBoxs[2].setEnabled(false);
		
		checkBoxs[3] = new Button(composite, SWT.CHECK);
		checkBoxs[3].setText("Choice 2");
		
		checkBoxs[4] = new Button(composite, SWT.CHECK);
		
		
		return null;
	}
}
