package com.xored.q7.quality.mockups.issues.parts.internal;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxLabelProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class VeryCustomComboPropertyDescriptor extends PropertyDescriptor {

	private String[] labels;

	public VeryCustomComboPropertyDescriptor(Object id, String displayName, String[] labelsArray) {
		super(id, displayName);
		labels = labelsArray;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new VeryCustomComboBoxCellEditor(parent, labels, SWT.READ_ONLY);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}

	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new ComboBoxLabelProvider(labels);
	}

}
