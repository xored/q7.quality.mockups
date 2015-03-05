package com.xored.q7.quality.mockups.draw2d;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class DiagramItemWithImage extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(content);
		GridLayoutFactory.fillDefaults().applyTo(content);

		ScrollingGraphicalViewer viewer = new ScrollingGraphicalViewer();
		viewer.createControl(content);
		viewer.setRootEditPart(new FreeformGraphicalRootEditPart());
		viewer.setEditPartFactory(new SimplePartFactory());
		viewer.setContents(new SimpleModel());

		return content;
	}

	public class SimpleModel {
	}

	public class SimpleEditPart extends AbstractGraphicalEditPart {

		@Override
		protected IFigure createFigure() {
			IFigure rectangle = new RectangleFigure();
			rectangle.setBackgroundColor(new Color(null, 230, 230, 255));
			return rectangle;
		}

		@Override
		protected void refreshVisuals() {
			Rectangle bounds = new Rectangle(0, 0, 100, 100);
			getFigure().setBounds(bounds);

			Label label = new Label("Simple node");
			label.setIcon(Activator.getImageDescriptor("icons/icon.png").createImage());
			label.setTextAlignment(PositionConstants.CENTER);
			label.setBounds(bounds);
			getFigure().add(label);
		}

		@Override
		protected void createEditPolicies() {
		}

	}

	public class SimplePartFactory implements EditPartFactory {

		@Override
		public EditPart createEditPart(EditPart iContext, Object iModel) {
			EditPart editPart = new SimpleEditPart();
			editPart.setModel(iModel);
			return editPart;
		}
	}

}
