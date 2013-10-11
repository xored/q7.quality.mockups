package com.xored.q7.quality.mockups.issues.parts;

import static org.eclipse.swt.SWT.FILL;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2641_DragDrop extends BaseMockupPart {
	@Override
	public String getLabel() {
		return "QS-2641 - Drag-n-drop";
	}

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(FILL, FILL).grab(true, true)
				.applyTo(content);
		GridLayoutFactory.swtDefaults().applyTo(content);

		TreeViewer viewer = new TreeViewer(content);
		TreeViewerColumn nameColumn = new TreeViewerColumn(viewer, SWT.NONE);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(200);
		TreeViewerColumn descColumn = new TreeViewerColumn(viewer, SWT.NONE);
		descColumn.getColumn().setText("Short description");
		descColumn.getColumn().setWidth(200);

		viewer.getTree().addListener(SWT.MouseDown, new Listener() {

			public void handleEvent(Event event) {
				// TODO Auto-generated method stub

			}
		});
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		int operations = DND.DROP_MOVE | DND.DROP_COPY;
		viewer.addDragSupport(operations, transferTypes, new DragListener(
				viewer));
		viewer.addDropSupport(operations, transferTypes, new DropListener(
				viewer));
		viewer.setContentProvider(new ContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.setInput(createSample());

		GridDataFactory.swtDefaults().align(FILL, FILL).grab(true, true)
				.applyTo(viewer.getControl());
		viewer.getTree().setHeaderVisible(true);
		return content;
	}

	public static Node[] createSample() {
		Node root = new Node("!!!Sandbox!!!");
		root.addChild(new Node("O1 [00003Q]"));
		root.addChild(new Node("O2 [00003R]"));
		return new Node[] { root };
	}

	public static class Node {
		public Node(String text) {
			this.text = text;
		}

		public final String text;

		protected Node parent;
		protected List<Node> children = new ArrayList<Node>();

		public Node[] getChildren() {
			return children.toArray(new Node[children.size()]);
		}

		public boolean isRoot() {
			return parent == null;
		}

		public void addChild(Node node) {
			if (node.parent != null) {
				node.parent.children.remove(node);
			}
			children.add(node);
			node.parent = this;
		}

		public String getPath() {
			return parent == null ? text : String.format("%s/%s",
					parent.getPath(), text);
		}

		public Node childByPath(String path) {
			if (text.equals(path)) {
				return this;
			}

			if (!path.startsWith(text)) {
				return null;
			}
			String reducedPath = path.substring(text.length() + 1);
			for (Node child : children) {
				Node result = child.childByPath(reducedPath);
				if (result != null) {
					return result;
				}
			}
			return null;
		}
	}

	public static class LabelProvider implements ITableLabelProvider {

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}

		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex != 0) {
				return null;
			}
			if (((Node) element).isRoot()) {
				return PlatformUI.getWorkbench().getSharedImages()
						.getImage(ISharedImages.IMG_OBJ_FOLDER);
			}
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_FILE);
		}

		public String getColumnText(Object element, int columnIndex) {
			return columnIndex == 0 ? ((Node) element).text : "";
		}

	}

	public static class ContentProvider implements ITreeContentProvider {

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public Object[] getElements(Object inputElement) {
			return (Node[]) inputElement;

		}

		public Object[] getChildren(Object parentElement) {
			return ((Node) parentElement).getChildren();
		}

		public Object getParent(Object element) {
			return ((Node) element).parent;
		}

		public boolean hasChildren(Object element) {
			return ((Node) element).getChildren().length > 0;
		}

	}

	public static class DragListener implements DragSourceListener {
		private TreeViewer viewer;

		public DragListener(TreeViewer viewer) {
			this.viewer = viewer;
		}

		public void dragStart(DragSourceEvent event) {
		}

		public void dragSetData(DragSourceEvent event) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			Node node = (Node) selection.getFirstElement();
			event.data = node.getPath();
		}

		public void dragFinished(DragSourceEvent event) {
		}

	}

	public static class DropListener extends ViewerDropAdapter {

		protected DropListener(Viewer viewer) {
			super(viewer);
		}

		@Override
		public boolean performDrop(Object data) {
			Node target = (Node) getCurrentTarget();
			String path = (String) data;
			Node source = ((Node[]) getViewer().getInput())[0]
					.childByPath(path);
			boolean perform = MessageDialog.openQuestion(null,
					"Moving a resource", String.format(
							"Is %s also a part of %s?", source.text,
							target.text));
			if (!perform) {
				return false;
			}

			target.addChild(source);
			getViewer().refresh();
			return true;
		}

		@Override
		public boolean validateDrop(Object target, int operation,
				TransferData transferType) {
			return true;
		}

	}
}
