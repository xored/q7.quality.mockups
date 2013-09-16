package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchPlugin;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeContentProvider;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeNode;
import com.xored.q7.quality.mockups.issues.parts.internal.GlobalVariableSelectionAdapter;

@SuppressWarnings("restriction")
public class QS1962_TableDemoPart extends BaseMockupPart {
	private TreeViewer viewer;

	@SuppressWarnings("unused")
	private void showPopup() {
	}

	public String getLabel() {
		return "Reproducing QS-1962";
	}

	public Control construct(Composite section) {
		ToolBar bar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
		ToolItem item = new ToolItem(bar, SWT.NONE);
		item.addSelectionListener(new GlobalVariableSelectionAdapter());
		item.setToolTipText("Browse/Select a global variable");

		item.setImage(WorkbenchPlugin.getDefault().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL)
				.createImage());
		item.setSelection(true);

		Composite client = new Composite(section, SWT.WRAP);
		GridLayoutFactory.fillDefaults().applyTo(client);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(client);
		Tree t = new Tree(client, SWT.NULL);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(t);

		viewer = new TreeViewer(t);
		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(viewer.getTree());
		viewer.setContentProvider(new SampleTreeContentProvider());

		viewer.setInput(SampleTreeNode.createSample());
		viewer.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column1;
			}
		});
		TreeViewerColumn column1 = new TreeViewerColumn(viewer, SWT.NONE);
		column1.getColumn().setWidth(200);
		column1.getColumn().setText("column1");
		column1.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column1;
			}
		});

		TreeViewerColumn column2 = new TreeViewerColumn(viewer, SWT.NONE);
		column2.getColumn().setWidth(200);
		column2.getColumn().setText("column2");
		column2.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((SampleTreeNode) element).column2;
			}
		});

		viewer.getTree().addMouseListener(new MouseAdapter() {
		});
		viewer.getTree().setHeaderVisible(true);
		viewer.refresh();
		return client;
	}
}
