package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;

public class QS2309_Checkboxes extends BaseMockupPart {

	public Control construct(Composite parent) {
		Tree tree = new Tree(parent, SWT.CHECK | SWT.BORDER);
		GridLayoutFactory.fillDefaults().applyTo(tree);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(tree);

		TreeItem root1 = new TreeItem(tree, SWT.NONE);
		root1.setText("Root");
		TreeItem root2 = new TreeItem(tree, SWT.NONE);
		root2.setText("Root");
		TreeItem root1_1 = new TreeItem(root1, SWT.NONE);
		root1_1.setText("Root/1");

		return tree;
	}

	public String getLabel() {
		return "QS-2309, Checkbox Tree";
	}

}
