package org.eclipse.swt.text;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Text_Mockup extends BaseMockupPart {

	static class Table {
		private static final GridDataFactory TEXT_LAYOUT_DATA = GridDataFactory.fillDefaults().grab(true, false);
		private final Composite composite;

		Table(Composite composite) {
			this.composite = composite;
			GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);
		}

		Text add(String label, String initialText, int flags) {
			new Label(composite, SWT.NONE).setText(label);
			Text text = new Text(composite, flags);
			TEXT_LAYOUT_DATA.applyTo(text);
			text.setText(initialText);
			return text;
		}
	}

	@Override
	public Control construct(Composite parent) {

		Composite composite = parent;
		Table tab = new Table(parent);

		tab.add("Plain Limited Text:", "Left", SWT.BORDER).setTextLimit(5);
		tab.add("Right-oriented text:", "Right", SWT.BORDER | SWT.RIGHT);
		tab.add("Center-oriented text:", "Center", SWT.BORDER | SWT.CENTER);
		tab.add("Icon Search text:", "Icon Search", SWT.ICON_SEARCH);
		tab.add("Icon-Cancel text:", "Icon Cancel", SWT.BORDER | SWT.ICON_CANCEL);
		tab.add("Password text:", "Password", SWT.BORDER | SWT.PASSWORD);
		tab.add("Readonly text:", "Readonly", SWT.BORDER | SWT.READ_ONLY);
		tab.add("Echo char text:", "Echo chars", SWT.BORDER).setEchoChar('e');

		return null;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Text Test";
	}

}
