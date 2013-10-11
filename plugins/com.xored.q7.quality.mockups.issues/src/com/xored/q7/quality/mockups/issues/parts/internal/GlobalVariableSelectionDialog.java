package com.xored.q7.quality.mockups.issues.parts.internal;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GlobalVariableSelectionDialog extends Dialog {
	private static final String TITLE = "Global Variable Selector";

	public GlobalVariableSelectionDialog() {
		super(Display.getDefault().getActiveShell());
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(TITLE);
		newShell.setSize(650, 300);
	}

	
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		Label label = new Label(composite, SWT.NONE);
		label.setText("No vars found");
		return composite;
	}

	
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		getButton(OK).setEnabled(true);
	}

}