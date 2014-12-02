package com.xored.q7.quality.mockups.swt.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

/** A tree viewer with color provider
 * http://support.xored.com/helpdesk/tickets/857
 * */
public class TreeItemBackground extends BaseMockupPart {

	class Node {
		public final Color color;
		public final String label;
		public Node(String label, Color color) {
			this.label = label;
			this.color = color;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}
	
	List<Node> createModel(Display display) {
		List<Node> rv = new ArrayList<Node>(3);
		rv.add(new Node("Red", display.getSystemColor(SWT.COLOR_RED)));
		rv.add(new Node("Green", display.getSystemColor(SWT.COLOR_GREEN)));
		rv.add(new Node("Blue", display.getSystemColor(SWT.COLOR_BLUE)));
		return rv;
	}
	
	class ColorLabelProvider extends ColumnLabelProvider {
		@Override
		public Color getBackground(Object element) {
			return ((Node)element).color;
		}
	}
	
	class TreeContentProvider extends ArrayContentProvider implements ITreeContentProvider {
		@Override
		public Object[] getChildren(Object parentElement) {
			return null;
		}
		@Override
		public Object getParent(Object element) {
			return null;
		}
		@Override
		public boolean hasChildren(Object element) {
			return false;
		}
	}
	
	@Override
	public Control construct(Composite parent) {
		TreeViewer viewer = new TreeViewer(parent);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
		viewer.setLabelProvider(new ColorLabelProvider());
		viewer.setContentProvider(new TreeContentProvider());
		viewer.setInput(createModel(parent.getDisplay()));
		return viewer.getControl();
	}

}
