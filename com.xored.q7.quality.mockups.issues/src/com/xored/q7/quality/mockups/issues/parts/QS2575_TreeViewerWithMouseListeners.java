package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeContentProvider;
import com.xored.q7.quality.mockups.issues.internal.SampleTreeNode;

public class QS2575_TreeViewerWithMouseListeners extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		TreeViewer treeViewer = new TreeViewer(parent);

		GridDataFactory.fillDefaults().grab(true, true)
				.applyTo(treeViewer.getControl());

		treeViewer.setContentProvider(new SampleTreeContentProvider());
		treeViewer.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				SampleTreeNode node = (SampleTreeNode) element;
				return node.column1;
			}
		});
		treeViewer.setInput(SampleTreeNode.createSample());

		treeViewer.getTree().addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
			}
		});

		return null;
	}

	@Override
	public String getLabel() {
		return "QS-2575, Issue with tree items selection";
	}

}
