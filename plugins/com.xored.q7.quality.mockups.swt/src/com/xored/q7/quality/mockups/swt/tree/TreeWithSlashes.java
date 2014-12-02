package com.xored.q7.quality.mockups.swt.tree;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class TreeWithSlashes extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		parent = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults()
				.align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(parent);
		GridLayoutFactory.swtDefaults()
				.numColumns(3)
				.applyTo(parent);

		Tree tree = new Tree(parent, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		tree.setHeaderVisible(true);
		GridDataFactory.swtDefaults()
				.align(SWT.FILL, SWT.FILL)
				.grab(true, true)
				.applyTo(tree);

		TreeColumn column1 = new TreeColumn(tree, SWT.NONE);
		column1.setText("Name column");
		column1.setWidth(200);

		TreeColumn column2 = new TreeColumn(tree, SWT.NONE);
		column2.setText("Value column");
		column2.setWidth(200);

		TreeColumn column3 = new TreeColumn(tree, SWT.NONE);
		column3.setText("Date");
		column3.setWidth(200);

		TreeViewer treeViewer = new TreeViewer(tree);
		treeViewer.setLabelProvider(new NodeLabelProvider());
		treeViewer.setContentProvider(new NodeContentProvider());
		treeViewer.setInput(createModel());

		return treeViewer.getControl();
	}

	private static List<Node> createModel() {
		final List<Node> result = new ArrayList<Node>();

		Node child1 = new Node("child1name/with/slash", "child1value/with/slash");
		Node child2 = new Node("child2name/with/slash", "child2value/with/slash");
		Node parent = new Node("parent1", null, Arrays.asList(child1, child2));
		result.add(parent);

		Node child3 = new Node("child3name-with-no-slashes", "val");
		Node child4 = new Node("child4name/with\\different\\slashes", "child4value/with\\different\\slashes");
		Node parent2 = new Node("parent2/with-slash", null, Arrays.asList(child3, child4));
		result.add(parent2);

		return result;
	}

	private static class Node {
		public String name;
		public String value;
		public String date;
		public Node parent;
		public final List<Node> children = new ArrayList<Node>();

		public Node(String name, String value) {
			this(name, value, null);
		}

		public Node(String name, String value, List<Node> children) {
			this.name = name;
			this.value = value;
			if (children != null) {
				this.children.addAll(children);
				for (Node child : this.children) {
					child.parent = this;
				}
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			this.date = dateFormat.format(date);
		}
	}

	private static class NodeLabelProvider implements ITableLabelProvider {

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Node node = (Node) element;
			switch (columnIndex) {
			case 0:
				return node.name;

			case 1:
				return node.value;

			case 2:
				return node.date;

			default:
				throw new RuntimeException("Unknown column index here.");
			}
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}

	private static class NodeContentProvider implements ITreeContentProvider {

		public NodeContentProvider() {
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@SuppressWarnings("unchecked")
		@Override
		public Object[] getElements(Object inputElement) {
			return ((List<Node>) inputElement).toArray();
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return ((Node) parentElement).children.toArray();
		}

		@Override
		public Object getParent(Object element) {
			return ((Node) element).parent;
		}

		@Override
		public boolean hasChildren(Object element) {
			return ((Node) element).children.size() != 0;
		}
	}
}
