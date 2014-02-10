package com.xored.q7.quality.mockups.draw2d;

import org.eclipse.draw2d.CheckBox;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class Draw2DPart extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(content);
		GridLayoutFactory.fillDefaults().applyTo(content);

		FigureCanvas canvas = new FigureCanvas(content);
		canvas.setContents(new CheckBox("Foo bar baz"));
		return content;
	}

	@Override
	public String getLabel() {
		return null;
	}

}
