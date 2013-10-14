package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2953_GeneratedText extends BaseMockupPart {

	private Composite parent;

	@Override
	public Control construct(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		this.parent = composite;
		createText();

		return null;

	}

	private void createText() {
		Text t = new Text(parent, SWT.BORDER);
		t.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.keyCode == SWT.TAB && e.stateMask != SWT.SHIFT) {
					createText();
				}
			}
		});
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP)
				.grab(true, false).applyTo(t);
		parent.layout();
		t.setFocus();
	}

	@Override
	public String getLabel() {
		return "QS2953 - Generated dialog";
	}

}
