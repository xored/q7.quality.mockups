package com.xored.q7.quality.mockups.draw2d;

import org.eclipse.draw2d.CheckBox;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalViewerImpl;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class GraphicalViewerPart extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(content);
		Canvas cv = new Canvas(content, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 20).applyTo(cv);
		GridLayoutFactory.fillDefaults().applyTo(content);
		GraphicalViewer gv = new GraphicalViewerImpl() {
			@Override
			protected void createDefaultRoot() {
				setRootFigure(new CheckBox("Foo bar baz"));
			}
		};
		gv.createControl(content);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(gv.getControl());

		GraphicalViewer gv2 = new ScrollingGraphicalViewer() {
			@Override
			protected void createDefaultRoot() {
				setRootFigure(new CheckBox("Foo2 bar2 baz2"));
			}
		};
		gv2.createControl(content);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(gv2.getControl());

		return content;
	}

	@Override
	public String getLabel() {
		return null;
	}

}
