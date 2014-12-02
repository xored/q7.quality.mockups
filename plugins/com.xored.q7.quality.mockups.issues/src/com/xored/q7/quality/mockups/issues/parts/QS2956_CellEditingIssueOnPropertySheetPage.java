package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.parts.internal.VeryCustomComboPropertyDescriptor;

public class QS2956_CellEditingIssueOnPropertySheetPage extends BaseMockupPart {


	@Override
	public Control construct(Composite parent) {
		view.setSelection(new StructuredSelection(new SelectionElement()));
		return parent;
	}

	// --

	public static class SelectionElement implements IPropertySource {

		private static final String ID_COLOR = "color";
		private static final String ID_BORDER = "border";

		private static final IPropertyDescriptor[] DESCRIPTORS = new IPropertyDescriptor[] {
				new ComboBoxPropertyDescriptor(ID_COLOR, "color", new String[] { "Red", "Green", "Blue" }),
				new VeryCustomComboPropertyDescriptor(ID_BORDER, "border", new String[] { "Solid", "Dashed", "Dotted" })
		};

		private Integer colorValue = 0;
		private Integer borderValue = 0;

		@Override
		public Object getEditableValue() {
			return null;
		}

		@Override
		public IPropertyDescriptor[] getPropertyDescriptors() {
			return DESCRIPTORS;
		}

		@Override
		public Object getPropertyValue(Object id) {
			if (id == ID_COLOR)
				return colorValue;
			else if (id == ID_BORDER)
				return borderValue;
			else
				return null;
		}

		@Override
		public boolean isPropertySet(Object id) {
			if (id == ID_COLOR)
				return true;
			else if (id == ID_BORDER)
				return true;
			else
				return false;
		}

		@Override
		public void resetPropertyValue(Object id) {
		}

		@Override
		public void setPropertyValue(Object id, Object value) {
			if (id == ID_COLOR)
				colorValue = (Integer) value;
			else if (id == ID_BORDER)
				borderValue = (Integer) value;
		}

	}

}
