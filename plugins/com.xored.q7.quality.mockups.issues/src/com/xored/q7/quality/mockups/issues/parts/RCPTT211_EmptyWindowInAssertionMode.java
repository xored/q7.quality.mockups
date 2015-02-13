package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class RCPTT211_EmptyWindowInAssertionMode extends BaseMockupPart {

	private Composite composite = null;

	@Override
	public Control construct(Composite parent) {
		createComposit(parent);
		createTreeWithUnnamedColumn();
		return null;
	}

	private void createComposit(Composite parent) {
		composite = new Composite(parent, SWT.FILL);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(composite);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);
	}

	private void createTreeWithUnnamedColumn() {
		Tree tree = new Tree(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		tree.setHeaderVisible(true);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, true).applyTo(tree);

		TreeColumn column = new TreeColumn(tree, SWT.NONE);
		column.setWidth(200);
		column.setAlignment(SWT.LEFT);


		for (int i = 0; i < 5; i++) {
			TreeItem treeItem = new TreeItem(tree, 0);
			treeItem.setText(new String[] { "TreeItem" + i,
					Integer.toString(i) });
		}

	}
}
