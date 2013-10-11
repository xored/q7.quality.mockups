package org.eclipse.swt.label;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class LabelMockup extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.NONE);

		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(parent);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

		Label l1 = new Label(composite, SWT.NONE);
		l1.setText("Label 1");

		Label l2 = new Label(composite, SWT.NONE);
		l2.setText("Label 2");

		Label l3 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		l3.setText("Label 3");

		Label l4 = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		l4.setText("Label 4");

		Label l5 = new Label(composite, SWT.NONE);
		l5.setText("Label");

		Text t1 = new Text(composite, SWT.BORDER);
		t1.setText("Text1");

		Label l6 = new Label(composite, SWT.NONE);
		l6.setText("Label");

		Text t2 = new Text(composite, SWT.BORDER);
		t2.setText("Text2");

		Label l7 = new Label(composite, SWT.NONE);
		l7.setText("Label");

		Text t3 = new Text(composite, SWT.BORDER);
		t3.setText("Text3");

		Label l8 = new Label(composite, SWT.NONE);
		l8.setText("Are you sure you want to store Material-1 [00004Z]\r\n in Storage-2 [000052]?");

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Label Test";
	}

}
