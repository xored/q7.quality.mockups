package com.xored.q7.quality.mockups.nattable;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xored.q7.quality.mockups.issues.BaseMockupPart;
import com.xored.q7.quality.mockups.nattable.datasets.SortableGridExample;;

public class NatTableDemoSortableGrid extends BaseMockupPart {

	@Override
	public Control construct(Composite parent) {
		Composite content = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(content);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(content);

		SortableGridExample example = new SortableGridExample();
		Control control = example.createExampleControl(content);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(control);

		return content;
	}

}