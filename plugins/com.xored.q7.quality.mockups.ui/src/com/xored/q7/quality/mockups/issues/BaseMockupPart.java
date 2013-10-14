package com.xored.q7.quality.mockups.issues;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class BaseMockupPart implements IQ7MockupPart {

	protected Q7MockupsCategoryView view;

	@Override
	public abstract Control construct(Composite parent);

	@Override
	public abstract String getLabel();

	@Override
	public void setView(Q7MockupsCategoryView view) {
		this.view = view;
	}
}
