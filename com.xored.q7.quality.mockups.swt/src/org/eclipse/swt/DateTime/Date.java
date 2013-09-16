package org.eclipse.swt.DateTime;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Date extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		
		DateTime date = new DateTime(composite, SWT.DATE);
		date.setDate(2013, 2, 8);
		
		DateTime date2 = new DateTime(composite, SWT.DATE|SWT.DROP_DOWN);
		date2.setDate(2013, 2, 8);
		
		DateTime date3 = new DateTime(composite, SWT.DATE|SWT.SHORT);
		date3.setDate(2012, 2, 8);
		
		DateTime date4 = new DateTime(composite, SWT.DATE|SWT.MEDIUM);
		date4.setDate(2011, 2, 8);
		date4.setEnabled(false);
		
		DateTime date5 = new DateTime(composite, SWT.DATE|SWT.LONG);
		date5.setDate(2010, 2, 8);
		
		
		
		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Date Test";
	}

}
