package org.eclipse.swt.button;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Button_Wrap extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		// TODO Auto-generated method stub

		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

		String string = "The quick brown fox jumps over the lazy dog";
		Button button;
		button = new Button(composite, SWT.PUSH | SWT.WRAP);
		button.setText(string);
		button = new Button(composite, SWT.RADIO | SWT.WRAP);
		button.setText(string);
		button = new Button(composite, SWT.TOGGLE | SWT.WRAP);
		button.setText(string);
		button = new Button(composite, SWT.CHECK | SWT.WRAP);
		button.setText(string);

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Wrap Button test";
	}

}
