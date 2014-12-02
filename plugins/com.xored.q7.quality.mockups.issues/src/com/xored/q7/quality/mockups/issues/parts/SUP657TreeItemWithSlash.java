package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.xored.q7.quality.mockups.issues.IQ7MockupPart;
import com.xored.q7.quality.mockups.issues.Q7MockupsCategoryView;

public class SUP657TreeItemWithSlash implements IQ7MockupPart {
	@Override
	public Control construct(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(container);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(container);
		Tree tree = new Tree(container, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tree);

		TreeColumn nameColumn = new TreeColumn(tree, SWT.NONE);
		nameColumn.setText("Name");
		nameColumn.setWidth(100);
		TreeColumn resourceColumn = new TreeColumn(tree, SWT.NONE);
		resourceColumn.setText("Resource");
		resourceColumn.setWidth(100);
		TreeColumn valueColumn = new TreeColumn(tree, SWT.NONE);
		valueColumn.setText("Value");
		valueColumn.setWidth(100);

		tree.setHeaderVisible(true);

		TreeItem item = new TreeItem(tree, SWT.NONE);
		item.setText(new String[] { "UART001/0", "", "" });

		TreeItem transmit = new TreeItem(item, SWT.NONE);
		transmit.setText(new String[] { "", "UART Transmit", "1.1" });

		TreeItem receive = new TreeItem(item, SWT.NONE);
		receive.setText(new String[] { "", "UART Receive", "1.1" });
		item.setExpanded(true);
		return container;
	}

	@Override
	public void setView(Q7MockupsCategoryView view) {
	}

}
