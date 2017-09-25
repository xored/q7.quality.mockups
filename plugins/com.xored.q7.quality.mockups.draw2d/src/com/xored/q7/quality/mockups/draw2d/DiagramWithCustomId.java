package com.xored.q7.quality.mockups.draw2d;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
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

public class DiagramWithCustomId extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(content);
		GridLayoutFactory.fillDefaults().applyTo(content);

		ScrollingGraphicalViewer viewer = new ScrollingGraphicalViewer();
		viewer.createControl(content);
		viewer.setRootEditPart(new FreeformGraphicalRootEditPart());
		viewer.setEditPartFactory(new CustomEditPartFactory());

		ContainerModel container = new ContainerModel("container-01");
		ComponentModel component1 = new ComponentModel("component-01", 0, 0);
		ComponentModel component2 = new ComponentModel("component-02", 200, 0);
		container.components.add(component1);
		container.components.add(component2);
		viewer.setContents(container);

		return content;
	}

	public class ContainerEditPart extends AbstractGraphicalEditPart {

		@Override
		protected IFigure createFigure() {
			FreeformLayer layer = new FreeformLayer();
			layer.setLayoutManager(new FreeformLayout());
			layer.setBorder(new LineBorder(1));
			return layer;
		}

		@Override
		protected void createEditPolicies() {
		}

		@Override
		protected List<ComponentModel> getModelChildren() {
			ContainerModel container = (ContainerModel) getModel();
			return container.components;
		}

	}

	public class ComponentEditPart extends AbstractGraphicalEditPart {

		@Override
		protected IFigure createFigure() {
			IFigure rectangle = new RectangleFigure();
			rectangle.setBackgroundColor(new Color(null, 230, 230, 255));
			return rectangle;
		}

		@Override
		protected void createEditPolicies() {
		}

		@Override
		protected void refreshVisuals() {
			ComponentModel component = (ComponentModel) getModel();
			Rectangle bounds = new Rectangle(component.x, component.y, 100, 100);
			getFigure().setBounds(bounds);
		}

	}

	public class ContainerModel {

		public String id;

		public List<ComponentModel> components = new ArrayList<ComponentModel>();

		public ContainerModel(String id) {
			this.id = id;
		}

	}

	public class ComponentModel {

		public String id;

		public int x;

		public int y;

		public ComponentModel(String id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

	}

	public class CustomEditPartFactory implements EditPartFactory {

		@Override
		public EditPart createEditPart(EditPart context, Object model) {
			EditPart editPart = null;
			if (model instanceof ContainerModel) {
				editPart = new ContainerEditPart();
			} else if (model instanceof ComponentModel) {
				editPart = new ComponentEditPart();
			}
			editPart.setModel(model);
			return editPart;
		}

	}

}
