package com.xored.q7.quality.mockups.issues.parts;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.xored.q7.quality.mockups.combotree.ComboTree;
import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.ui.treeviewer.model.TreeViewerModel;
import com.xored.q7.quality.mockups.ui.treeviewer.provider.CategoryProvider;
import com.xored.q7.quality.mockups.ui.treeviewer.provider.TreeLabelProvider;

public class QS3169_ComboTree extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(content);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(content);

		GridDataFactory labelData = GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(false, false);
		GridDataFactory comboData = GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false);

		Label firstComboLabel = new Label(content, SWT.NONE);
		firstComboLabel.setText("First combo:");

		ComboTree firstCombo = new ComboTree(content, SWT.NONE);
		setProviders(firstCombo);
		Label secondComboLabel = new Label(content, SWT.NONE);
		secondComboLabel.setText("Second combo:");

		ComboTree secondCombo = new ComboTree(content, SWT.NONE);
		setProviders(secondCombo);
		labelData.applyTo(firstComboLabel);
		labelData.applyTo(secondComboLabel);
		comboData.applyTo(firstCombo);
		comboData.applyTo(secondCombo);
		return content;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setProviders(ComboTree combo) {
		// fake instance of the same model, as used by mockups UI on the left
		TreeViewerModel input = new TreeViewerModel();
		IContentProvider content = new CategoryProvider();
		ILabelProvider label = new TreeLabelProvider();

		combo.setContentProvider(content);
		combo.setLabelProvider(label);
		combo.setInput(input);
	}
}
