package com.xored.q7.quality.mockups.ui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class Q7TextNotes extends ViewPart {

	private StyledText text;

	public Q7TextNotes() {
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayoutFactory.swtDefaults().applyTo(parent);
		text = new StyledText(parent, SWT.BORDER | SWT.MULTI);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(text);

		text.setFont(new Font(text.getDisplay(), new FontData("sans", 25, SWT.BOLD)));
	}

	@Override
	public void setFocus() {
	}

}
