package com.xored.q7.quality.mockups.swt;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class CComboPart extends BaseMockupPart {

	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		Label label = new Label(composite, SWT.NONE);
		label.setText("Editable CCombo");

		CCombo combo = new CCombo(composite, SWT.BORDER);
		combo.setItems(new String[] { "One", "Two", "Three" });
		return null;
	}

	public String getLabel() {
		return "CCombo test";
	}

}
