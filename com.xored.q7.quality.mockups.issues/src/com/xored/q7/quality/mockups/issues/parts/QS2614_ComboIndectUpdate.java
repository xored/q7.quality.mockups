package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2614_ComboIndectUpdate extends BaseMockupPart {

	@Override
	public Control construct(final Composite parent) {
		final Button button = new Button(parent, 0);
		button.setText("Create");
		button.addSelectionListener(new SelectionListener() {
			int i = 0;

			public void widgetSelected(SelectionEvent e) {
				final CCombo ccombo = new CCombo(parent, SWT.BORDER);
				parent.layout(true);
				ccombo.add("Item 1");
				ccombo.add("Item 2");
				ccombo.add("Item 3");
				ccombo.add("Item 4");
				ccombo.select(i++ % 4);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		return button;
	}

	@Override
	public String getLabel() {
		return "QS-2614 Combo create and select on recorded event";
	}

}
