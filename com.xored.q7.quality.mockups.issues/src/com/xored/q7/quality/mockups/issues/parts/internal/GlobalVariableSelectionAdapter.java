package com.xored.q7.quality.mockups.issues.parts.internal;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GlobalVariableSelectionAdapter extends SelectionAdapter {
	
	public void widgetSelected(SelectionEvent e) {
		new GlobalVariableSelectionDialog().open();
	}
}
