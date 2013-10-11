package org.eclipse.swt.DateTime;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Time extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		DateTime time = new DateTime(composite, SWT.TIME);
		time.setTime(11, 22, 33);
		
		
		DateTime time2 = new DateTime(composite, SWT.TIME|SWT.SHORT);
		time2.setTime(11, 22, 33);
				
		DateTime time3 = new DateTime(composite, SWT.TIME|SWT.LONG);
		time3.setTime(11, 22, 33);
		
		
		DateTime time4 = new DateTime(composite, SWT.TIME|SWT.MEDIUM);
		time4.setTime(11, 22, 33);
		time4.setEnabled(false);
		
				
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Time Test";
	}

}
