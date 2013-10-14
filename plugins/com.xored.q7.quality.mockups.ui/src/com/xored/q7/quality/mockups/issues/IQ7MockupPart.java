package com.xored.q7.quality.mockups.issues;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IQ7MockupPart {
	public Control construct(Composite parent);

	public String getLabel();

	public void setView(Q7MockupsCategoryView view);
}
