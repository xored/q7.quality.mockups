package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2574_CComboItemsOnFocus extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		final CCombo ccombo = new CCombo(parent, SWT.BORDER | SWT.READ_ONLY);
		ccombo.setText("CCombo fake selected item");
		ccombo.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				for (int i = 0; i < 10; ++i)
					ccombo.add("CCombo Item #" + i);
			}
		});

		final Combo combo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		combo.add("Combo fake selected item");
		combo.setText("Combo fake selected item");
		combo.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				combo.removeAll();
				for (int i = 0; i < 10; ++i)
					combo.add("Combo Item #" + i);
			}
		});

		return null;
	}

	@Override
	public String getLabel() {
		return "QS-2574 Combo/CCombo provides items on focus";
	}

}
