package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS1962_SimpleControlsPart extends BaseMockupPart {
	public String getLabel() {
		return "Reproducing QS-1962";
	}

	public Control construct(Composite section) {

		Composite composite = new Composite(section, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

		addLabel("MouseUp/Down/Focus", composite);
		Text t = new Text(composite, SWT.BORDER);
		t.setText("Please eat some muffins. They are very cool.");
		t.addFocusListener(createFocusListener());
		t.addMouseListener(new MouseAdapter() {
		});

		addLabel("Multiline and MouseUp/Down/Focus", composite);
		t = new Text(composite, SWT.BORDER | SWT.MULTI);
		t.setText("Line 1#######\nLine2qweqweqweqweqweq\nLIne3efsfhlkjsadhfkjasd\nline4kjkss");
		t.addFocusListener(createFocusListener());
		t.addMouseListener(new MouseAdapter() {
		});
		addLabel("Multiline no listeners", composite);
		t = new Text(composite, SWT.BORDER | SWT.MULTI);
		t.setText("Line 1#######\nLine2qweqweqweqweqweq\nLIne3efsfhlkjsadhfkjasd\nline4kjkss");

		return composite;
	}

	private FocusListener createFocusListener() {
		return new FocusListener() {

			public void focusLost(FocusEvent e) {

			}

			public void focusGained(FocusEvent e) {
			}
		};
	}

	private void addLabel(String labelValue, Composite composite) {
		Label l = new Label(composite, SWT.NONE);
		l.setText(labelValue);
		GridDataFactory.fillDefaults().applyTo(l);
	}
}
