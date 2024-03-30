package org.eclipse.swt.canvas;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class ColoredCanvas extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Canvas canvas = new Canvas(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(parent);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
		    .grab(true, true).applyTo(canvas);
		Color red = new Color(parent.getDisplay(), 255, 0, 0);
		canvas.addDisposeListener(event -> red.dispose());
		canvas.setBackground(red);
		canvas.setForeground(red);
		return null;
	}

}
