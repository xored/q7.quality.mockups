package com.xored.quality.mockups.jface.celleditors;

import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class GCheckboxCellEditor extends CheckboxCellEditor {

	private Label label;
	private String propdesc;

	public GCheckboxCellEditor(Composite parent, String pdesc) {
		super(parent);
		propdesc = pdesc;
	}

	@Override
	public void activate() {
		if (propdesc != null) {
			label.setText("  " + propdesc);
		}
	}

	public void toggle() {
		super.activate();
	}

	@Override
	public boolean isValueValid() {
		return true;
	}

	@Override
	protected boolean isCorrect(Object value) {
		return true;
	}

	@Override
	protected Control createControl(Composite parent) {
		label = new Label(parent, SWT.NONE);
		return label;
	}

}
