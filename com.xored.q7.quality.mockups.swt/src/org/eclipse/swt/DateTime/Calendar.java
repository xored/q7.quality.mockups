package org.eclipse.swt.DateTime;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Calendar extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

		DateTime c1 = new DateTime(composite, SWT.CALENDAR);
		c1.setDate(2013, 02, 8);

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Calendar Test";
	}

}
